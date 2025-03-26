package ru.aston.userservice.security.provider;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.aston.userservice.security.JwtTokenUtil;
import ru.aston.userservice.security.UserDetailsImpl;
import ru.aston.userservice.security.UserDetailsServiceImpl;
import ru.aston.userservice.security.authentication.JwtAuthentication;
import ru.aston.userservice.security.principal.InServiceUserDetails;
import ru.aston.userservice.service.UserService;
import ru.aston.userservice.service.impl.JwtTokenService;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {
  private final JwtTokenUtil tokenUtil;
  private final UserDetailsService userDetailsService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String token = authentication.getCredentials().toString();
    String username = tokenUtil.getLogin(token);
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    return new JwtAuthentication(
        userDetails,
        null,
        userDetails.getAuthorities());
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.isAssignableFrom(JwtAuthentication.class);
  }
}
