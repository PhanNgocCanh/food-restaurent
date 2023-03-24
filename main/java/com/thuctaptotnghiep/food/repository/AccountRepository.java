package com.thuctaptotnghiep.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thuctaptotnghiep.food.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String>{
	Account findOneByEmail(String email);
}
