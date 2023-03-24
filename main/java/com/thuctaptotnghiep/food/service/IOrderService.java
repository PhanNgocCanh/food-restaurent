package com.thuctaptotnghiep.food.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.thuctaptotnghiep.food.dto.OrderDTO;

public interface IOrderService {
	List<OrderDTO> findAll(Pageable pageable);
	List<OrderDTO> findByAccount(String email);
	List<OrderDTO> findByOrderStatus(String orderStatusCode,Pageable pageable);
	OrderDTO findOne(long orderCode);
	long getTotal();
	long save(OrderDTO orderDTO);
	void delete(long orderCode);
}
