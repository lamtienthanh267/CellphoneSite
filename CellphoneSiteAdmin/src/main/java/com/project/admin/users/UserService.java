package com.project.admin.users;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.models.entities.User;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository repo;

	public List<User> getAllUser(){
		return repo.findAll();
	}
	
	public User getUserByUsername(String username) {
		return repo.getUserByUsername(username);
	}
	
	public void addUser(User user) {
	
		repo.save(user);
		System.out.println("register successfull");
	}
}
