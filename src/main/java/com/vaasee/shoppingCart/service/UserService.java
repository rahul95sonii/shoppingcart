package com.vaasee.shoppingCart.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaasee.shoppingCart.dto.UserDto;
import com.vaasee.shoppingCart.entity.User;

/*
 * 
 * author rahul.soni
 * 
 * */

public interface UserService {

	public User registerUser(User user);

	public Optional<UserDto> authenticateUser(String username, String password);
}
