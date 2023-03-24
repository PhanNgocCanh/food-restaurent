package com.thuctaptotnghiep.food.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thuctaptotnghiep.food.converter.ProductConverter;
import com.thuctaptotnghiep.food.dto.ProductDTO;
import com.thuctaptotnghiep.food.entity.Category;
import com.thuctaptotnghiep.food.entity.Origin;
import com.thuctaptotnghiep.food.entity.Product;
import com.thuctaptotnghiep.food.repository.CategoryRepository;
import com.thuctaptotnghiep.food.repository.OriginRepository;
import com.thuctaptotnghiep.food.repository.ProductRepository;
import com.thuctaptotnghiep.food.service.IProductService;

@Service
public class ProductService implements IProductService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductConverter productConverter;
    private long count=0;
    
    
	@Override
	public List<ProductDTO> findAll() {
		List<Product> product = productRepository.findAll();
		List<ProductDTO> productDTOs = new ArrayList<>();
		for (Product p : product) {
			ProductDTO productDTO = productConverter.toDTO(p);
			productDTOs.add(productDTO);
		}
		count = productRepository.count();
		return productDTOs;
	}

	@Override
	public List<ProductDTO> findAll(Pageable pageable) {
		List<Product> product = productRepository.findAll(pageable).getContent();
		List<ProductDTO> productDTOs = new ArrayList<>();
		for (Product p : product) {
			ProductDTO productDTO = productConverter.toDTO(p);
			productDTOs.add(productDTO);
		}
		count = productRepository.count();
		return productDTOs;
	}

	@Override
	public List<ProductDTO> findAllWithSearchAndFilter(String categoryCode, String originCode,long maxPrice,String keyword, Pageable pageable) {
		List<Product> product =productRepository.findByCategoryAndOrigin(categoryCode, originCode,maxPrice,keyword, pageable).getContent();
		List<ProductDTO> productDTOs = new ArrayList<>();
		for (Product p : product) {
			ProductDTO productDTO = productConverter.toDTO(p);
			productDTOs.add(productDTO);
		}
		count = productRepository.findByCategoryAndOrigin(categoryCode, originCode,maxPrice,keyword, pageable).getTotalElements();
		return productDTOs;
	}

	@Override
	public ProductDTO findOne(long productCode) {
		ProductDTO product = productConverter.toDTO(productRepository.findOneByProductCode(productCode));
		return product;
	}

	@Override
	public ProductDTO save(ProductDTO product) {
		Product p = productConverter.toEntity(product);
		p = productRepository.save(p);
		return productConverter.toDTO(p);
	}

	@Override
	public boolean delete(long productCode) {
		try {
			productRepository.deleteById(productCode);
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public long getTotalItem() {
		return count;
	}

}
