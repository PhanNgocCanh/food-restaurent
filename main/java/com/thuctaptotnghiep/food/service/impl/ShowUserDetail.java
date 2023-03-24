package com.thuctaptotnghiep.food.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.thuctaptotnghiep.food.entity.Account;
import com.thuctaptotnghiep.food.entity.Role;

public class ShowUserDetail implements UserDetails{
	private static final long serialVersionUID = 1L;

	private Account account;
	
	public ShowUserDetail(Account account) {
		this.account = account;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(Role role : account.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return account.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return account.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public String getFullname() {
		return account.getFullName();
	}
}
