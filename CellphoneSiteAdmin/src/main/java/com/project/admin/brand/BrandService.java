package com.project.admin.brand;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.models.entities.Brand;

@Service
public class BrandService {
	
	@Autowired
	private BrandRepository repo;
	
	public List<Brand> getAllBrand(){
		return repo.findAll();
	}
}
