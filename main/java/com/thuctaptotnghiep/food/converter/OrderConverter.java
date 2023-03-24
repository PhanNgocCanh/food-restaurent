package com.thuctaptotnghiep.food.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thuctaptotnghiep.food.dto.OrderDTO;
import com.thuctaptotnghiep.food.entity.Account;
import com.thuctaptotnghiep.food.entity.Order;
import com.thuctaptotnghiep.food.entity.OrderStatus;
import com.thuctaptotnghiep.food.repository.AccountRepository;
import com.thuctaptotnghiep.food.repository.OrderStatusRepository;

@Component
public class OrderConverter {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private OrderStatusRepository orderStatusRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	public Order toEntity(OrderDTO orderDTO) {
		Order order = modelMapper.map(orderDTO, Order.class);
		Account account = accountRepository.findOneByEmail(orderDTO.getEmail());
		OrderStatus orderStatus = orderStatusRepository.findOneByOrderStatusCode(orderDTO.getOrderStatusCode());
		order.setAccount(account);
		order.setOrderStatus(orderStatus);
		return order;
	}
	
	public OrderDTO toDTO(Order order) {
		OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
		orderDTO.setEmail(order.getAccount().getEmail());
		orderDTO.setOrderStatusCode(order.getOrderStatus().getOrderStatusCode());
		return orderDTO;
	}
}
