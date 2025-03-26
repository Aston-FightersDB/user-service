package ru.aston.userservice.security.provider;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import ru.aston.userservice.security.JwtTokenUtil;
import ru.aston.userservice.security.authentication.JwtAuthentication;
import ru.aston.userservice.security.principal.InServiceUserDetails;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {
  private final JwtTokenUtil tokenUtil;
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String token = authentication.getCredentials().toString();

    UUID userId = tokenUtil.getUserId(token);

    InServiceUserDetails userDetails = new InServiceUserDetails(userId, token);
    return new JwtAuthentication(
        userDetails,
        null,
        null);
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.isAssignableFrom(JwtAuthentication.class);
  }
}
