package com.thuctaptotnghiep.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thuctaptotnghiep.food.entity.OrderStatus;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, String>{
	OrderStatus findOneByOrderStatusCode(String orderStatusCode);
}
