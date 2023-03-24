package com.thuctaptotnghiep.food.dto;

public class OrderDetailDTO {
	private Long orderCode;
	private Long productCode;
	private int quantity;
	private Long price;
	private int discount;
	private Long intoMoney;
	
	public Long getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(Long orderCode) {
		this.orderCode = orderCode;
	}
	public Long getProductCode() {
		return productCode;
	}
	public void setProductCode(Long productCode) {
		this.productCode = productCode;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public Long getIntoMoney() {
		return intoMoney;
	}
	public void setIntoMoney(Long intoMoney) {
		this.intoMoney = intoMoney;
	}
	
}
