package com.project.admin.product.images;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.models.entities.ProductImage;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
	@Query("SELECT image FROM ProductImage image WHERE image.id = ?1")
	public ProductImage getProductImageById(@Param("id") Integer id);
}
