package com.vti.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vti.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {

	@Query(value = "SELECT o.* FROM orders o WHERE o.customer_id = :id AND o.status < 100 ", nativeQuery = true)
	Order findActiveOrderByCustomerId(@Param("id") int id);

//	@Modifying
	@Query(value = "SELECT sum(s.service_weight) FROM services s JOIN service_completion_progress scp ON scp.service_id = s.id where order_id= :id ", nativeQuery = true)
	float getTotalWeightServiceByOrderId(@Param("id") int id);

	@Query(value = "SELECT sum(s.service_weight) FROM services s JOIN service_completion_progress scp ON scp.service_id = s.id where order_id= :id and `status` = 1", nativeQuery = true)
	float getWeightServiceCompleteByOrderId(int id);

	@Modifying
	@Transactional
	@Query(value = " UPDATE orders SET `status` = :newStatus WHERE id = :orderId", nativeQuery = true)
	void UpdateStatus(int orderId, int newStatus);

//    @Modifying
//    @Query("UPDATE Order o SET o.payment_status = :paymentStatus WHERE o.id= :id")
//    void updatePaymentByOrderId(@Param("id") int id, @Param("paymentStatus") int paymentStatus);

//    @Modifying
//    @Query("UPDATE Order o SET o.payment_status = :paymentStatus WHERE o.id= :id")
//	void UpdateStatus();

}
