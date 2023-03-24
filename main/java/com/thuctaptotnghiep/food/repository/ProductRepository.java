package com.thuctaptotnghiep.food.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thuctaptotnghiep.food.entity.Category;
import com.thuctaptotnghiep.food.entity.Origin;
import com.thuctaptotnghiep.food.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>,ProductRepositoryCustom{
	Product findOneByProductCode(Long productCode);
	
	@Query(value = "SELECT * FROM Product WHERE (:categoryCode is null or categoryCode = :categoryCode) and (:originCode is null"
			  + " or originCode = :originCode) and(:maxPrice = 0 or (price-price*discount/100 < :maxPrice))"
			  + " and(:keyword is null or productName like %:keyword%)",nativeQuery = true)
	Page<Product> findByCategoryAndOrigin(@Param("categoryCode") String categoryCode
			,@Param("originCode") String originCode,@Param("maxPrice") long maxPrice,@Param("keyword") String keyword ,Pageable pageable);
}
