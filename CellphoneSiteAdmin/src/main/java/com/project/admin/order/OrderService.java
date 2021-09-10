package com.project.admin.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.models.entities.Order;

@Service
public class OrderService {
	
	public static int PAGE_SIZE = 10;
	
	@Autowired
	private OrderRepository orderRepo;
	
	public List<Order> getAllOrders() {
		return orderRepo.findAll();
	}
	
	public Order getOrderById (Integer id) {
		return orderRepo.getOrderById(id);
	}
	
	public void deleteOrder (Integer id) {
		orderRepo.deleteById(id);
	}
	
	public void saveOrder(Order order) {
		orderRepo.save(order);
	}
}
