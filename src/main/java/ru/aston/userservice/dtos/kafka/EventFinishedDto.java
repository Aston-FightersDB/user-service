package ru.aston.userservice.dtos.kafka;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.kafka.common.protocol.types.Field.Str;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EventFinishedDto{
  private UUID eventId;
  private UUID winnerId;
  private UUID loserId;

}
