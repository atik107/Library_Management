package com.example.demo.application.error.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public enum ErrorCode {

    BOOK_NOT_FOUND("001", "Book not found"),
    NO_BOOK("002", "No copies available"),
    NO_USER_FOUND("003", "No user found"),
    NO_RENT_LIMIT("004", "User cannot rent more than 3 books at the same time."),
    NO_RENT_RECORD("005", "No rent record found for userId and BookId"),

    ;

    final String code;
    final String message;
}
