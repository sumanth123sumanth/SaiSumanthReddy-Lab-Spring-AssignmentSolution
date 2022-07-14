package com.greatlearning.student.studentmanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.greatlearning.student.studentmanagement.service.MyUserDetailsService;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService getUserDetailsService() {
		return new MyUserDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider getDaoAuthenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(getUserDetailsService());
		auth.setPasswordEncoder(getBCryptPasswordEncoder());
		return auth;
	}

	@Override
	public void configure(AuthenticationManagerBuilder mgr) {
		mgr.authenticationProvider(getDaoAuthenticationProvider());
	}

	@Override
	public void configure(HttpSecurity http) {
		try {
			http.authorizeRequests().antMatchers("/", "/students/save", "/students/list", "/students/add", "/students")
					.hasAnyAuthority("USER", "ADMIN").antMatchers("/students/update", "/students/delete")
					.hasAuthority("ADMIN").anyRequest().authenticated().and().formLogin().loginProcessingUrl("/login")
					.successForwardUrl("/students/list").permitAll().and().logout().logoutSuccessUrl("/login")
					.permitAll().and().exceptionHandling().accessDeniedPage("/students/403").and().cors().and().csrf()
					.disable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
