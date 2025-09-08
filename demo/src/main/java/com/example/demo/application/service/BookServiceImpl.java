package com.example.demo.application.service;

import com.example.demo.api.payload.request.BookRequest;
import com.example.demo.api.payload.response.BookResponse;
import com.example.demo.application.domain.BookEntity;
import com.example.demo.application.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


// BookRequest   BookResponse
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public BookResponse createBook(BookRequest newbookRequest) {
        BookEntity entity = BookEntity.builder()
                .name(newbookRequest.getName())
                .author(newbookRequest.getAuthor())
                .bookCount(newbookRequest.getBookCount())
                .rentCount(newbookRequest.getRentCount())
                .build();
        BookEntity saved = bookRepository.save(entity);
        return BookResponse.from(saved);
    }

    @Override
    public BookResponse getBook(long id) {

        BookEntity bookEntity = bookRepository.findById(id).orElse(null);
        if (bookEntity == null) {
            System.out.println("Book Not Found with : " + id);
            return null;
        }
        return BookResponse.from(bookEntity);
    }

    @Override
    public BookResponse updateBook(long id, BookRequest updatedBook) {

        BookEntity bookEntity = bookRepository.findById(id).orElse(null);
        if (bookEntity == null) {
            System.out.println("No update bcz Book Not Found with : " + id);
            return null;
        }
        bookEntity.setName(updatedBook.getName());
        bookEntity.setAuthor(updatedBook.getAuthor());
        BookEntity saveEntity = bookRepository.save(bookEntity);
        return BookResponse.from(saveEntity);
    }

    @Override
    public BookResponse deleteBook(long id) {

        BookEntity bookEntity = bookRepository.findById(id).orElse(null);
        if (bookEntity != null) {
            bookRepository.delete(bookEntity);
        } else {
            System.out.println("Book Not Found with : " + id);
            return null;
        }
        return BookResponse.from(bookEntity);
    }

    @Override
    public List<BookResponse> getAllBook() {
        List<BookResponse> responses = new ArrayList<BookResponse>();

        List<BookEntity> books = (List<BookEntity>) bookRepository.findAll();

        for (BookEntity book : books) {
            responses.add(BookResponse.from(book));
        }
        return responses;
    }
}
