package com.thuctaptotnghiep.food.dto;

public class CartDTO {
	private Long productCode;
	private String productName;
	private String image;
	private int quantity;
	private long price;
	private int discount;

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public long getTotalMoney() {
		return price*quantity-quantity*(price*discount/100);
	}
	
	@Override
	public boolean equals(Object obj) {
		CartDTO cart = (CartDTO)obj;
		if(this.productCode == cart.getProductCode()) return true;
		else return false;
	}

}
