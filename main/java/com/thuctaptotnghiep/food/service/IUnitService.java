package com.thuctaptotnghiep.food.service;

import java.util.List;

import com.thuctaptotnghiep.food.dto.UnitDTO;

public interface IUnitService {
	List<UnitDTO> findAll();
	UnitDTO findOne(String unitCode);
}
