package com.project.admin.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.admin.users.UserService;
import com.project.appenum.StatusOrder;
import com.project.models.entities.Order;
import com.project.models.entities.User;

@Controller
public class OrderController {
	
	private StatusOrder statusOrder;

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
	
	@GetMapping("/edit_order/{id}")
	public ModelAndView showOrder(@PathVariable(name="id") Integer id, Authentication auth) {
		Order order = orderService.getOrderById(id);
		User user = userService.getUserByUsername(auth.getName());
		
		ModelAndView modelAndView = new ModelAndView("edit_order");
		modelAndView.addObject("user",user);
		modelAndView.addObject("order", order);
		modelAndView.addObject("statusOrder",statusOrder);
		
		return modelAndView;
	}
	
	@PostMapping("/edit_order")
	public String editOrder(@ModelAttribute("order") Order order, Authentication auth) {
		System.out.println("role: "+auth.getAuthorities());
		
		orderService.saveOrder(order);
		return "redirect:/order_list";
	}
}
