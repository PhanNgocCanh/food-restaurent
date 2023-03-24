package com.thuctaptotnghiep.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thuctaptotnghiep.food.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	Role findOneByRoleName(String roleName);
}
