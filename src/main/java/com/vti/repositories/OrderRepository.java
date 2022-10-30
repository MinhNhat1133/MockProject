package com.vti.repositories;

import com.vti.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vti.entities.Order;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {

    @Query(value="SELECT o.* FROM orders o WHERE o.customer_id = :id AND o.status < 100 ",nativeQuery = true)
    Order findActiveOrderByCustomerId(@Param("id") int id);

    @Modifying
    @Query("UPDATE Order o SET o.payment_status = :paymentStatus WHERE o.id= :id")
    void updatePaymentByOrderId(@Param("id") int id, @Param("paymentStatus") int paymentStatus);



}
