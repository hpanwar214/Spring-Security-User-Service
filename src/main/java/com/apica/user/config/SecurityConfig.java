package com.apica.user.config;

import com.apica.user.domain.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	public static final String USER_PATH = "/api/user";
	public static final String USER_PATH_2 = "/api/user/**";
	public static final String USER_AUDIT_PATH = "/api/user-audit**";
	private final UserDetailsServiceImpl userDetailsServiceImpl;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsServiceImpl);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;

	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		        return http.authorizeHttpRequests(auth -> auth
							.requestMatchers(HttpMethod.POST, USER_PATH).hasAuthority(Role.ROLE_ADMIN.toString())
							.requestMatchers(HttpMethod.DELETE, USER_PATH_2).hasAuthority(Role.ROLE_ADMIN.toString())
							.requestMatchers(USER_PATH_2).hasAnyAuthority(Role.ROLE_USER.toString(), Role.ROLE_ADMIN.toString())
							.requestMatchers(USER_AUDIT_PATH).hasAuthority(Role.ROLE_ADMIN.toString()).anyRequest().authenticated()
						)
				.httpBasic(Customizer.withDefaults())
						.csrf().disable()
				.userDetailsService(userDetailsServiceImpl).build();
	}
	/*
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(auth -> auth
						.requestMatchers(HttpMethod.POST, USER_PATH).hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, USER_PATH_2).hasRole("ADMIN")
						.requestMatchers(USER_PATH_2).hasAnyRole("USER", "ADMIN")
						.requestMatchers(USER_AUDIT_PATH).hasRole("ADMIN").anyRequest().authenticated()
				)
				.httpBasic(customizer -> customizer.realmName("My User Realm"))
				.csrf().disable()
				.userDetailsService(getUserDetailService()).build();
	}
	*/
}
