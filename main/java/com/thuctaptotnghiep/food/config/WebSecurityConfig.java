package com.thuctaptotnghiep.food.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.thuctaptotnghiep.food.service.impl.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Autowired
	private CustomUserDetailService customUserDetailService;
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(customUserDetailService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http
		    .csrf().disable()
		    .authorizeHttpRequests((requests) ->requests
		    		.antMatchers("/clients/**","/images/**","/admins/**").permitAll()
		    		.antMatchers("/").permitAll()		
		    		.antMatchers("/access-denied").permitAll()
		    		.antMatchers("/register").permitAll()
		    		.antMatchers("/admin/**").hasRole("ADMIN")
		    		.antMatchers("/**").hasAnyRole("USER","ADMIN")
		    		.anyRequest().authenticated()
		    )
		    .formLogin((form) -> form
		    		.loginPage("/login").permitAll()
		    		.usernameParameter("email")
		    		.loginProcessingUrl("/j_spring_security_check")
		    		.defaultSuccessUrl("/",true)
		    		.failureUrl("/login?failure")
		    )
		    .logout().permitAll()
		    .logoutUrl("/logout")
		    .logoutSuccessUrl("/");
		http.authenticationProvider(authenticationProvider());
		return http.build();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}	
}
