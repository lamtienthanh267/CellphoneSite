package com.project.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	
	private String desciption;
	
	@Column(name="descrip_details")
	private String descriptionDetails;
	
	@Column(name="best_sale")
	private Boolean bestSale;
	
	private Boolean featured;
	
	private Boolean newest;
	
	private Float price;
	
	@Column(name="price_competitive")
	private Float priceCompetitive;
	
	private String image;
	
	@Column(name="total_amount")
	private Integer totalAmount;
	
	@Column(name="brand_id")
	private Integer brandId;
	
	@Column(name="product_type_id")
	private Integer productTypeId;

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

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public Integer getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}
	
	
}
