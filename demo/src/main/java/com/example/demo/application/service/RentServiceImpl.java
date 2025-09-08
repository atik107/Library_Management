package com.example.demo.application.service;

import com.example.demo.api.payload.request.RentRequest;
import com.example.demo.api.payload.response.RentResponse;
import com.example.demo.application.domain.BookEntity;
import com.example.demo.application.domain.RentEntity;
import com.example.demo.application.domain.UserEntity;
import com.example.demo.application.repository.BookRepository;
import com.example.demo.application.repository.RentRepository;
import com.example.demo.application.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional



public class RentServiceImpl implements RentService {

    private final BookRepository bookRepository;
    private final RentRepository rentRepository;
    private final UserRepository userRepository;

    @Override
    public RentResponse createRent(RentRequest createRentRequest) {

        BookEntity bookEntity = bookRepository.findByName(createRentRequest.getBookname());

        if (bookEntity == null) {
            throw new RuntimeException("Book not found with name: " + createRentRequest.getBookname());
        }
        if (bookEntity.getBookCount() <= bookEntity.getRentCount()) {
            throw new RuntimeException("No copies available");
        }



        UserEntity userEntity = userRepository.findById(createRentRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found, id: " + createRentRequest.getUserId()));

        bookEntity.setRentCount(bookEntity.getRentCount()+1);
        bookRepository.save(bookEntity);

        RentEntity rentEntity = RentEntity.builder()
                .bookId(bookEntity.getId())
                .userId(userEntity.getId())
                .rentDate(LocalDateTime.now())
                .build();

        RentEntity saved = rentRepository.save(rentEntity);


        RentResponse showrentResponse = RentResponse.builder()
                .bookId(saved.getBookId())
                .userId(saved.getUserId())
                .bookName(bookEntity.getName())
                .bookCount(bookEntity.getBookCount())
                .rentCount(bookEntity.getRentCount())
                .author(bookEntity.getAuthor())
                .status(createRentRequest.getStatus())
                .userName(userEntity.getName())
                .build();

        return showrentResponse;
    }



    @Override
    public RentResponse deleteRent(long userId, long bookId) {


        //List<RentEntity> rents = rentRepository.findByUserIdAndBookId(userId, bookId);
        List<RentEntity> rents = rentRepository.findByUserIdAndBookIdOrderByRentDateAsc(userId, bookId);


        if (rents.isEmpty()) {
            throw new RuntimeException("No rent record found for userId: " + userId + " and bookId: " + bookId);
        }
        RentEntity oldestRent = rents.get(0);
        BookEntity bookEntity = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found, id: " + bookId));

        bookEntity.setRentCount(bookEntity.getRentCount() - 1);
        bookRepository.save(bookEntity);
        rentRepository.delete(oldestRent);


        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found, id: " + userId));

        return RentResponse.builder()
                .bookId(bookEntity.getId())
                .userId(userEntity.getId())
                .bookName(bookEntity.getName())
                .bookCount(bookEntity.getBookCount())
                .rentCount(bookEntity.getRentCount())
                .author(bookEntity.getAuthor())
                .status(statusType.RETURN.name()) // delet means return....
                .userName(userEntity.getName())
                .build();
        //return null;
    }

    public enum statusType {
        RENT, RETURN
    }
}
