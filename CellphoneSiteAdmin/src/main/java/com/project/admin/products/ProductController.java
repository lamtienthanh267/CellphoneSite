package com.project.admin.products;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.admin.brand.BrandService;
import com.project.admin.category.CategoryService;
import com.project.admin.product.images.ProductImageService;
import com.project.library.helpers.FileUploadHelper;
import com.project.models.entities.Brand;
import com.project.models.entities.Category;
import com.project.models.entities.Product;
import com.project.models.entities.ProductImage;

@Controller
public class ProductController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private BrandService brandService;
	
	@GetMapping("/add_new_product")
	public String showAddProduct(Model model) {
		
		Product product = new Product();
		List<Category> allCategory = categoryService.getAllCategory();
		List<Brand> allBrand = brandService.getAllBrand();
		
		model.addAttribute("allBrand", allBrand);
		model.addAttribute("allCategory", allCategory);
		model.addAttribute("product", product);
		return "add_new_product";
	}
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductImageService imageService;
	
	@PostMapping("/add_new_product")
	public String saveProduct(@ModelAttribute("product") Product product,
								@RequestParam("productImage") MultipartFile[] multipartFiles) {
		Set<ProductImage> images = new HashSet<>();
		product.setDateCreate(Date.valueOf(LocalDate.now()));
		
		Product saveProduct = productService.addNewProduct(product);
		System.out.println("add product: "+saveProduct.getProductId());
		for (MultipartFile multipartFile : multipartFiles) {
			if(!multipartFile.isEmpty()) {
				String photoName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
				ProductImage image = new ProductImage();
				image.setFileName(photoName);
				image.setProduct(saveProduct);
				images.add(imageService.saveImage(image));
			}			
		}
		
		saveProduct.setImage(images);
		
		String uploadDir = "product-images/"+saveProduct.getProductId();
		for (MultipartFile multipartFile : multipartFiles) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			// save photo
			if(!multipartFile.isEmpty()) {
				try {
	
					FileUploadHelper.saveFile(uploadDir, fileName, multipartFile);
	
				} catch (IOException e) {
	
					System.out.println("file upload: error! " + e.getMessage());
				}
			}

		}

		return "redirect:/add_new_product";
	}
}
