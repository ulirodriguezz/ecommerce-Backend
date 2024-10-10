package com.example.backenddesarrollodeapps2ecommerce.config;

import java.util.Arrays;
import java.util.Base64;

import javax.crypto.SecretKey;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		  http
          .cors(httpSecurityCorsConfigurer -> {
              CorsConfiguration configuration = new CorsConfiguration();
              configuration.setAllowedMethods(Arrays.asList("*"));
              configuration.setAllowedHeaders(Arrays.asList("*"));
              configuration.setAllowCredentials(true);
              UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
              source.registerCorsConfiguration("/**", configuration);
              httpSecurityCorsConfigurer.configurationSource(source);
          });

		http.authorizeHttpRequests((authz) -> authz.anyRequest().authenticated());


		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/**");
	}


}