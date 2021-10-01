package com.webApp.loginApp.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class UserCookie {

	@Id
	@GeneratedValue
	private int id;
	private String value;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, value);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserCookie other = (UserCookie) obj;
		return id == other.id && Objects.equals(value, other.value);
	}
	@Override
	public String toString() {
		return "UserCookie [id=" + id + ", " + (value != null ? "value=" + value : "") + "]";
	}
	
}
