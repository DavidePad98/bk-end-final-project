package bkendfinalproject.finalpj.payloads;

import java.time.LocalDateTime;

public record ErrorResponseDTO(String message, LocalDateTime timestamp) {
}
