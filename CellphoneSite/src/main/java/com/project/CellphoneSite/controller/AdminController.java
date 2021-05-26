package com.project.CellphoneSite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.CellphoneSite.models.User;
import com.project.CellphoneSite.service.UserService;

@Controller
public class AdminController {
	
	@GetMapping("/management_page_master")
	public String managerPageView() {
		
		return "management_page_master";
	}
	
	@GetMapping("/add_user")
	public String addUserView(Model model) {
		User user = new User();
		model.addAttribute("user",user);
		return "add_user";
	}
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/add_user", method = RequestMethod.POST)
	public String doRegister(@ModelAttribute("user") User userRegister) {
		System.out.println("register doing");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userRegister.setPassword(encoder.encode(userRegister.getPassword()));
		userRegister.setEnabled(true);
		
		if(userService.getUserByUsername(userRegister.getUsername())==null) {
			
			userService.addUser(userRegister);			
			return "redirect:/management_page_master";
		}
		
		return "403";
	}
}
