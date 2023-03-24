package com.thuctaptotnghiep.food.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.thuctaptotnghiep.food.dto.ProductDTO;

public interface IProductService {
	List<ProductDTO> findAll();
	List<ProductDTO> findAll(Pageable pageable);
	List<ProductDTO> findAllWithSearchAndFilter(String categoryCode,String originCode,long maxPrice,String keyword,Pageable pageable);
	ProductDTO findOne(long productCode);
	ProductDTO save(ProductDTO product);
	boolean delete(long productCode);
	long getTotalItem();
}
