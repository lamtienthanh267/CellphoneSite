package com.project.admin.brand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.models.entities.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

}
