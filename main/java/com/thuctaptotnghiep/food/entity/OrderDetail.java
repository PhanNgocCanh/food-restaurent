package com.thuctaptotnghiep.food.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "OrderDetail")
public class OrderDetail {
	@EmbeddedId
	private ProductOrderKey productOrderKey;
	@ManyToOne
	@MapsId("productCode")
	@JoinColumn(name = "ProductCode")
	private Product product;
	@ManyToOne
	@MapsId("orderCode")
	@JoinColumn(name = "OrderCode")
	private Order order;
	@Column(name = "Quantity")
	private int quantity;
	@Column(name = "Price")
	private Long price;
	@Column(name = "Discount")
	private int discount;
	@Column(name = "IntoMoney")
	private Long intoMoney;

	public ProductOrderKey getProductOrderKey() {
		return productOrderKey;
	}

	public void setProductOrderKey(ProductOrderKey productOrderKey) {
		this.productOrderKey = productOrderKey;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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
