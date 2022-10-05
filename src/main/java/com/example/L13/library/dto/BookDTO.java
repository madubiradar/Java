package com.example.L13.library.dto;


import com.example.L13.library.entity.Author;
import com.example.L13.library.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.UUID;

@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    @NotBlank(message = "name can not be blank")
    private String name;

    @Positive(message = "cost can not be negative value")
    private double cost;

    @NotBlank
    private String authorName;

    @NotBlank
    @Email
    private String authorEmail;

    public Book toBook(){
        Author author = Author.builder()
                .name(authorName)
               .email(authorEmail).build();


        return Book.builder()
                .cost(cost)
                .isbn(UUID.randomUUID().toString())
                .name(name)
                .bookStatus(BookStatus.AVAILABLE)
                .author(author)
                .build();
    }
}
