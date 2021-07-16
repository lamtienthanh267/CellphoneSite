package com.project.admin.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.models.entities.Product;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public Product addNewProduct(Product product) {
		return productRepository.save(product);
	}
	
	public Product getProductById(Integer id) {
		return productRepository.getById(id);
	}
}
