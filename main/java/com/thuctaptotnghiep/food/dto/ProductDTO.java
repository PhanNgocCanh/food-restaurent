package com.thuctaptotnghiep.food.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProductDTO {
	private Long productCode;
	@NotEmpty(message = "Tên sản phẩm bắt buộc nhập")
	private String productName;
	@NotEmpty(message = "Danh mục bắt buộc chọn")
	private String categoryCode;
	@NotEmpty(message = "Đơn vị tính bắt buộc chọn")
	private String unitCode;
	@NotEmpty(message = "Xuất cứ bắt buộc chọn")
	private String originCode;
	private String image;
	@NotNull(message = "Giá bắt buộc nhập")
	private Long price;
	@NotNull(message = "Số lượng bắt buộc nhập")
	private int quantity;
	private int discount;
	private String ingredient;
	private String userManual;

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

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getOriginCode() {
		return originCode;
	}

	public void setOriginCode(String originCode) {
		this.originCode = originCode;
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

	@Override
	public String toString() {
		return "ProductDTO [productCode=" + productCode + ", productName=" + productName + ", categoryCode="
				+ categoryCode + ", unitCode=" + unitCode + ", originCode=" + originCode + ", image=" + image
				+ ", price=" + price + ", quantity=" + quantity + ", discount=" + discount + ", ingredient="
				+ ingredient + ", userManual=" + userManual + "]";
	}
	
}
