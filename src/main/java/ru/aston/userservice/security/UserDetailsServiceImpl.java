package ru.aston.userservice.security;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.aston.userservice.entity.User;
import ru.aston.userservice.exception.AppException;
import ru.aston.userservice.exception.EnumException;
import ru.aston.userservice.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;


  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

    User user =
        userRepository.findByUsername(login).orElseThrow(() ->
                    new AppException(EnumException.NOT_FOUND,
                    String.format("User with login %s has not been found!", login)));

    return new UserDetailsImpl(user);
  }
}
