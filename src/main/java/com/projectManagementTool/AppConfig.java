package com.projectManagementTool;

import java.util.Arrays;
import java.util.Collections;

import jakarta.servlet.http.HttpServletRequest; // Important for CORS
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration; // Missing!
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration 
@EnableWebSecurity
public class AppConfig {
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/api/**").authenticated() // use antMatchers if Boot 2.x
	            .anyRequest().permitAll()
	        )
	        .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
	        .csrf(csrf -> csrf.disable())
	        .cors(cors -> cors.configurationSource(corsConfigurationSource()));

	    return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration config = new CorsConfiguration();
//	    config.addAllowedOrigin("http://localhost:5173"); // frontend origin
	    config.addAllowedOrigin("https://projectmanagementtoolfrontend-production-aa23.up.railway.app");
	    config.addAllowedMethod("*");
	    config.addAllowedHeader("*");

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", config);
	    return source;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/api/**").authenticated()
//                .anyRequest().permitAll()
//            )
//            .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
//            .csrf(csrf -> csrf.disable())
//            .cors(cors -> cors.configurationSource(corsConfigurationSource()));
//
//        return http.build();
//    }
//
//    // ✅ Fixed method name, imports, and list values
//    private CorsConfigurationSource corsConfigurationSource() {
//        return request -> {
//            CorsConfiguration cfg = new CorsConfiguration();
//            cfg.setAllowedOrigins(Arrays.asList(
//                "http://localhost:3000",
//                "http://localhost:5173",
//                "http://localhost:4200"
//            ));
//            cfg.setAllowedMethods(Collections.singletonList("*"));
//            cfg.setAllowCredentials(true);
//            cfg.setAllowedHeaders(Collections.singletonList("*")); // Accept all headers
//            cfg.setExposedHeaders(Arrays.asList("Authorization"));
//            cfg.setMaxAge(3600L);
//            return cfg;
//        };
//    }
//    
    @Bean
    PasswordEncoder passwordEncoder() {
    	 return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}






