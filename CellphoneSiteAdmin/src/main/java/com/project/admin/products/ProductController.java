package com.project.admin.products;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.project.admin.brand.BrandService;
import com.project.admin.category.CategoryService;
import com.project.admin.product.images.ProductImageService;
import com.project.admin.users.UserService;
import com.project.library.helpers.FileUploadHelper;
import com.project.models.entities.Brand;
import com.project.models.entities.Category;
import com.project.models.entities.Product;
import com.project.models.entities.ProductImage;
import com.project.models.entities.User;

@Controller
public class ProductController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/add_new_product")
	public String showAddProduct(Model model, Authentication auth) {
		
		Product product = new Product();
		List<Category> allCategory = categoryService.getAllCategory();
		List<Brand> allBrand = brandService.getAllBrand();
		User user = userService.getUserByUsername(auth.getName());
		
		model.addAttribute("user",user);
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
		
		product.setDateCreate(Date.valueOf(LocalDate.now()));		
		Product saveProduct = productService.addNewProduct(product);
		
		Set<ProductImage> images = new HashSet<>();	
		//System.out.println("add product: "+saveProduct.getProductId());
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
	
	@GetMapping("/list_product/{pageNum}")
	public String showListProduct(Model model, @PathVariable(name = "pageNum") int pageNum, Authentication auth,
									@Param("sortBy") String sortBy, @Param("sortDirection") String sortDirection) {
		String direction = "asc";
		if(sortDirection != null && sortDirection.equals("asc")) {
			direction = "desc";
		}
		if(sortBy == null) {
			sortBy = "productId";
		}
		
		Page<Product> page = productService.getAllProduct(pageNum, sortBy, direction);
		List<Product> AllProducts = page.getContent();
		User user = userService.getUserByUsername(auth.getName());
		
		model.addAttribute("user",user);
		model.addAttribute("AllProducts", AllProducts);
		model.addAttribute("direction", direction);
		model.addAttribute("sortBy", sortBy);
		
		int startCount = (pageNum - 1) * UserService.PAGE_SIZE + 1;
		int endCount = startCount + UserService.PAGE_SIZE - 1;
		
		if(endCount > page.getTotalElements()) {
			endCount = (int) page.getTotalElements();
		}
		
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("curentPage", pageNum);
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		
		return "list_product";
	}
	
	@GetMapping("/list_product")
	public String showListProduct(Model model, Authentication auth) {
		
		return showListProduct(model, 1, auth, null, null);
	}
	
	@RequestMapping("/edit_product/{id}")
	public ModelAndView editProduct(@PathVariable(name="id") Integer id, Authentication auth) {
		Product product = productService.getProductById(id);
		List<Category> allCategory = categoryService.getAllCategory();
		List<Brand> allBrand = brandService.getAllBrand();
		List<ProductImage> allPhotos = product.getPhotoName();
		User user = userService.getUserByUsername(auth.getName());
		
		ModelAndView modelAndView = new ModelAndView("edit_product");
		modelAndView.addObject("user",user);
		modelAndView.addObject("product", product);
		modelAndView.addObject("allBrand", allBrand);
		modelAndView.addObject("allCategory", allCategory);
		modelAndView.addObject("allPhotos", allPhotos);
		modelAndView.addObject("amountPhotos", allPhotos.size());
		return modelAndView;
	}
	
	@RequestMapping(value="/edit_product", method = RequestMethod.POST)
	public String doEditProduct(@ModelAttribute("product") Product product, 
								@RequestParam("productImage") MultipartFile[] multipartFiles) {
		
		product.setDateEdit(Date.valueOf(LocalDate.now()));	
		Set<ProductImage> images =  new HashSet<>();	
		//System.out.println("add product: "+saveProduct.getProductId());
		for (MultipartFile multipartFile : multipartFiles) {
			if(!multipartFile.isEmpty()) {
				String photoName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
				ProductImage image = new ProductImage();
				image.setFileName(photoName);
				image.setProduct(product);
				images.add(imageService.saveImage(image));
			}			
		}
		
		product.setImage(images);
		productService.saveProduct(product);
		String uploadDir = "product-images/"+product.getProductId();
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
					
		return "redirect:/list_product";
	}
	
	@GetMapping("delete_product/{id}")
	public String deleteUser(@PathVariable(name = "id") Integer id) {
		productService.deleteProduct(id);
		return "redirect:/list_product";
	}
}
