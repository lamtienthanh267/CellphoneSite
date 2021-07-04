package com.project.admin.users;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.models.entities.User;

@Service
@Transactional
public class UserService {
	
	public static int PAGE_SIZE = 5;
	
	@Autowired
	private UserRepository repo;

	public List<User> getAllUser(){
		
		return repo.findAll();
	}
	
	public Page<User> getAllUser(int pageNum) {
		
		Pageable pageable;
		
		if(pageNum >= 1) {
			pageable = PageRequest.of(pageNum - 1, PAGE_SIZE);		
		}else {
			pageable = PageRequest.of(0, PAGE_SIZE);
		}
		
		return repo.findAll(pageable);
	}
	
	public Page<User> getAllUser(int pageNum, String sortBy, String sortDirection) {
		Sort sort = Sort.by(sortBy);
		
		if(sortDirection.equals("asc")) {
			sort = sort.ascending();
		}else {
			sort = sort.descending();
		}
		
		Pageable pageable;
		
		if(pageNum >= 1) {
			pageable = PageRequest.of(pageNum - 1, PAGE_SIZE, sort);		
		}else {
			pageable = PageRequest.of(0, PAGE_SIZE, sort);
		}
		
		return repo.findAll(pageable);
	}
	
	public User getUserById(Integer id) {
		return repo.getUserByUserId(id);
	}
	
	public User getUserByUsername(String username) {
		return repo.getUserByUsername(username);
	}
	
	public void addUser(User user) {
		repo.save(user);
		System.out.println("add user successfull");
	}
	
	public void deleteUser(Integer id) {
		repo.deleteById(id);
	}

}
