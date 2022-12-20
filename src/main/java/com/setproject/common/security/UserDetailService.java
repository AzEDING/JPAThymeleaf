package com.setproject.common.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.setproject.common.advice.exception.UserNotFoundException;
import com.setproject.domain.user.entity.User;
import com.setproject.domain.user.entity.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user =userRepository.findByEmailAndIsdel(email, false).orElseThrow(UserNotFoundException::new);
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		
		return new org
		        .springframework
		        .security
		        .core
		        .userdetails
		        .User(user.getEmail(), user.getPassword(), grantedAuthorities);
	}

}
