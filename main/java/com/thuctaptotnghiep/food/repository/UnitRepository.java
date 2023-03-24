package com.thuctaptotnghiep.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thuctaptotnghiep.food.entity.Unit;

public interface UnitRepository extends JpaRepository<Unit, String>{
	Unit findOneByUnitCode(String unitCode);
}
