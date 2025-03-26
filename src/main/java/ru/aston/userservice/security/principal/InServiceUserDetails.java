package ru.aston.userservice.security.principal;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InServiceUserDetails {
  private UUID userId;
  private String token;
  private List<String> authorities;

  public InServiceUserDetails(UUID userId, String token) {
    this.userId = userId;
    this.token = token;
  }
}
