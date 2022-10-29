package com.vti.repositories;

import com.vti.entities.OrderServiceId;
import com.vti.entities.Service;
import com.vti.entities.ServiceCompletionProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceCompletionRepository extends JpaRepository<ServiceCompletionProgress, OrderServiceId>, JpaSpecificationExecutor<ServiceCompletionProgress> {
//    List<Service> findById(int orderId);

    @Query(value = "FROM ServiceCompletionProgress scp WHERE scp.id.orderId=:orderId")
    List<ServiceCompletionProgress> findServicesByOrderId(Integer orderId);

}
