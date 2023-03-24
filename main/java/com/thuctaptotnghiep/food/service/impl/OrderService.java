package com.thuctaptotnghiep.food.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thuctaptotnghiep.food.converter.OrderConverter;
import com.thuctaptotnghiep.food.dto.OrderDTO;
import com.thuctaptotnghiep.food.entity.Account;
import com.thuctaptotnghiep.food.entity.Order;
import com.thuctaptotnghiep.food.entity.OrderStatus;
import com.thuctaptotnghiep.food.repository.AccountRepository;
import com.thuctaptotnghiep.food.repository.OrderRepository;
import com.thuctaptotnghiep.food.repository.OrderStatusRepository;
import com.thuctaptotnghiep.food.service.IOrderService;

@Service
@Transactional
public class OrderService implements IOrderService{
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private OrderStatusRepository orderStatusRepository;
	@Autowired
	private OrderConverter orderConverter;
	private long count = 0;
	@Override
	public List<OrderDTO> findAll(Pageable pageable) {
		List<Order> orders = orderRepository.findAll(pageable).getContent();
		List<OrderDTO> orderDTOs = new ArrayList<>();
		for(Order order : orders) {
			OrderDTO orderDTO = orderConverter.toDTO(order);
			orderDTOs.add(orderDTO);
		}
		this.count = orderRepository.count();
		return orderDTOs;
	}

	@Override
	public List<OrderDTO> findByAccount(String email) {
		Account account = accountRepository.findOneByEmail(email);
		List<Order> orders = orderRepository.findAllByAccount(account);
		List<OrderDTO> orderDTOs = new ArrayList<>();
		for(Order odr : orders) {
			OrderDTO odrDTO = orderConverter.toDTO(odr);
			orderDTOs.add(odrDTO);
		}
		return orderDTOs;
	}

	@Override
	public List<OrderDTO> findByOrderStatus(String orderStatusCode,Pageable pageable) {
		OrderStatus orderStatus = orderStatusRepository.findOneByOrderStatusCode(orderStatusCode);
		List<Order> orders = orderRepository.findAllByOrderStatus(orderStatus,pageable).getContent();
		List<OrderDTO> orderDTOs = new ArrayList<>();
		for(Order order : orders) {
			OrderDTO orderDTO = orderConverter.toDTO(order);
			orderDTOs.add(orderDTO);
		}
		this.count = orderRepository.findAllByOrderStatus(orderStatus,pageable).getTotalElements();
		return orderDTOs;
	}

	@Override
	public OrderDTO findOne(long orderCode) {
		Order order = orderRepository.findOneByOrderCode(orderCode);
		OrderDTO orderDTO = orderConverter.toDTO(order);
		return orderDTO;
	}

	@Override
	public long getTotal() {
		return count;
	}

	@Override
	public long save(OrderDTO orderDTO) {
		Order order = orderConverter.toEntity(orderDTO);
		long orderCode = orderRepository.save(order).getOrderCode();
		return orderCode;
	}

	@Override
	public void delete(long orderCode) {
		orderRepository.deleteById(orderCode);		
	}

	

}
