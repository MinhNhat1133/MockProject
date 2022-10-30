package com.vti.repositories;

import com.vti.entities.OrderServiceId;
import com.vti.entities.Service;
import com.vti.entities.ServiceCompletionProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ServiceCompletionRepository extends JpaRepository<ServiceCompletionProgress, OrderServiceId>, JpaSpecificationExecutor<ServiceCompletionProgress> {
//    List<Service> findById(int orderId);

    @Query(value = "FROM ServiceCompletionProgress scp WHERE scp.id.orderId=:orderId")
    List<ServiceCompletionProgress> findServicesByOrderId(Integer orderId);

    @Modifying
    @Query(value = "INSERT INTO service_completion_progress(order_id, service_id, proposed_date) VALUES (:orderId, :serviceId, :proposedDate)", nativeQuery = true)
    void addOrderAndService(Integer orderId, Integer serviceId, LocalDate proposedDate);

    @Modifying
   	@Transactional
   	@Query(value =" UPDATE service_completion_progress SET `status` = 1, completion_date = SYSDATE() where order_id = :id and service_id IN (:ids)", nativeQuery = true)
	Integer setServiceStatusIsCompleteByOrderIdAndServiceIds(@Param("id")int id,@Param("ids") List<Integer> ids);

    



//	
//	@Modifying
//	@Transactional
//	@Query("DELETE FROM Account WHERE id IN(:ids)")
//	void deleteByIds(@Param("ids") List<Integer> ids);
}
