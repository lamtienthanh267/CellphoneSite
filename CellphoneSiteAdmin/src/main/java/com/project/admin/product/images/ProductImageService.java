package com.project.admin.product.images;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.models.entities.ProductImage;

@Service
public class ProductImageService {
	
	@Autowired
	private ProductImageRepository repo;
	
	public ProductImage saveImage (ProductImage image) {
		 return repo.save(image);
	}
	
	public void deleteImage(Integer id) {
		repo.deleteById(id);
	}
	
	public ProductImage getProductImageById(Integer id) {
		return repo.getProductImageById(id);
	}
}
