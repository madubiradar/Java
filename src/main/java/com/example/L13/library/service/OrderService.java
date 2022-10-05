package com.example.L13.library.service;

import com.example.L13.library.config.UserConfig;
import com.example.L13.library.dto.BookStatus;
import com.example.L13.library.dto.CreateOrderDTO;
import com.example.L13.library.dto.OrderStatus;
import com.example.L13.library.dto.UpdateOrderDTO;

import com.example.L13.library.entity.Book;
import com.example.L13.library.entity.Orders;
import com.example.L13.library.entity.User;
import com.example.L13.library.exceptions.*;
import com.example.L13.library.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @Autowired
    UserConfig userConfig;



    public Orders createOrder(CreateOrderDTO orderRequestDto){
        return saveOrUpdate(orderRequestDto.toOrder());
    }


    public Orders saveOrUpdate(Orders order){
        return orderRepository.save(order);
    }

    public Orders updateOrder(UpdateOrderDTO order){
        Optional<Orders> byOrderReference = orderRepository.findByOrderReference(order.getOrderReference());
        if(byOrderReference.isEmpty()){
            throw new OrderNotFoundException("Order Not found Exception");
        }

        Orders existingOrder = byOrderReference.get();
        existingOrder.setOrderStatus(OrderStatus.valueOf(order.getOrderStatus()));
        existingOrder.setAmount(order.getAmount());

        return saveOrUpdate(existingOrder);
    }


    public Optional<Orders> fetchOrderById(String orderReferenceId){
        return orderRepository.findByOrderReference(orderReferenceId);
    }

    public Orders placeOrder(Integer userId, Integer bookId ){


        //Issue book to user, validate user if exist


            Optional<User> userInfo = userService.fetchOneById(userId);
            if(userInfo.isEmpty()){
                throw new UserNotFoundException("User Not found Exception");
            }




        //validate book, if book exist and also not issued to anyone
        Optional<Book> existingBook = bookService.FindById(bookId);

        if(existingBook.isEmpty()){
            throw new BookNotFoundException("Book not found");

        }

        Book catalogueBook= existingBook.get();

        if(catalogueBook.getBookStatus() == BookStatus.UNAVAILABLE){
            throw new BookNotFoundException("book not available");
        }

        //check if student has reached max quota for issuing books

        User existingUser =  userInfo.get();

        if(existingUser.getOrdersList().size() >= 10){
            throw new QuotaExceededException("User quota has exceeded 10 orders");
        }
        //order with pending state

        Orders orders = Orders.builder()
                        .amount(catalogueBook.getCost())
                                .orderStatus(OrderStatus.PENDING)
                                        .orderReference(UUID.randomUUID().toString())
                                                .userinfo(existingUser)

                                                    .build();

        try{
            saveOrUpdate(orders);
            //mark book unavailable and issue that to user

            catalogueBook.setBookStatus(BookStatus.UNAVAILABLE);
            catalogueBook.setUser(existingUser);

            bookService.saveOrUpdate(catalogueBook);

            // will update order status to success & save it

            orders.setOrderStatus(OrderStatus.SUCCESS);
            saveOrUpdate(orders);

        } catch(Exception ex){
            //roll back
            catalogueBook.setBookStatus(BookStatus.AVAILABLE);
            catalogueBook.setUser(null);

            bookService.saveOrUpdate(catalogueBook);

            if(Objects.nonNull(orders.getId())){
                orders.setOrderStatus(OrderStatus.FAILED);
                saveOrUpdate(orders);
            }

        }
        return orders;
    }


}
