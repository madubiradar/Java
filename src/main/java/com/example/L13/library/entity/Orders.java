package com.example.L13.library.entity;


import com.example.L13.library.dto.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    /**
     * whenever you are trying to update the same entity version would increment
     *
     * --> update order set transactionType= ? and version++ where id = ? and version = ?
     *
     *  This helps in locking
     *      (Optimistic lock and pessimistic lock)
     *
     */
    @Version
    long version;

    /**
     *
     *  order entity
     *
     */
    String orderReference;
    double amount;

    @Enumerated(value = EnumType.STRING)
    OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    User userinfo;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    Book associatedBook;



//    @ManyToOne
//    private User user;

}
