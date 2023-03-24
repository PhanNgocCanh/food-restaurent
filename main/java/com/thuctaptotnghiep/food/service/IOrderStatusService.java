package com.thuctaptotnghiep.food.service;

import java.util.List;

import com.thuctaptotnghiep.food.dto.OrderStatusDTO;

public interface IOrderStatusService {
	List<OrderStatusDTO> findAll();
	OrderStatusDTO findOneByOrderStatusCode(String orderStatusCode);
}
