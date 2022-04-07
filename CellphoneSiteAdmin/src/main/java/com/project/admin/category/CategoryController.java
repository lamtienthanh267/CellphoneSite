package com.project.admin.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.admin.users.UserService;
import com.project.models.entities.Category;
import com.project.models.entities.User;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/add_category")
	public String addNewCategory(Model model, Authentication auth) {
		User user = userService.getUserByUsername(auth.getName());
		Category category = new Category();
		List<Category> allCategory = categoryService.getAllCategory();
		
		model.addAttribute("user", user);
		model.addAttribute("category", category);
		model.addAttribute("allCategory", allCategory);
		return "add_category";
	}
	
	@RequestMapping(value = "/add_category", method = RequestMethod.POST)
	public String saveCategory(@ModelAttribute ("category") Category category) {
		categoryService.saveCategory(category);
		return "redirect:/add_category";
	}
}
