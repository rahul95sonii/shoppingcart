package com.vaasee.shoppingCart.controller;

import java.util.Map;
import java.util.Optional;
import java.util.WeakHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaasee.shoppingCart.dto.LoginRequest;
import com.vaasee.shoppingCart.dto.UserDto;
import com.vaasee.shoppingCart.entity.User;
import com.vaasee.shoppingCart.service.UserService;
import com.vaasee.shoppingCart.util.JwtUtil;
import com.vaasee.shoppingCart.util.TokenBlacklist;

/*
 * 
 * author rahul.soni
 * 
 * */

@RestController
@RequestMapping("/login/v1")
public class LoginController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private TokenBlacklist tokenService;

	// Signup API
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody User user) {
		User registeredUser = userService.registerUser(user);
		if (registeredUser.getId() != null) {
			return sendSuccessResponse("User registration Successfully!!", registeredUser.getId()); // Send categories
																									// with success
																									// response
		}
		return sendErrorResponse("Registration Failed!!");
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

		Optional<UserDto> user = userService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
		if (user.isPresent()) {
			String role = user.get().getRole().name(); // Get the user's role
			String token = JwtUtil.generateToken(loginRequest.getUsername(), role); // Generate JWT token
			WeakHashMap<String, Object> response = new WeakHashMap<String, Object>();
			response.put("role", role);
			response.put("token", token);
			return sendSuccessResponse("Login successfully..!!", response);
			
		} else {
			return sendErrorResponse("Invalid username or password");
		}
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
		String jwt = token.substring(7); // Remove "Bearer " prefix

		tokenService.blacklistToken(jwt); // Add token to blacklist
		return ResponseEntity.ok("Logout successful");
	}

}