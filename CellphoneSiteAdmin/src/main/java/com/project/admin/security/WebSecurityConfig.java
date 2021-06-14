package com.project.admin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.admin.handlers.OnAuthenticationFailureHandler;
import com.project.admin.handlers.OnAuthenticationSuccessHandler;
import com.project.admin.users.UserDetailsServiceImpl;

@Configuration()
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		System.out.println("userDetailsService ****");
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {			
		
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider ;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/", "/register", "/css/**", "/login_error", "/403", "/management_page_master", "/add_user",
				"/list_user", "/login_user", "/edit_user", "/delete_user",
				"/images/**", "/js/**", "/libs/**", "/vendors/**", "/src/**")
		.permitAll()
		.antMatchers("/add_user", "/edit_user", "/delete_user").hasAnyAuthority("admin")
		.anyRequest().authenticated()
		.and().formLogin().loginPage("/login_user").permitAll()
		.usernameParameter("username")
		.passwordParameter("password")
		.loginProcessingUrl("/dologin")
		.successHandler(new OnAuthenticationSuccessHandler())
		.failureHandler(new OnAuthenticationFailureHandler())
		.and().logout().permitAll()
		.and().exceptionHandling().accessDeniedPage("/403");
	}
}
