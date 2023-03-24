package com.thuctaptotnghiep.food.service;

import java.util.List;

import com.thuctaptotnghiep.food.dto.OriginDTO;

public interface IOriginService {
	List<OriginDTO> findAll();
	OriginDTO findOne(String originCode);
}
