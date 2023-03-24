package com.thuctaptotnghiep.food.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Origin")
public class Origin {
	@Id
	@Column(name = "OriginCode")
	private String originCode;
	@Column(name = "OriginName")
	private String originName;
	@OneToMany(mappedBy = "origin")
	private List<Product> products = new ArrayList<>();

	public String getOriginCode() {
		return originCode;
	}

	public void setOriginCode(String originCode) {
		this.originCode = originCode;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
