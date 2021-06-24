package com.project.admin.users;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.project.admin.role.RoleService;
import com.project.admin.users.profile.UserProfileService;
import com.project.library.helpers.FileUploadHelper;
import com.project.models.entities.Role;
import com.project.models.entities.User;
import com.project.models.entities.UserProfile;


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
	public String doAddUser(@ModelAttribute("user") User user) {
		UserProfile profile = new UserProfile();
		profile.setUser(user);
		profile.setPhoto("user.png");
		user.setUserProfile(profile);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		
		if(userService.getUserByUsername(user.getUsername())==null) {

			userService.addUser(user);

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
	
	@RequestMapping("/edit_user/{id}")
	public ModelAndView doEditUser(@PathVariable(name="id") Integer id) {
		List<Role> allRole = roleService.getAllRole();
		User user = userService.getUserById(id);
		System.out.println("edit user: "+user.getUsername());
		ModelAndView modelAndView = new ModelAndView("edit_user");
		
		modelAndView.addObject("user",user);
		modelAndView.addObject("allRole",allRole);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit_user", method = RequestMethod.POST)
	public String editUser(@ModelAttribute("user") User user) {
		System.out.println("save user:"+user.getUsername());
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		userService.addUser(user);			
		return "redirect:/list_user";

	}
	
	@RequestMapping("delete_user/{id}")
	public String deleteUser(@PathVariable(name = "id") Integer id) {
		userService.deleteUser(id);
		return "redirect:/list_user";
	}
	
	@GetMapping("/user_profile")
	public String userProfile(Authentication auth ,Model model) {

		User user = userService.getUserByUsername(auth.getName());
		
		model.addAttribute("user",user);
		
		return "user_profile";
	}
	
	@RequestMapping(value="/user_profile", method = RequestMethod.POST)
	public String editUserProfile(@ModelAttribute("user") User user, @RequestParam("old_password") String oldPassword,
									@RequestParam("fileImage") MultipartFile multipartFile) {
		
		//System.out.println("user id: "+user.getUserId());
		String photoName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		user.getUserProfile().setPhoto(photoName);
		
		String uploadDirect = "profile-photos/";
		userService.addUser(user);	
		//save photo
		try {
			
			FileUploadHelper.saveFile(uploadDirect, photoName, multipartFile);
			
		}catch(IOException e){
			
			System.out.println("file upload: error! "+ e.getMessage());
		}
		
				
		return "redirect:/user_profile";
	}
	
	@RequestMapping(value="/change_password", method = RequestMethod.POST)
	public String changeUserPassword(@ModelAttribute("user") User user, @RequestParam("new_password") String newPassword){
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		
		System.out.println("user pass: "+user.getPassword());
		
		User userDB = userService.getUserById(user.getUserId());
		
		if(user.getPassword().equalsIgnoreCase(userDB.getPassword())) {
			user.setPassword(encoder.encode(newPassword));
			
			userService.addUser(user);
		}		
			
	
		return "redirect:/user_profile";
	}
}

