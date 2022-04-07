package com.project.admin.orderdetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.models.entities.OrderDetails;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer>{
	@Query("SELECT ord FROM OrderDetails ord WHERE ord.order.id = ?1")
	public OrderDetails getOrderDetailsByOrderId(Integer orderId);
}
