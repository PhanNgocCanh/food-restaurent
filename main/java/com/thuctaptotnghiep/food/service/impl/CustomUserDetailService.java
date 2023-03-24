package com.thuctaptotnghiep.food.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.thuctaptotnghiep.food.entity.Account;
import com.thuctaptotnghiep.food.repository.AccountRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Account account = accountRepository.findOneByEmail(email);
		if(account == null) {
			throw new UsernameNotFoundException("User account not found !");
		}
		return new ShowUserDetail(account);
	}

}
