package ru.aston.userservice.security.filter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.aston.userservice.security.authentication.JwtAuthentication;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final AuthenticationManager authenticationManager;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    Optional<String> token = getTokenFromHeader(request);

    if (token.isPresent()) {
      Authentication authResult = doJwtAuthentication(token.get());
      setAuthInSecurityContext(authResult);
    }

    filterChain.doFilter(request, response);
  }

  private Optional<String> getTokenFromHeader(HttpServletRequest request) {
    String header = request.getHeader("Authorization");
    if (header == null) {
      return Optional.empty();
    }
    return Optional.of(header.substring(7));
  }

  private Authentication doJwtAuthentication(String token) {
    Authentication authentication = new JwtAuthentication(null, token);
    return authenticationManager.authenticate(authentication);
  }

  private void setAuthInSecurityContext(Authentication authentication) {
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }
}
