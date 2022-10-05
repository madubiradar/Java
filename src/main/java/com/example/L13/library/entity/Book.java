package com.example.L13.library.entity;


import com.example.L13.library.dto.BookStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
//@Table(indexes = @Index(name = "uniqueISBN", columnList = "isbn") )
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String isbn;

    private double cost;

    @Enumerated(value = EnumType.STRING)
    BookStatus bookStatus;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Author author;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "associatedBook")
    List<Orders> ordersList;

    @CreationTimestamp
    LocalDateTime creationDate;

    @UpdateTimestamp
    LocalDateTime updateTimeStamp;

    @PrePersist
    public void prePersistCheck(){
        if(Objects.isNull(this.bookStatus)){
            this.bookStatus = BookStatus.AVAILABLE;

        }
    }
}
