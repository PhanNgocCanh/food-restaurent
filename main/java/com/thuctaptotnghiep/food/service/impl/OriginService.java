package com.thuctaptotnghiep.food.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thuctaptotnghiep.food.dto.OriginDTO;
import com.thuctaptotnghiep.food.entity.Origin;
import com.thuctaptotnghiep.food.repository.OriginRepository;
import com.thuctaptotnghiep.food.service.IOriginService;

@Service
public class OriginService implements IOriginService {
	@Autowired
	private OriginRepository originRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<OriginDTO> findAll() {
		List<Origin> origins = originRepository.findAll();
		List<OriginDTO> originDTO = new ArrayList<>();
		for (Origin ori : origins) {
			OriginDTO origin = modelMapper.map(ori, OriginDTO.class);
			originDTO.add(origin);
		}
		return originDTO;
	}

	@Override
	public OriginDTO findOne(String originCode) {
		Origin origin = originRepository.findOneByOriginCode(originCode);
		OriginDTO originDTO = modelMapper.map(origin, OriginDTO.class);
		return originDTO;
	}

}
