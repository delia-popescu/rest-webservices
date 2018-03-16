//package com.training.restwebservices.security.formLogin;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
//import org.springframework.stereotype.Component;
//
//import com.training.restwebservices.security.CustomBasicAuthenticationEntryPoint;
//
//@Component
//@EnableWebSecurity
//public class FormLoginWebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
//
//	@Autowired
//	private MySavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler;
//
//	@Autowired
//	private CustomAccessDeniedHandler accessDeniedHandler;
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//		auth.inMemoryAuthentication()
//		.withUser("admin").password("{noop}admin").roles("ADMIN")
//		.and()
//		.withUser("user").password("{noop}password").roles("USER");
//	}
//
//	@Override
//	protected void configure(final HttpSecurity http) throws Exception{
//		http.csrf().disable().exceptionHandling().accessDeniedHandler(accessDeniedHandler)
//				.authenticationEntryPoint(restAuthenticationEntryPoint)
//				.and()
//				.authorizeRequests()
//				.antMatchers("/courses/**").permitAll()
//				.antMatchers("/students/**").hasRole("ADMIN")
//				.and()
//				.formLogin()
//				.successHandler(authenticationSuccessHandler)
//				.failureHandler(new SimpleUrlAuthenticationFailureHandler())
//				.and()
//				.logout();
//	}
//
//	@Bean
//	public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
//		return new CustomBasicAuthenticationEntryPoint();
//	}
//
//	@Bean
//	public MySavedRequestAwareAuthenticationSuccessHandler mySuccessHandler() {
//		return new MySavedRequestAwareAuthenticationSuccessHandler();
//	}
//}