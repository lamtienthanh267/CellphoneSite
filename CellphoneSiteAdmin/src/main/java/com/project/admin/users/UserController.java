package com.project.admin.users;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.admin.role.RoleService;
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
	
//	@GetMapping("/nav_fragment")
//	public String managerPageView(Authentication auth ,Model model) {
//		User user = userService.getUserByUsername(auth.getName());
//		
//		model.addAttribute("user",user);
//		return "nav_fragment";
//	}
	
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
	
//	@GetMapping("/list_user")
//	public String showListUser(Model model) {
//		List<User> allUser = userService.getAllUser();
//		model.addAttribute("allUser", allUser);
//		return "list_user";
//	}
	
	@GetMapping("/list_user")
	public String showListUser(Model model) {
		return showListUser(model, 1, null, null);
	}
	
	@GetMapping("/list_user/{pageNum}")
	public String showListUser(Model model, @PathVariable(name = "pageNum") int pageNum,
								@Param("sortBy") String sortBy, @Param("sortDirection") String sortDirection) {
		String direction = "asc";
		if(sortDirection != null && sortDirection.equals("asc")) {
			direction = "desc";
		}
		if(sortBy == null) {
			sortBy = "userId";
		}
		
		Page<User> page = userService.getAllUser(pageNum, sortBy, direction);
		List<User> allUser = page.getContent();
		
		int startCount = (pageNum - 1) * UserService.PAGE_SIZE + 1;
		int endCount = startCount + UserService.PAGE_SIZE - 1;
		
		if(endCount > page.getTotalElements()) {
			endCount = (int) page.getTotalElements();
		}
		
		model.addAttribute("allUser", allUser);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("curentPage", pageNum);
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		//sort
		model.addAttribute("direction", direction);
		model.addAttribute("sortBy", sortBy);
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
	public String editUserProfile(@ModelAttribute("user") User user,
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
	public String changeUserPassword(@ModelAttribute("user") User user,
										RedirectAttributes redirectAttributes,
										@RequestParam("new_password") String newPassword){
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		System.out.println("user pass: "+user.getPassword());
		System.out.println("user pass encode: "+encoder.encode(user.getPassword()));
		
		User userDB = userService.getUserById(user.getUserId());
		System.out.println("user db: "+userDB.getPassword());
		if(encoder.matches(user.getPassword(), userDB.getPassword())) {
			user.setPassword(encoder.encode(newPassword));
			
			userService.addUser(user);
			System.out.println("doi password thanh cong ");

		}else {
			redirectAttributes.addFlashAttribute("error","doi password khong thanh cong");
		
			System.out.println("doi password khong thanh cong ");
			return "redirect:/user_profile/";
		}		
			
	
		return "redirect:/user_profile";
	}
}

