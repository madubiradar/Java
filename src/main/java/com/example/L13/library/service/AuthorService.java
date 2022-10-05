package com.example.L13.library.service;


import com.example.L13.library.entity.Author;
import com.example.L13.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {


    @Autowired
    AuthorRepository authorRepository;

    public Author saveOrUpdate(Author author){
        return authorRepository.save(author);
    }


    public Optional<Author> findByEmail(Author author){
        return Optional.ofNullable(authorRepository.findByEmail(author.getEmail()));
    }
}
