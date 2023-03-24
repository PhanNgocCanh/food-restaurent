package com.thuctaptotnghiep.food.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thuctaptotnghiep.food.converter.OrderDetailConverter;
import com.thuctaptotnghiep.food.dto.OrderDetailDTO;
import com.thuctaptotnghiep.food.entity.OrderDetail;
import com.thuctaptotnghiep.food.entity.ProductOrderKey;
import com.thuctaptotnghiep.food.repository.OrderDetailRepository;
import com.thuctaptotnghiep.food.service.IOrderDetailService;

@Service
@Transactional
public class OrderDetailService implements IOrderDetailService{
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	@Autowired
	private OrderDetailConverter orderDetailConverter;
	
	
	@Override
	public List<OrderDetailDTO> findAllByOrderCode(long orderCode) {
		List<OrderDetail> orderDetails = orderDetailRepository.findAllByOrderCode(orderCode);
		List<OrderDetailDTO> orderDetailDTOs = new ArrayList<>();
		for(OrderDetail od : orderDetails) {
			OrderDetailDTO odDTO = orderDetailConverter.toDTO(od);
			orderDetailDTOs.add(odDTO);
		}
		return orderDetailDTOs;
	}

	@Override
	public void save(OrderDetailDTO orderDetailDTO) {
		OrderDetail orderDetail = orderDetailConverter.toEntity(orderDetailDTO);
		orderDetailRepository.save(orderDetail);
		
	}

	@Override
	public void delete(long orderCode,long productCode) {
		ProductOrderKey productOrderKey = new ProductOrderKey();
		productOrderKey.setOrderCode(orderCode);
		productOrderKey.setProductCode(productCode);
		orderDetailRepository.deleteById(productOrderKey);
	}

	@Override
	public void deleteAllByOrderCode(long orderCode) {
		orderDetailRepository.deleteAllByOrderCode(orderCode);
	}

}
