package com.example.L13.library.service;

import com.example.L13.library.dto.BookDTO;
import com.example.L13.library.entity.Author;
import com.example.L13.library.entity.Book;
import com.example.L13.library.exceptions.BookExistsException;
import com.example.L13.library.exceptions.ConnectionErrorException;
import com.example.L13.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorService authorService;

    public Book createBook(BookDTO bookDTO) {

        Book book = bookDTO.toBook();
//        /*
//            check if book already exist
//         */
//
//        Optional<Book> existingBook = Optional.ofNullable(bookRepository.findByIsbn(book.getIsbn()));
//
//        if(existingBook.isPresent()){
//            throw new BookExistsException("Book Already exists");
//        }
//        /*
//            check if author already exist
//         */
        try {
            Optional<Author> existingAuthor = authorService.findByEmail(book.getAuthor());
            if(existingAuthor.isEmpty()) {
                Author author = authorService.saveOrUpdate(book.getAuthor());
                book.setAuthor(author);
            } else {
                book.setAuthor(existingAuthor.get());
            }
        } catch (Exception e){
            throw new ConnectionErrorException();
        }

        return saveOrUpdate(book);
    }


    public Book saveOrUpdate(Book book){
        return bookRepository.save(book);
    }

    public List<Book> fetchAllBooks() {

        return bookRepository.findAll();
    }

    public Book updateBook(BookDTO bookDTO){
        Book book = bookDTO.toBook();
        return saveOrUpdate(book);
    }

    public Optional<Book> FindById(Integer bookId) {
        return bookRepository.findById(bookId);
    }
}
