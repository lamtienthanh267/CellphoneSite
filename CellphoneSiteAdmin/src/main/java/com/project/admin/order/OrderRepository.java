package com.project.admin.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.models.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	@Query("SELECT ord FROM Order ord WHERE ord.id = :orderId")
	public Order getOrderById(@Param("orderId") Integer orderId);
}
