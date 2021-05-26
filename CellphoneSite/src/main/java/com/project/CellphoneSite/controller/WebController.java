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
public class WebController {
	
	@RequestMapping("/")
	public String showHomePage() {
		return "index";
	}
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}
	
	@GetMapping("/login_error")
	public String showLogin_Error() {
		return "login_error";
	}
	
	@GetMapping("/403")
	public String show403() {
		return "403";
	}
	
	@GetMapping("/user_profile")
	public String showUserProfilePage() {
		return "user_profile";
	}
	
//	@GetMapping("/register")
//	public String showRegisterPage(Model model) {
//		
//		User user = new User();
//		model.addAttribute("user",user);
//		System.out.println("get model user done");
//		return "register";
//	}
//	
//	@Autowired
//	private UserService userService;
//	
//	//@PostMapping("/register")
//	@RequestMapping(value="/register", method = RequestMethod.POST)
//	public String doRegister(@ModelAttribute("user") User userRegister) {
//		System.out.println("register doing");
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		userRegister.setPassword(encoder.encode(userRegister.getPassword()));
//		userRegister.setEnabled(true);
//		
//		if(userService.getUserByUsername(userRegister.getUsername())==null) {
//			
//			userService.addUser(userRegister);			
//			return "redirect:/login";
//		}
//		
//		return "403";
//	}
}
