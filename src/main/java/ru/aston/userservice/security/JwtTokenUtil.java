package ru.aston.userservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {

  public UUID getUserId(String token) {
    String userIdStr = getPayloadWithoutSignature(token).getSubject();
    return UUID.fromString(userIdStr);
  }

  public List<String> getAuthorities(String token) {
    Claims payload = getPayloadWithoutSignature(token);
    return (List<String>) payload.get("authorities", Collection.class);
  }

  private Claims getPayloadWithoutSignature(String token) {
    JwtParser parser = Jwts.parser().unsecured().build();
    String unsignedToken = deleteTokenSignature(token);
    return parser.parseUnsecuredClaims(unsignedToken).getPayload();
  }

  private String deleteTokenSignature(String token) {
    String emptySignature = "eyJ0eXAiOiJKV1QiLCJhbGciOiJub25lIn0";
    String[] splitToken = token.split(("\\."));
    return emptySignature + "." + splitToken[1] + ".";
  }
}
