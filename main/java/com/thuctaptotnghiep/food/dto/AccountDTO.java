package com.thuctaptotnghiep.food.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class AccountDTO {
	@NotEmpty(message = "Email không được bỏ trống")
	@Email(message = "Không đúng định dạng Email")
	private String email;
	@NotEmpty(message = "Tên không được bỏ trống")
	private String fullName;
	@NotEmpty(message = "Mật khẩu không được bỏ trống")
	private String password;
	private int status;
	private String passwordReEnter;

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

	public String getPasswordReEnter() {
		return passwordReEnter;
	}

	public void setPasswordReEnter(String passwordReEnter) {
		this.passwordReEnter = passwordReEnter;
	}

}
