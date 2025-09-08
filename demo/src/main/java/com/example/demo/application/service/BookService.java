package com.example.demo.application.service;

import com.example.demo.api.payload.request.BookRequest;
import com.example.demo.api.payload.response.BookResponse;

import java.util.List;

public interface BookService {

    BookResponse createBook(BookRequest request);

    BookResponse getBook(long id);

    BookResponse updateBook(long id, BookRequest updatedBookEntity);

    BookResponse deleteBook(long id);

    List<BookResponse> getAllBook();
}
