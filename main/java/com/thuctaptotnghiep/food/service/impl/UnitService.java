package com.thuctaptotnghiep.food.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thuctaptotnghiep.food.dto.UnitDTO;
import com.thuctaptotnghiep.food.entity.Unit;
import com.thuctaptotnghiep.food.repository.UnitRepository;
import com.thuctaptotnghiep.food.service.IUnitService;

@Service
public class UnitService implements IUnitService{
	@Autowired
	private UnitRepository unitRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<UnitDTO> findAll() {
		List<Unit> units = unitRepository.findAll();
		List<UnitDTO> unitDTO = new ArrayList<>();
		for(Unit u:units) {
			UnitDTO unit = modelMapper.map(u, UnitDTO.class);
			unitDTO.add(unit);
		}
		return unitDTO;
	}

	@Override
	public UnitDTO findOne(String unitCode) {
		Unit unit = unitRepository.findOneByUnitCode(unitCode);
		UnitDTO unitDTO = modelMapper.map(unit, UnitDTO.class);
		return unitDTO;
	}
	
}
