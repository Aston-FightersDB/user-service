package ru.aston.userservice.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import ru.aston.userservice.entity.Role;
import ru.aston.userservice.entity.User;
import ru.aston.userservice.exception.AppException;
import ru.aston.userservice.exception.EnumException;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenService {
  @Value("${jwt.key.filename.private}")
  private String PRIVATE_KEY_FILENAME;
  @Value("${jwt.key.filename.public}")
  private String PUBLIC_KEY_FILENAME;


  public String generateAuthToken(User user) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("login", user.getUsername());
    claims.put("authorities", user.getRoles().stream()
        .map(Role::getName)
        .toList());
      return createToken(claims, user);
  }

  public UUID getUserId(String token) {
    final Claims claims = extractAllClaims(token);
    return UUID.fromString(claims.getSubject());

  }

  public List<String> getAuthorities(String token) {
    final Claims claims = extractAllClaims(token);
    return claims.get("authorities", List.class);

  }

  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public Boolean isTokenExpired(String token) {
    try {
      return extractExpiration(token).before(new Date());
    } catch (ExpiredJwtException e) {
      return true;
    } catch (Exception e) {
      throw new AppException(EnumException.BAD_REQUEST,
              String.format("Not able to extract expiration from jwt %s!", token.substring(7, 12)));
    }
  }

  private String createToken(Map<String, Object> claims, User user) {
    return Jwts.builder()
            .claims(claims)
            .subject(String.valueOf(user.getUserId()))
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) // 30 min
            .signWith(getPrivateKey(), Jwts.SIG.RS256).compact();
  }

  private Claims extractAllClaims(String token) {
    return Jwts
            .parser()
            .verifyWith(getPublicKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
  }

  private PrivateKey getPrivateKey() {
    String privateKey = readKeyFromFile(PRIVATE_KEY_FILENAME);
    assert privateKey != null;
    String normalizedKey = normalizeKey(privateKey);

    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(normalizedKey));

    KeyFactory kf;
    try {

      kf = KeyFactory.getInstance("RSA");
      return kf.generatePrivate(keySpec);

    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
  }

  private PublicKey getPublicKey() {
    String publicKey = readKeyFromFile(PUBLIC_KEY_FILENAME);
    assert publicKey != null;
    String normalizedKey = normalizeKey(publicKey);

    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(normalizedKey));

    KeyFactory kf;
    try {

      kf = KeyFactory.getInstance("RSA");
      return kf.generatePublic(keySpec);

    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
  }

  private String normalizeKey(String key) {
    String result = key.replace("-----END PRIVATE KEY-----", "");
    result = result.replace("-----BEGIN PRIVATE KEY-----", "");
    result = result.replace("-----BEGIN PUBLIC KEY-----", "");
    result = result.replace("-----END PUBLIC KEY-----", "");
    result = result.replace("\n", "");
    return result;
  }

  private String readKeyFromFile(String keyFileName) {
    ClassPathResource resource = new ClassPathResource(keyFileName);
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
      return reader.lines().collect(Collectors.joining());
    } catch (IOException e) {
      log.error("Key read error");
      throw new RuntimeException(e);
    }
  }
}
