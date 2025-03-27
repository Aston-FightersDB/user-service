package ru.aston.userservice.dtos.response;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.UUID;
import ru.aston.userservice.entity.User;

public record BetDto(
    String id,
    String eventId,
    String fighterId,
    UserResponseDto user,
    boolean success
) {

}
