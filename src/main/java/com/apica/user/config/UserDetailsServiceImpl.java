package com.apica.user.config;

import com.apica.user.domain.entity.UserEntity;
import com.apica.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl
		implements UserDetailsService
{

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<UserEntity> user = userRepository.findByEmail(username);

		if (user.isEmpty()) {
			throw new UsernameNotFoundException("Could not found user !!");
		}

		return new CustomUserDetails(user.get());
	}

}
