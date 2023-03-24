package com.thuctaptotnghiep.food.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thuctaptotnghiep.food.dto.CategoryDTO;
import com.thuctaptotnghiep.food.entity.Category;
import com.thuctaptotnghiep.food.repository.CategoryRepository;
import com.thuctaptotnghiep.food.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService{
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public List<CategoryDTO> findAll() {
		List<Category> categories = categoryRepository.findAll();
		List<CategoryDTO> categoryDTO = new ArrayList<>();
		for(Category c:categories) {
			CategoryDTO category = modelMapper.map(c, CategoryDTO.class);
			categoryDTO.add(category);
		}
		return categoryDTO;
	}
	@Override
	public CategoryDTO findOne(String categoryCode) {
		Category category = categoryRepository.findOneByCategoryCode(categoryCode);
		CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
		return categoryDTO;
	}
	
}
