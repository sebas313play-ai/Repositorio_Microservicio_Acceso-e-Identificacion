package com.nexa.acceso.config;

import com.nexa.acceso.security.JwtAuthFilter;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
 @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
 @Bean SecurityFilterChain filterChain(HttpSecurity http, JwtAuthFilter jwt) throws Exception {
  return http.csrf(csrf->csrf.disable())
    .sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    .authorizeHttpRequests(auth->auth
      .requestMatchers("/api/auth/login","/api/solicitudes-restablecimiento").permitAll()
      .requestMatchers("/api/cuentas/**","/api/solicitudes-restablecimiento/**").hasRole("ADMINISTRADOR")
      .anyRequest().authenticated())
    .addFilterBefore(jwt, UsernamePasswordAuthenticationFilter.class)
    .build();
 }
}
