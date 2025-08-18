package org.lessons.java.project.java_spec_backoffice_spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  @Bean
  // @SuppressWarnings("removal")
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.authorizeHttpRequests(requests -> requests
        .requestMatchers("/anime/create", "/anime/*/edit").hasAuthority("ADMIN")
        .requestMatchers(HttpMethod.POST, "anime/**").hasAuthority("ADMIN")
        .requestMatchers("/platforms", "/platforms/**").hasAuthority("ADMIN")
        .requestMatchers("/anime", "/anime/**").hasAnyAuthority("USER", "ADMIN")
        .requestMatchers("/**").permitAll())
        .formLogin(org.springframework.security.config.Customizer.withDefaults())
        .cors(cors -> cors.disable())
        .csrf(csfr -> csfr.disable());

    return http.build();
  }

  @Bean
  @SuppressWarnings("deprecation")
  DaoAuthenticationProvider authenticationProvider() {

    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

    authProvider.setUserDetailsService(userDetailService());
    authProvider.setPasswordEncoder(passwordEncoder());

    return authProvider;
  }

  @Bean
  DatabaseUserDetailService userDetailService() {
    return new DatabaseUserDetailService();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}
