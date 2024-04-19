package bkendfinalproject.finalpj.payloads;

import jakarta.validation.constraints.NotNull;

public record PrenotazioniDTO(

        @NotNull(message = "User ID field must not be null.")
        long userId,
        @NotNull(message = "Event ID field must not be null.")
        long eventId) {
}
