package com.vaasee.shoppingCart.util;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class TokenBlacklist {
	private Set<String> blacklistedTokens = new HashSet<>();

	public void blacklistToken(String token) {
		blacklistedTokens.add(token);
	}

	public boolean isBlacklisted(String token) {
		return blacklistedTokens.contains(token);
	}

}