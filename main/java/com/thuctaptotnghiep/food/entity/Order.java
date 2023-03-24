package com.thuctaptotnghiep.food.entity;

import java.util.ArrayList;
import java.util.Date;
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
@Table(name = "Orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderCode")
	private Long orderCode;
	@ManyToOne
	@JoinColumn(name = "Email")
	private Account account;
	@ManyToOne
	@JoinColumn(name = "OrderStatusCode")
	private OrderStatus orderStatus;
	@Column(name = "FullName")
	private String fullName;
	@Column(name = "Address")
	private String address;
	@Column(name = "Phone")
	private String phone;
	@Column(name = "OrderDate")
	private Date orderDate;
	@Column(name = "TotalMoney")
	private Long totalMoney;
	@OneToMany(mappedBy = "order")
	private List<OrderDetail> orderDetails = new ArrayList<>();
	
	public Long getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(Long orderCode) {
		this.orderCode = orderCode;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Long getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Long totalMoney) {
		this.totalMoney = totalMoney;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

}
