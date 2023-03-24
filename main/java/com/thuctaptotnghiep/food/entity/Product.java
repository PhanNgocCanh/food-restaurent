package com.thuctaptotnghiep.food.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ProductCode")
	private Long productCode;
	@Column(name = "ProductName")
	private String productName;
	@ManyToOne
	@JoinColumn(name = "CategoryCode")
	private Category category;
	@ManyToOne
	@JoinColumn(name = "UnitCode")
	private Unit unit;
	@ManyToOne
	@JoinColumn(name = "OriginCode")
	private Origin origin;
	@Column(name = "Image")
	private String image;
	@Column(name = "Price")
	private Long price;
	@Column(name = "Quantity")
	private int quantity;
	@Column(name = "Discount")
	private int discount;
	@Column(name = "Ingredient")
	private String ingredient;
	@Column(name = "UserManual")
	private String userManual;
	@OneToMany(mappedBy = "product")
	private List<OrderDetail> orderDetail = new ArrayList<>();
	public Long getProductCode() {
		return productCode;
	}

	public void setProductCode(Long productCode) {
		this.productCode = productCode;
	}
		
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Origin getOrigin() {
		return origin;
	}

	public void setOrigin(Origin origin) {
		this.origin = origin;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	public String getUserManual() {
		return userManual;
	}

	public void setUserManual(String userManual) {
		this.userManual = userManual;
	}

	public List<OrderDetail> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(List<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}
	
}
