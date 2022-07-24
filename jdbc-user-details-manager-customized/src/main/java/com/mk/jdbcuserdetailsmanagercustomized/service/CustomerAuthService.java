package com.mk.jdbcuserdetailsmanagercustomized.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mk.jdbcuserdetailsmanagercustomized.model.Customer;
import com.mk.jdbcuserdetailsmanagercustomized.model.SecurityCustomer;
import com.mk.jdbcuserdetailsmanagercustomized.repo.CustomerRepository;

@Service
public class CustomerAuthService implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<Customer> customerList = customerRepository.findByEmail(username);
		// TODO Auto-generated method stub
		if(customerList.size() == 0) {
			throw new UsernameNotFoundException("User not exist : "+username);
		}
		return new SecurityCustomer(customerList.get(0));
	}

}
