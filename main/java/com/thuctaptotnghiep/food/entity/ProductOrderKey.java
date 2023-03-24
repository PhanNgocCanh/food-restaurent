package com.thuctaptotnghiep.food.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProductOrderKey implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "ProductCode")
	private Long productCode;
	@Column(name = "OrderCode")
	private Long orderCode;

	public Long getProductCode() {
		return productCode;
	}

	public void setProductCode(Long productCode) {
		this.productCode = productCode;
	}

	public Long getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(Long orderCode) {
		this.orderCode = orderCode;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return (int)(productCode+orderCode);
	}
	@Override
	public boolean equals(Object obj) {
		ProductOrderKey productOrderKey = (ProductOrderKey) obj;
		return this.productCode!=productOrderKey.getProductCode()&&this.orderCode!=productOrderKey.getOrderCode();
	}
}
