package com.setproject.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//		return authenticationConfiguration.getAuthenticationManager();
//	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.httpBasic().disable()
				.formLogin().disable()
				.sessionManagement(session -> session
					.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
					.maximumSessions(1)
					.maxSessionsPreventsLogin(true))
			.logout(logout -> logout
					.logoutRequestMatcher(new AntPathRequestMatcher("/v1/logout"))
					.logoutSuccessUrl("/v1/home")
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
					.deleteCookies()
					.permitAll())
            .exceptionHandling()
            .and()
            .authorizeHttpRequests()
//			.antMatchers(PERMIT_ALL_LIST).permitAll()
//			.antMatchers(OWNER_ONLY_LIST).h()
//			.antMatchers(ADMIN_ONLY_LIST).hasRole("ROLE_ADMIN")
			.anyRequest().permitAll();
		return httpSecurity.build();
	}
//
//	private static final String[] PERMIT_ALL_LIST = {
//			"/*/signup",
//			"/*/login"
//	};
//
//	private static final String[] USER_ONLY_LIST = {
//			"/*/user/{id}/**"
//	};
//
//	private static final String[] ADMIN_ONLY_LIST = {
//			"/*/admin/**"
//	};

//	@Bean
//	public WebSecurityCustomizer configure() {
//		return (web) -> web.ignoring().mvcMatchers("/",
//                "*/js/**",
//                "*/css/**",
//				"*/webjars/**");
//	}


}
