package com.vaasee.shoppingCart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
 * 
 * author rahul.soni
 * 
 * */


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Disable CSRF
            .authorizeHttpRequests(auth -> auth
                // Public endpoints
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**").permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/login/v1/signup", "/login/v1/login").permitAll()
                // Restricted endpoints
                .requestMatchers("/admin/**").hasRole("ADMIN")
                // All other endpoints require authentication
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Disable session management
            );

        // Add JWT filter before UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCrypt for password encoding
    }
}
/*
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()) // Disable CSRF for H2 console
				.authorizeHttpRequests(auth -> auth

						.requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**")
						.permitAll().requestMatchers("/h2-console/**").permitAll()
						// Allow public access to any URL under "/master/**" and "/**"
						.requestMatchers("/master/**", "/**").permitAll()
						.requestMatchers("/login/v1/signup", "/login/v1/login").permitAll() // Allow signup and login
																							// without authentication
						// Restrict access to "/api/getApi" for users with the ADMIN role
						.requestMatchers("/api/getApi").hasRole("ADMIN").anyRequest().authenticated()

				).httpBasic(); // or .formLogin() for form-based authentication

		return http.build();
	}

	// UserDetailsService to manage users in memory
	@Bean
	public UserDetailsService userDetailsService() {
		// Create a regular user with "USER" role
		UserDetails user = User.builder().username("user").password(passwordEncoder().encode("user123")) // Ensure
																											// password
																											// is
																											// encoded
				.roles("USER").build();

		// Create an admin user with "ADMIN" role
		UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin123")) // Ensure
																											// password
																											// is
																											// encoded
				.roles("ADMIN").build();

		// Return an in-memory user manager with both users
		return new InMemoryUserDetailsManager(user, admin);
	}

	// Password encoder to use for encoding user passwords
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // Use BCrypt hashing for password encoding
	}*/

