package com.thuctaptotnghiep.food.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Account")
public class Account {
	@Id
	@Column(name = "Email")
	private String email;
	@Column(name = "FullName")
	private String fullName;
	@Column(name = "Password")
	private String password;
	@Column(name = "Status")
	private int status;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "AccountRole" ,joinColumns = @JoinColumn(name="Email"),
			inverseJoinColumns = @JoinColumn(name="RoleCode"))
	private List<Role> roles = new ArrayList<>();
	@OneToMany(mappedBy = "account")
	private List<Order> orders = new ArrayList<>();

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
}
