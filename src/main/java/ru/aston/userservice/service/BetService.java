package ru.aston.userservice.service;

import ru.aston.userservice.dtos.kafka.EventFinishedDto;

public interface BetService {
  void submitBet(EventFinishedDto resultCardRequestDto);
}
