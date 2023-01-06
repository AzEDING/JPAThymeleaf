package com.setproject.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
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

	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
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
			.csrf().disable()
            .exceptionHandling()
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, OWNER_ONLY_LIST).access("@authorizationChecker.boardIdCheck(request, #boardId)")
			.antMatchers(ADMIN_ONLY_LIST).hasRole("ADMIN")
			.antMatchers(PERMIT_ALL_LIST).permitAll()
			.anyRequest().authenticated();
		return httpSecurity.build();
	}

	private static final String[] PERMIT_ALL_LIST = {
			"/v1",
			"/v1/home/**",
			"/*/login",
			"/*/signup",
			"/*/board/**",
			"/*/user/**"
	};
	
	private static final String[] OWNER_ONLY_LIST = {
			"/v1/board/{boardId}/update",
			"/v1/board/{boardId}/delete"
	};

	private static final String[] ADMIN_ONLY_LIST = {
			"/*/admin/**"
	};

	@Bean
	public WebSecurityCustomizer configure() {
		return (web) -> web.ignoring().mvcMatchers(
                "*/js/**",
                "*/css/**",
				"*/webjars/**");
	}


}
