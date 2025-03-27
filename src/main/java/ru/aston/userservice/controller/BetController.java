package ru.aston.userservice.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.aston.userservice.dtos.response.BetDto;

@RequestMapping("/user-service")
@Validated
public interface BetController {
  @GetMapping("/bets")
  ResponseEntity<List<BetDto>> getBets();
}
