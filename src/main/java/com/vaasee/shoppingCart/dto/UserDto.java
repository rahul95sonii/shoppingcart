package com.vaasee.shoppingCart.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.vaasee.shoppingCart.serialization.RoleDeserializer;


/*
 * 
 * author rahul.soni
 * 
 * */


public class UserDto {

	private Long id;

	private String username;
	private String password;
	private String email;

	@JsonDeserialize(using = RoleDeserializer.class) // Use custom deserializer
    private Role role;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
	
	
}
