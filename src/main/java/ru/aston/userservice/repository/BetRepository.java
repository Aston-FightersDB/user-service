package ru.aston.userservice.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.aston.userservice.entity.Bet;

public interface BetRepository extends JpaRepository<Bet, UUID> {

  List<Bet> findBetsByEventId(UUID eventId);
}
