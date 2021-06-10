package com.project.admin.role;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.models.entities.Role;


@Service
@Transactional
public class RoleService {
	@Autowired
	RoleRepository roleRepository;
	
	public List<Role> getAllRole(){
		return roleRepository.findAll();
	}
	
	public Role getRoleById(Integer id){
		return roleRepository.getRoleById(id);
	}
}
