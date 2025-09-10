package com.example.demo.application.service;

import com.example.demo.api.payload.request.RentRequest;
import com.example.demo.api.payload.response.RentResponse;
import com.example.demo.application.domain.BookEntity;
import com.example.demo.application.domain.RentEntity;
import com.example.demo.application.domain.UserEntity;
import com.example.demo.application.enums.StatusType;
import com.example.demo.application.error.code.ErrorCode;
import com.example.demo.application.error.exception.CustomException;
import com.example.demo.application.repository.BookRepository;
import com.example.demo.application.repository.RentRepository;
import com.example.demo.application.repository.UserRepository;
import com.example.demo.util.Constants;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional


public class RentServiceImpl implements RentService {

    private final BookRepository bookRepository;
    private final RentRepository rentRepository;
    private final UserRepository userRepository;

    @Override
    public RentResponse createRent(RentRequest createRentRequest) throws CustomException {

        BookEntity bookEntity = bookRepository.findByName(createRentRequest.getBookname());

        if (bookEntity == null) {
            throw new CustomException(
                    ErrorCode.BOOK_NOT_FOUND.getCode(),
                    ErrorCode.BOOK_NOT_FOUND.getMessage() + ": " + createRentRequest.getBookname()
            );
        }
        if (bookEntity.getBookCount() <= bookEntity.getRentCount()) {
            throw new CustomException(
                    ErrorCode.NO_BOOK.getCode(),
                    ErrorCode.NO_BOOK.getMessage()
            );
        }


        UserEntity userEntity = userRepository.findById(createRentRequest.getUserId())
                .orElseThrow(() -> new CustomException(
                        ErrorCode.NO_USER_FOUND.getCode(),
                        ErrorCode.NO_USER_FOUND.getMessage() + createRentRequest.getUserId())
                );


        long activeRents = rentRepository.countByUserId(userEntity.getId());
        if (activeRents >= 3) {
            throw new CustomException(
                    ErrorCode.NO_RENT_LIMIT.getCode(),
                    ErrorCode.NO_RENT_RECORD.getMessage()
            );
        }

        bookEntity.setRentCount(bookEntity.getRentCount() + 1);
        bookRepository.save(bookEntity);

        RentEntity rentEntity = RentEntity.builder()
                .bookId(bookEntity.getId())
                .userId(userEntity.getId())
                .rentDate(LocalDateTime.now())
                .build();

        RentEntity saved = rentRepository.save(rentEntity);


        return RentResponse.builder()
                .bookId(saved.getBookId())
                .userId(saved.getUserId())
                .bookName(bookEntity.getName())
                .bookCount(bookEntity.getBookCount())
                .rentCount(bookEntity.getRentCount())
                .author(bookEntity.getAuthor())
                .status(createRentRequest.getStatus())
                .userName(userEntity.getName())
                .build();
    }


    @Override
    public RentResponse deleteRent(long userId, long bookId) throws CustomException {

        List<RentEntity> rents = rentRepository.findByUserIdAndBookIdOrderByRentDateAsc(userId, bookId);

        if (rents.isEmpty()) {
            throw new CustomException(
                   ErrorCode.NO_RENT_RECORD.getCode(),
                    ErrorCode.NO_RENT_RECORD.getMessage() + " bookId : " + bookId + " userId : " + userId
            );
        }
        RentEntity oldestRent = rents.getFirst();
        BookEntity bookEntity = bookRepository.findById(bookId)
                .orElseThrow(() -> new CustomException(
                        ErrorCode.BOOK_NOT_FOUND.getCode(),
                        ErrorCode.BOOK_NOT_FOUND.getMessage()
                                + "bookId"
                                + Constants.Symbols.COLON
                                + Constants.Symbols.SPACE
                                + bookId
                        )
                );

        bookEntity.setRentCount(bookEntity.getRentCount() - 1);
        bookRepository.save(bookEntity);
        rentRepository.delete(oldestRent);


        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(
                        ErrorCode.NO_USER_FOUND.getCode(),
                        ErrorCode.NO_USER_FOUND.getMessage()
                                + "userId"
                                + Constants.Symbols.COLON
                                + Constants.Symbols.SPACE
                                + bookId
                        )
                );

        return RentResponse.builder()
                .bookId(bookEntity.getId())
                .userId(userEntity.getId())
                .bookName(bookEntity.getName())
                .bookCount(bookEntity.getBookCount())
                .rentCount(bookEntity.getRentCount())
                .author(bookEntity.getAuthor())
                .status(StatusType.valueOf(StatusType.RETURN.name())) // delete means return....
                .userName(userEntity.getName())
                .build();
    }


    @Override
    public List<RentResponse> getRentedBooks() {

        List<Long> rentedBookIds = rentRepository.findDistinctBookIds();

        List<RentResponse> rentedBooks = new ArrayList<>();

        for (Long bookId : rentedBookIds) {

            BookEntity book = bookRepository.findById(bookId).orElse(null);
            if (book == null) continue;

            // Fetch most recent rent book
            List<RentEntity> rents = rentRepository.findByBookIdOrderByRentDateDesc(bookId);
            if (rents.isEmpty()) continue;

            RentEntity lastRent = rents.getFirst(); // latest rent
            UserEntity user = userRepository.findById(lastRent.getUserId()).orElse(null);

            RentResponse response = RentResponse.builder()
                    .bookId(book.getId())
                    .userId(user != null ? user.getId() : null)
                    .bookName(book.getName())
                    .bookCount(book.getBookCount())
                    .rentCount(book.getRentCount())
                    .author(book.getAuthor())
                    .userName(user != null ? user.getName() : "Unknown")
                    .status(StatusType.RENT)
                    .build();

            rentedBooks.add(response);
        }

        return rentedBooks;
    }


}
