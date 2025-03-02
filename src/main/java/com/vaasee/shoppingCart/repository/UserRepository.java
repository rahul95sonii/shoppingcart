package com.vaasee.shoppingCart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vaasee.shoppingCart.dto.UserDto;
import com.vaasee.shoppingCart.entity.User;


/*
 * 
 * author rahul.soni
 * 
 * */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}