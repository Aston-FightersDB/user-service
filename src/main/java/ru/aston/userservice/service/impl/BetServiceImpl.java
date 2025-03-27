package ru.aston.userservice.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.userservice.dtos.kafka.EventFinishedDto;
import ru.aston.userservice.entity.Bet;
import ru.aston.userservice.repository.BetRepository;
import ru.aston.userservice.service.BetService;

@Service
@RequiredArgsConstructor
public class BetServiceImpl implements BetService {
  private final BetRepository repository;

  @Override
  public void submitBet(EventFinishedDto resultCardRequestDto) {
    List<Bet> bets = repository.findBetsByEventId(resultCardRequestDto.getEventId());
    if (!bets.isEmpty()) {
      bets.forEach(bet -> {
        boolean isWin = resultCardRequestDto.getWinnerId() == bet.getFighterId();
        bet.setSuccess(isWin);
      });
      repository.saveAll(bets);
    }
  }
}
