package com.thuctaptotnghiep.food.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thuctaptotnghiep.food.dto.ProductDTO;
import com.thuctaptotnghiep.food.entity.Category;
import com.thuctaptotnghiep.food.entity.Origin;
import com.thuctaptotnghiep.food.entity.Product;
import com.thuctaptotnghiep.food.entity.Unit;
import com.thuctaptotnghiep.food.service.ICategoryService;
import com.thuctaptotnghiep.food.service.IOriginService;
import com.thuctaptotnghiep.food.service.IUnitService;

@Component
public class ProductConverter {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private IOriginService originService;
	@Autowired
	private IUnitService unitService;
	public ProductDTO toDTO(Product product) {
		ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
		productDTO.setCategoryCode(product.getCategory().getCategoryCode());
		productDTO.setOriginCode(product.getOrigin().getOriginCode());
		productDTO.setUnitCode(product.getUnit().getUnitCode());
		return productDTO;
	}
	
	public Product toEntity(ProductDTO product) {
		Product productEntity = modelMapper.map(product,Product.class);
		productEntity.setCategory(modelMapper.
				map(categoryService.findOne(product.getCategoryCode()), Category.class));
		productEntity.setOrigin(modelMapper.
				map(originService.findOne(product.getOriginCode()), Origin.class));
		productEntity.setUnit(modelMapper.
				map(unitService.findOne(product.getUnitCode()), Unit.class));
		return productEntity;
	}
	
//	public Product toEntity(ProductDTO productDTO,Product product) {
//		
//	}
}
