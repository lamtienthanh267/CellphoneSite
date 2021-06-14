package com.project.admin.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.admin.role.RoleService;
import com.project.models.entities.Role;
import com.project.models.entities.User;


@Controller
public class UserController {
	
	@RequestMapping("/")
	public String showHomePage() {
		return "index";
	}
	
	@GetMapping("/login_user")
	public String showLoginPage() {
		return "login_user";
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
	
	@RequestMapping("edit_user/{id}")
	public ModelAndView doEditUser(@PathVariable(name="id") Integer id) {
		
		User user = userService.getUserById(id);
		ModelAndView modelAndView = new ModelAndView("edit_user");
		modelAndView.addObject(user);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit_user", method = RequestMethod.POST)
	public String doEditUser(@ModelAttribute("user") User userEdit) {
		System.out.println(userEdit.getUsername());
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userEdit.setPassword(encoder.encode(userEdit.getPassword()));
		userService.addUser(userEdit);			
		return "redirect:/list_user";

	}
	
	@RequestMapping("delete_user/{id}")
	public String deleteUser(@PathVariable(name = "id") Integer id) {
		userService.deleteUser(id);
		return "redirect:/list_user";
	}
}

