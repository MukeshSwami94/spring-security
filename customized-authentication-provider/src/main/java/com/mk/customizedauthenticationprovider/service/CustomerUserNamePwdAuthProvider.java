package com.mk.customizedauthenticationprovider.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.mk.customizedauthenticationprovider.model.Customer;
import com.mk.customizedauthenticationprovider.repo.CustomerRepository;

@Component
public class CustomerUserNamePwdAuthProvider implements AuthenticationProvider{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = authentication.getName();
		String password = authentication.getCredentials().toString();
		List<Customer> customerList = customerRepository.findByEmail(userName);
		
		if(customerList.size()!=0) {
			if(passwordEncoder.matches(password, customerList.get(0).getPwd())) {
				List<GrantedAuthority> authorities = new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority(customerList.get(0).getRole()));
				return new UsernamePasswordAuthenticationToken(userName, password,authorities);
			}
			throw new BadCredentialsException("Invalid Password");
		}
		
		throw new BadCredentialsException("User does not exist, using credentials");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	
}
