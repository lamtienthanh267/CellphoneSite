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
}
