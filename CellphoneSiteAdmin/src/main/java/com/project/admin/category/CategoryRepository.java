package com.project.admin.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.models.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
