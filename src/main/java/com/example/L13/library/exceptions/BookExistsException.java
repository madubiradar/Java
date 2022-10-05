package com.example.L13.library.exceptions;

public class BookExistsException extends RuntimeException {

    public BookExistsException(String book_already_exists) {
        super(book_already_exists);
    }
}
