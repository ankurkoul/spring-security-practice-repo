package com.cybertooth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.cybertooth.security.encrypt.EncryptUserDetailsService;

import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.NoOp;

@Configuration
@EnableWebSecurity
public class MySecurityConfiguration extends WebSecurityConfigurerAdapter {

	/*
	 * @Override
	 * 
	 * @Bean protected UserDetailsService userDetailsService() {
	 * 
	 * List<UserDetails> users= new ArrayList<>();
	 * users.add(User.withDefaultPasswordEncoder().username("ankur").password("1234"
	 * ).roles("USER").build()); return new InMemoryUserDetailsManager(users); }
	 */

	/*
	 * @Autowired UserDetailsService userDetailsService;
	 */

	@Autowired
	EncryptUserDetailsService userDetailsService;

	/*
	 * here we will not user direct user details service
	 * 
	 * we will use getAuthentication provider
	 * 
	 * which will talk to service---> service will talk to repo ---->repo via
	 * application.yml will talk to db
	 * 
	 * but service need to be userdetail service to do so
	 */
	@Bean
	public AuthenticationProvider getAuthProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// use http to disable csrf
		http.csrf().disable().authorizeRequests().antMatchers("/login").permitAll()
				// it absence result direct access to methods
				.anyRequest().authenticated().and()
				// this show login form page
				.formLogin()
				// user form match with login call
				.loginPage("/login").permitAll().and()
				// after login do some logout work
				// do cleaing
				.logout().clearAuthentication(true).invalidateHttpSession(true)
				// on which request to do this
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/logout-success")
				.permitAll();
	}

}
