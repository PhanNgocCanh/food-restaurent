package com.thuctaptotnghiep.food.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thuctaptotnghiep.food.entity.OrderDetail;
import com.thuctaptotnghiep.food.entity.ProductOrderKey;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, ProductOrderKey>{
	@Modifying
	@Query(value = "delete from OrderDetail where orderCode = :orderCode",nativeQuery = true)
	void deleteAllByOrderCode(@Param("orderCode") long orderCode);
	@Query(value = "select * from OrderDetail where orderCode = :orderCode",nativeQuery = true)
	List<OrderDetail> findAllByOrderCode(@Param("orderCode") long orderCode);
}
