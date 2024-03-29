package com.project.CellphoneSite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.CellphoneSite.models.Role;
import com.project.CellphoneSite.models.User;
import com.project.CellphoneSite.service.RoleService;
import com.project.CellphoneSite.service.UserService;

@Controller
public class UserController {
	
	@GetMapping("/management_page_master")
	public String managerPageView() {
		
		return "management_page_master";
	}
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/add_user")
	public String addUserView(Model model) {
		User user = new User();
		List<Role> allRole = roleService.getAllRole();
		model.addAttribute("user",user);
		model.addAttribute("allRole", allRole);
		return "add_user";
	}
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/add_user", method = RequestMethod.POST)
	public String doRegister(@ModelAttribute("user") User userRegister) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userRegister.setPassword(encoder.encode(userRegister.getPassword()));
		
		if(userService.getUserByUsername(userRegister.getUsername())==null) {
			for(Role r :userRegister.getRole()) {
				System.out.println(r.getRole_name());
			}
			userService.addUser(userRegister);			
			return "redirect:/list_user";
		}
		
		return "403";
	}
	
	@GetMapping("/list_user")
	public String showListUser(Model model) {
		List<User> allUser = userService.getAllUser();
		model.addAttribute("allUser", allUser);
		return "list_user";
	}
	
	@GetMapping("/edit_user")
	public String editUserView(Model model) {
		User user = new User();
		//List<Role> allRole = roleService.getAllRole();
		model.addAttribute("user",user);
		//model.addAttribute("allRole", allRole);
		return "add_user";
	}
}
