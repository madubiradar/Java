package com.example.L13.library.repository;

import com.example.L13.library.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders,Integer> {


    Optional<Orders> findByOrderReference(String orderReference);

//    @Query(nativeQuery = true, value = "select * from order where orderReferenceId = orderRef")
//    Optional<Order> findOrdersByReference(@Param("orderRef") String orderReferenceId);
//
//    @Query(nativeQuery = false, value = "select o from order o where o.orderReferenceId=:orderRef")
//    Optional<Order> findOrderByReference(@Param("orderRef") String orderRef);
}
