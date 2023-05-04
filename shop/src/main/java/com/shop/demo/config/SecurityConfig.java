package com.shop.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

import com.shop.demo.config.jwt.JwtAuthenticationFilter;
import com.shop.demo.config.jwt.JwtAuthorizationFilter;
import com.shop.demo.config.jwt.JwtProperties;
import com.shop.demo.filter.TraceFilter;
import com.shop.demo.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity // SpringSecurityFilter가 SpringFilterChain 에 등록이 된다.
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // secured annotation 활성화, preAuthorize,
																			// postAuthorize annotion 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	
	private final CorsConfig corsConfig;

	private final UserRepository userRepository;

	private final JwtProperties jwtProperties;
		
	private final AuthenticationFailureHandler customFailureHandler;
	
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
		return new JwtAuthenticationFilter(authenticationManager(), jwtProperties);
	}

	
	@Bean
	public JwtAuthorizationFilter jwtAuthorizationFilter() throws Exception {
		return new JwtAuthorizationFilter(authenticationManager(), userRepository, jwtProperties);
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Security Filter 순서
		// SecurityContextPersistenceFilter -> LogoutFilter ->
		// UsernamePasswordAuthenticationFilter
		// -> DefaultLoginPageGeneratingFilter -> BasicAuthenticationFilter ->
		// RememberMeAuthenticationFilter
		// -> SecurityContextHolderAwareRequestFilter -> AnonymousAuthenticationFilter
		// -> SessionManagementFilter
		// -> ExceptionTranslationFilter -> FilterSecurityInterceptor
		http.addFilterBefore(new TraceFilter(), SecurityContextPersistenceFilter.class); // csrf 비활성화
		// http.addFilterAfter(new TraceFilter(),
		// SecurityContextPersistenceFilter.class); //csrf 비활성화

		http.csrf().disable(); // csrf 비활성화
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // session 사용하지 않음
				.and().addFilter(corsConfig.corsFilter()).formLogin().disable().httpBasic().disable()
				.addFilter(jwtAuthenticationFilter()) // AuthenticationManager
				.addFilter(jwtAuthorizationFilter()) // AuthenticationManager
				.authorizeRequests()
				.antMatchers("/api/user/**").access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
				.antMatchers("/api/manager/**").access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
				.antMatchers("/api/admin/**").access("hasRole('ROLE_ADMIN')")
				.anyRequest().permitAll();

	}

	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception {
	 * auth.userDetailsService(principalDetailsService).passwordEncoder(encodePwd())
	 * ; //패스워드 encoder 설정 }
	 */

}
