package com.mk.spring_security_basic_auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	protected void configure(HttpSecurity http) throws Exception {

		/*===========default configuration==========*/
		
		/*
		 * http.authorizeRequests((requests) -> requests.anyRequest().authenticated());
		 * http.formLogin(); http.httpBasic();
		 */
		
		
		/*===========custom configuration============*/
		
		/*
		 * http.authorizeRequests(requests -> {
		 * requests.antMatchers("/user","/userList").authenticated().
		 * antMatchers("/home").permitAll();
		 * 
		 * }); http.formLogin(); http.httpBasic();
		 */

		/*===========Deny all request configuration============*/
		
		/*
		 * http.authorizeRequests((requests) -> requests.anyRequest().denyAll());
		 * http.formLogin(); http.httpBasic();
		 */
		  
		  /*===========Permit all request configuration============*/
			
		  http.authorizeRequests((requests) -> requests.anyRequest().permitAll());
		  http.formLogin(); http.httpBasic();
		 
		
	}

}
