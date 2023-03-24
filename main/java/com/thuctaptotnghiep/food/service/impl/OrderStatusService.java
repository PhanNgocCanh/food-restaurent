package com.thuctaptotnghiep.food.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thuctaptotnghiep.food.dto.OrderStatusDTO;
import com.thuctaptotnghiep.food.entity.OrderStatus;
import com.thuctaptotnghiep.food.repository.OrderStatusRepository;
import com.thuctaptotnghiep.food.service.IOrderStatusService;

@Service
public class OrderStatusService implements IOrderStatusService{
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private OrderStatusRepository orderStatusRepository;
	@Override
	public List<OrderStatusDTO> findAll() {
		List<OrderStatus> orderStatus = orderStatusRepository.findAll();
		List<OrderStatusDTO> orderStatusDTOs = new ArrayList<>();
		for(OrderStatus os : orderStatus) {
			OrderStatusDTO osDTO = modelMapper.map(os, OrderStatusDTO.class);
			orderStatusDTOs.add(osDTO);
		}
		return orderStatusDTOs;
	}

	@Override
	public OrderStatusDTO findOneByOrderStatusCode(String orderStatusCode) {
		OrderStatus orderStatus = orderStatusRepository.findOneByOrderStatusCode(orderStatusCode);
		OrderStatusDTO orderStatusDTO= modelMapper.map(orderStatus, OrderStatusDTO.class);
		return orderStatusDTO;
	}

}
