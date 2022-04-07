package com.project.admin.orderdetails;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.models.entities.OrderDetails;

@Service
@Transactional
public class OrderDetailsService {
	
	@Autowired
	private OrderDetailsRepository orderDetailsRepository;
	
	public OrderDetails getOrderDetailsByOrderId(Integer orderId) {
		return orderDetailsRepository.getOrderDetailsByOrderId(orderId);
	}
}
