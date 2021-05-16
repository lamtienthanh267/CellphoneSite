package com.project.CellphoneSite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.project.CellphoneSite.daos.UserRepository;
import com.project.CellphoneSite.helper.AppString;
import com.project.CellphoneSite.models.User;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.getUserByUsername(username);
		
		if(user == null) {
			
			throw new UsernameNotFoundException(AppString.usernameNotFound);
		}
		
		return null;
	}

}
