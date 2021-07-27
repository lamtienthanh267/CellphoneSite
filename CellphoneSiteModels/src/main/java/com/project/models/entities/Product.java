package com.project.models.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.transaction.Transactional;

@Entity
public class Product {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private Integer productId;
	
	@Column(name = "name")
	private String productName;
	
	@Column(name="code")
	private String productCode;
	
	private String description;
	
	@Column(name="descrip_details")
	private String descriptionDetails;
	
	@Column(name="best_sale")
	private Boolean bestSale;
	
	private Boolean featured;
	
	private Boolean newest;
	
	@Column(name="date_create")
	private Date dateCreate;
	
	@Column(name="date_edit")
	private Date dateEdit;
	
	private Float price;
	
	@Column(name="price_competitive")
	private Float priceCompetitive;
	
	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
	private Set<ProductImage> image;
	
	@Column(name="total_amount")
	private Integer totalAmount;
	
	@ManyToOne
	@JoinColumn(name="brand_id")
	private Brand brand;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescriptionDetails() {
		return descriptionDetails;
	}

	public void setDescriptionDetails(String descriptionDetails) {
		this.descriptionDetails = descriptionDetails;
	}

	public Boolean getBestSale() {
		return bestSale;
	}

	public void setBestSale(Boolean bestSale) {
		this.bestSale = bestSale;
	}

	public Boolean getFeatured() {
		return featured;
	}

	public void setFeatured(Boolean featured) {
		this.featured = featured;
	}

	public Boolean getNewest() {
		return newest;
	}

	public void setNewest(Boolean newest) {
		this.newest = newest;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getPriceCompetitive() {
		return priceCompetitive;
	}

	public void setPriceCompetitive(Float priceCompetitive) {
		this.priceCompetitive = priceCompetitive;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Date getDateEdit() {
		return dateEdit;
	}

	public void setDateEdit(Date dateEdit) {
		this.dateEdit = dateEdit;
	}

	public Set<ProductImage> getImage() {
		return image;
	}

	public void setImage(Set<ProductImage> image) {
		this.image = image;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
	@Transactional
	public List<ProductImage> getPhotoName(){
		List<ProductImage> list = new ArrayList<>();
		for (ProductImage productImage : image) {
			list.add(productImage);
		}
		return list;
	}
}
