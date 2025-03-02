package com.vaasee.shoppingCart.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaasee.shoppingCart.dto.UserDto;
import com.vaasee.shoppingCart.entity.User;
import com.vaasee.shoppingCart.repository.UserRepository;
import com.vaasee.shoppingCart.service.UserService;


/*
 * 
 * author rahul.soni
 * 
 * */

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	ObjectMapper objectMapper = new ObjectMapper();

	public User registerUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword())); // Encode the password
		user.addAuthority(new SimpleGrantedAuthority("user"));
		return userRepository.save(user);
	}

	public Optional<UserDto> authenticateUser(String username, String password) {
		Optional<User> user = userRepository.findByUsername(username);

		if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
			try {
				// Convert User entity to UserDto using ObjectMapper
				UserDto userDto = objectMapper.convertValue(user.get(), UserDto.class);
				return Optional.of(userDto);
			} catch (Exception e) {
				// Handle exception, maybe log it
				e.printStackTrace();
			}
		}
		return Optional.empty();
	}

//    public Optional<UserDto> authenticateUser(String username, String password) {
//        Optional<User> user = userRepository.findByUsername(username);
//      
//        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
//            return user;
//        }
//        return Optional.empty();
//    }
}