package com.vaasee.shoppingCart.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;


/*
 * 
 * author rahul.soni
 * 
 * */


public class LoginRequest {
    private String username;
    private String password;
    
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
}