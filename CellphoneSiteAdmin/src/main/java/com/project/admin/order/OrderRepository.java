package com.project.admin.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.models.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
