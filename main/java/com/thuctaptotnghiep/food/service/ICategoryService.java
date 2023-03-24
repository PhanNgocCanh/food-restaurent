package com.thuctaptotnghiep.food.service;

import java.util.List;

import com.thuctaptotnghiep.food.dto.CategoryDTO;

public interface ICategoryService {
	List<CategoryDTO> findAll();
	CategoryDTO findOne(String categoryCode);
}
