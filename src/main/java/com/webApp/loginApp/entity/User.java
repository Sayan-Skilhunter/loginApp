package com.webApp.loginApp.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class User {

	@Id
	private String email;
	private String password;
	private String Otp;
	private String returnMsg;
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the otp
	 */
	public String getOtp() {
		return Otp;
	}
	/**
	 * @param otp the otp to set
	 */
	public void setOtp(String otp) {
		Otp = otp;
	}
	/**
	 * @return the returnMsg
	 */
	public String getReturnMsg() {
		return returnMsg;
	}
	/**
	 * @param returnMsg the returnMsg to set
	 */
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	@Override
	public int hashCode() {
		return Objects.hash(Otp, email, password, returnMsg);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(Otp, other.Otp) && Objects.equals(email, other.email)
				&& Objects.equals(password, other.password) && Objects.equals(returnMsg, other.returnMsg);
	}
	@Override
	public String toString() {
		return "User [" + (email != null ? "email=" + email + ", " : "")
				+ (password != null ? "password=" + password + ", " : "") + (Otp != null ? "Otp=" + Otp + ", " : "")
				+ (returnMsg != null ? "returnMsg=" + returnMsg : "") + "]";
	}
	
}
