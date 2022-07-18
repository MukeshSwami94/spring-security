package com.mk.jdbcuserdetailsmanager.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

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
		
		
		  http.authorizeRequests(requests -> {
		  requests.antMatchers("/user","/userList").authenticated().
		  antMatchers("/home").permitAll();
		  
		  }); http.formLogin(); http.httpBasic();
		 

		/*===========Deny all request configuration============*/
		
		/*
		 * http.authorizeRequests((requests) -> requests.anyRequest().denyAll());
		 * http.formLogin(); http.httpBasic();
		 */
		  
		  /*===========Permit all request configuration============*/
			
		/*
		 * http.authorizeRequests((requests) -> requests.anyRequest().permitAll());
		 * http.formLogin(); http.httpBasic();
		 */
		 
		
	}
	//===================================IN Memory Authentication=========================================
	
//	The upper one will accept HTTP strictly security and the lawyer one will accept AuthenticationManagerBuilder .
	
	/*
	 * So this is a method where if you want to customize, you will use a user
	 * details password encoders along with authentication providers.
	 */
	
	
	/*
	 * If you want to configure your own users and multiple users, as you can see
	 * here for now, we are going to use in memory authentication.
	 */
	
	/*
	 * That means all these users that we want to maintain will be stored inside
	 * memory of spring container,
	 * 
	 * which will be erased by spring security while performing authentication and
	 * authorization details.
	 * 
	 * First, we have to select in memory of authentication and whenever we want to
	 * add a user, we have to
	 * 
	 * call the method with user and what is a user name followed by a dot password
	 * and the user password along
	 * 
	 * with the authorities that the user want to maintain.
	 */
	
	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception {
	 * auth.inMemoryAuthentication().withUser("vendor").password("12345").
	 * authorities("admin").and().
	 * withUser("user1").password("user@123").authorities("read").and().
	 * passwordEncoder(NoOpPasswordEncoder.getInstance());
	 * 
	 * 
	 * }
	 */
	
	
	//===================================InMemoryUserDetailsManager=========================================
	
	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { InMemoryUserDetailsManager userDetailsManager = new
	 * InMemoryUserDetailsManager(); UserDetails user1 =
	 * User.withUsername("admin").password("12345").authorities("admin").build();
	 * UserDetails user2 =
	 * User.withUsername("user").password("12345").authorities("read").build();
	 * userDetailsManager.createUser(user1); userDetailsManager.createUser(user2);
	 * auth.userDetailsService(userDetailsManager); }
	 */

	/*
	 * We have to create a been by password and quarter and I am returning no
	 * password encoder as the type
	 * 
	 * for this be here my spring security since it's a find it being of by password
	 * encoder.
	 * 
	 * It takes this as a default password encoder that it has to assume for this
	 * user details custom configurations
	 * 
	 * that we have been here with error.
	 */
	
    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
    
    @Bean
    UserDetailsService userDetailsService(DataSource dataSource) {
    	return new JdbcUserDetailsManager(dataSource);
    }
}
