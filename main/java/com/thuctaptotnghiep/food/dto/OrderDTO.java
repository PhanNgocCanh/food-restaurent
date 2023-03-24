package com.thuctaptotnghiep.food.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.thuctaptotnghiep.food.entity.OrderStatus;

public class OrderDTO {
	private Long orderCode;
	private String Email;
	private String orderStatusCode;
	@NotEmpty(message = "Tên người nhận không được bỏ trống")
	private String fullName;
	@NotEmpty(message = "Địa chỉ nhận hàng không được bỏ trống")
	private String address;
	@NotEmpty(message = "Số điện thoại không được bỏ trống")
	@Pattern(regexp = "\\d{10}", message = "Số điện thoại không hợp lệ")
	private String phone;
	private Date orderDate;
	private Long totalMoney;

	public Long getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(Long orderCode) {
		this.orderCode = orderCode;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getOrderStatusCode() {
		return orderStatusCode;
	}

	public void setOrderStatusCode(String orderStatusCode) {
		this.orderStatusCode = orderStatusCode;
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

}
