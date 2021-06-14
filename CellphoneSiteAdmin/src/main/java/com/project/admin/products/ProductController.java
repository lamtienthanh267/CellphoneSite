package com.project.admin.products;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.models.entities.Product;

@Controller
public class ProductController {
	
	@GetMapping("/add_new_product")
	public String showAddProduct(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		return "add_new_product";
	}
}
