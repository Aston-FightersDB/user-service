package ru.aston.userservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.protocol.types.Field.Str;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.aston.userservice.dtos.kafka.EventFinishedDto;
import ru.aston.userservice.service.BetService;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {
  private final BetService betService;

  private final ObjectMapper objectMapper;

  @KafkaListener(topics = "event-finished", groupId = "user-service-group")
  public void consumeEvent(ConsumerRecord<String, String> record) {
    try {
      EventFinishedDto requestDto = objectMapper.readValue(record.value(),
          EventFinishedDto.class);
      if (requestDto.getEventId() == null || requestDto.getLoserId() == null
          || requestDto.getWinnerId() == null) {
        return;
      }
      betService.submitBet(requestDto);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }
}
