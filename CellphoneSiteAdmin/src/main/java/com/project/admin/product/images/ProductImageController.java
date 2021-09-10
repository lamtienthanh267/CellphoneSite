package com.project.admin.product.images;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.models.entities.ProductImage;

@Controller
public class ProductImageController {
	
	@Autowired
	private ProductImageService productImageService;
	
	@GetMapping("/delete_proImage/{id}")
	public String deleteProImage(@PathVariable(name="id") Integer id) {
		ProductImage proImage = productImageService.getProductImageById(id);
		Integer productId = proImage.getProduct().getProductId();
		
		productImageService.deleteImage(id);
	
		return "redirect:/edit_product/"+productId;
	}
}
