package com.project.admin.products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.models.entities.Product;

@Service
public class ProductService {
	
	public static int PAGE_SIZE = 10;
	@Autowired
	private ProductRepository productRepository;
	
	public Product addNewProduct(Product product) {
		return productRepository.save(product);
	}
	
	public void saveProduct(Product product) {
		productRepository.save(product);
	}
	
	public Product getProductById(Integer id) {
		return productRepository.getProductById(id);
	}
	
	public List<Product> getAllProduct(){
		return productRepository.findAll();
	}
	
	public Page<Product> getAllProduct(int pageNum, String sortBy, String sortDirection){
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
		
		return productRepository.findAll(pageable);
	}
	
	public void deleteProduct(Integer id) {
		productRepository.deleteById(id);
	}
}
