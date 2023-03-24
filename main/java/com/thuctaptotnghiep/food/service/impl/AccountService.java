package com.thuctaptotnghiep.food.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thuctaptotnghiep.food.dto.AccountDTO;
import com.thuctaptotnghiep.food.entity.Account;
import com.thuctaptotnghiep.food.entity.Role;
import com.thuctaptotnghiep.food.repository.AccountRepository;
import com.thuctaptotnghiep.food.repository.RoleRepository;
import com.thuctaptotnghiep.food.service.IAccountService;

@Service
public class AccountService implements IAccountService {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void save(AccountDTO accountDTO) {
		Account account = modelMapper.map(accountDTO, Account.class);
		List<Role> roles = new ArrayList<>();
		roles.add(roleRepository.findOneByRoleName("ROLE_USER"));
		account.setRoles(roles);
		accountRepository.save(account);
	}

}
