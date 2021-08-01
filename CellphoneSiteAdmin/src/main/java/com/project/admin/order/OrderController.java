package com.project.admin.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.admin.users.UserService;
import com.project.models.entities.Order;
import com.project.models.entities.User;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/order_list")
	public String showOrderList(Model model, Authentication auth) {
		List<Order> orders = orderService.getAllOrders();
		User user = userService.getUserByUsername(auth.getName());
		
		model.addAttribute("user",user);
		model.addAttribute("allOrders", orders);
		return "order_list";
	}
}
