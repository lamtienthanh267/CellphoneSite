package com.project.CellphoneSite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@RequestMapping("/register")
	public String showRegisterPage() {
		return "register";
	}
}
