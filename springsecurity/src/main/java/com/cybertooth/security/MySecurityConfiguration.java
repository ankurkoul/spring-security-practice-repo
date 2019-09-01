package com.cybertooth.security;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class MySecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	@Override
	@Primary
	protected void configure(HttpSecurity http) throws Exception {
		// use http to disable csrf
		http.csrf().disable().authorizeRequests().antMatchers("/login").permitAll()
				// it absence result direct access to methods
				.anyRequest().authenticated().and()
				// this show login form page
				.formLogin();
				// user form match with login call
				
				
	}

}
