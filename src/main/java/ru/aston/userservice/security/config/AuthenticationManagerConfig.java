package ru.aston.userservice.security.config;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.aston.userservice.security.provider.JwtAuthenticationProvider;

@RequiredArgsConstructor
@Configuration
public class AuthenticationManagerConfig {
  private final JwtAuthenticationProvider jwtAuthenticationProvider;
  private final UserDetailsService userDetailsService;

  @Bean
  AuthenticationManager authenticationManager() {
    return new ProviderManager(List.of(jwtAuthenticationProvider, daoAuthenticationProvider()));
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    daoAuthenticationProvider.setUserDetailsService(userDetailsService);
    return daoAuthenticationProvider;
  }
}
