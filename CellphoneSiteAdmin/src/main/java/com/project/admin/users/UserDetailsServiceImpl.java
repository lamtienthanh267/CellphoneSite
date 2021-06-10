package com.project.admin.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.project.admin.security.MyUserDetails;
import com.project.library.helpers.AppString;
import com.project.models.entities.Role;
import com.project.models.entities.User;


public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.getUserByUsername(username);
		
		if(user == null) {
			
			throw new UsernameNotFoundException(AppString.usernameNotFound);
		}
		
		for(Role role : user.getRole()) {
			System.out.println("user: "+user.getUsername()+ " has role "+role.getRole_name());
		}
		
		return new MyUserDetails(user);
	}

}
