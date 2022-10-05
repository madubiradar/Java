package com.example.L13.library.repository;

import com.example.L13.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book,Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM Book b WHERE b.isbn = ?1")
    public Book findByIsbn(String isbn);
}
