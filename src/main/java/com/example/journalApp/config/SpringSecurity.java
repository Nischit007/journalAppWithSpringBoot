package com.example.journalApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class SpringSecurity {

//	@Autowired
//	private UserDetailsServiceImpl userDetailsService;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests(request -> request.requestMatchers("/public/**", "/swagger-ui/**").permitAll()
						.requestMatchers("/journal/**", "/user/**").authenticated().requestMatchers("/admin/**")
						.hasRole("ADMIN").anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults()).csrf(AbstractHttpConfigurer::disable).build();
	}

//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
