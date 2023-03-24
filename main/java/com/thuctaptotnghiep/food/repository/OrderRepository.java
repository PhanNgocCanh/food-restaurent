package com.thuctaptotnghiep.food.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thuctaptotnghiep.food.entity.Account;
import com.thuctaptotnghiep.food.entity.Order;
import com.thuctaptotnghiep.food.entity.OrderStatus;

public interface OrderRepository extends JpaRepository<Order, Long>{
	Order findOneByOrderCode(long orderCode);
	
	List<Order> findAllByAccount(Account account);
	
	@Modifying
	@Query(value = "delete from Orders where orderCode = :orderCode",nativeQuery = true)
	void deleteOrders(@Param("orderCode") long orderCode);
	
	Page<Order> findAllByOrderStatus(OrderStatus orderStatus,Pageable pageable);
}
