package com.vaasee.shoppingCart.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

public class JwtUtil {

	private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Generate a secure key
	private static final long EXPIRATION_TIME = 864_000_000; // 10 days in milliseconds

	/**
	 * Generates a JWT token for the given username and role.
	 */
	public static String generateToken(String username, String role) {
		return Jwts.builder().setSubject(username).claim("role", role) // Add custom claims (e.g., role)
				.setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SECRET_KEY).compact();
	}

	/**
	 * Extracts the username from the JWT token.
	 */
	public static String extractUsername(String token) {
		return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody().getSubject();
	}

	/**
	 * Validates the JWT token. Returns true if the token is valid, false otherwise.
	 */
	public static boolean validateToken(String token) {
		try {
			// Parse the token and verify its signature
			Claims claims = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();

			// Check if the token is expired
			Date expirationDate = claims.getExpiration();
			return !expirationDate.before(new Date()); // Token is valid if not expired
		} catch (Exception e) {
			// Token is invalid (e.g., malformed, expired, or signature verification failed)
			return false;
		}
	}
}