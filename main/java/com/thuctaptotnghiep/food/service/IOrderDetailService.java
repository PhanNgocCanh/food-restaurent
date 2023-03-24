package com.thuctaptotnghiep.food.service;

import java.util.List;

import com.thuctaptotnghiep.food.dto.OrderDetailDTO;
import com.thuctaptotnghiep.food.entity.Order;

public interface IOrderDetailService {
	List<OrderDetailDTO> findAllByOrderCode(long orderCode);
	void save(OrderDetailDTO orderDetailDTO);
	void delete(long orderCode,long productCode);
	void deleteAllByOrderCode(long orderCode);
}
