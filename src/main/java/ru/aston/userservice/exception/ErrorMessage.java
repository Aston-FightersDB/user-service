package ru.aston.userservice.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

  String uri;

  String type;

  String message;

  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  LocalDateTime timestamp;

}


