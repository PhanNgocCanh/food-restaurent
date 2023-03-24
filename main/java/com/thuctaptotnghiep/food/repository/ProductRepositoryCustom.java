package com.thuctaptotnghiep.food.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thuctaptotnghiep.food.entity.Category;
import com.thuctaptotnghiep.food.entity.Origin;
import com.thuctaptotnghiep.food.entity.Product;

public interface ProductRepositoryCustom {
	Page<Product> getByCategoryAndOriginWithPage(Category category,Origin origin,Pageable  pageable);
}
