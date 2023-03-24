package com.thuctaptotnghiep.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thuctaptotnghiep.food.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, String>{
	Category findOneByCategoryCode(String categoryCode);
}
