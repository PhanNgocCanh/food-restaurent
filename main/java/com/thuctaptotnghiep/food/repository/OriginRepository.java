package com.thuctaptotnghiep.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thuctaptotnghiep.food.entity.Origin;

public interface OriginRepository extends JpaRepository<Origin, String>{
	Origin findOneByOriginCode(String originCode);
}
