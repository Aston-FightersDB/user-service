package ru.aston.userservice.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.aston.userservice.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {

  Optional<User> findByUsername(String login);

  boolean existsByUsernameIgnoreCase(String username);
}
