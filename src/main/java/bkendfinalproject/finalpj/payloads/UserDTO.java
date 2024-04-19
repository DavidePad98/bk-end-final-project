package bkendfinalproject.finalpj.payloads;

import bkendfinalproject.finalpj.entities.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserDTO(
        @NotEmpty(message = "devi mettere un titolo all'evento")
        @Size(min = 2, max = 30, message = "il titolo deve contenere da un minimo di 2 caratteri and un massimo di 30")
        String username,

        @NotEmpty(message = "devi mettere un titolo all'evento")
        @Size(min = 2, max = 30, message = "il titolo deve contenere da un minimo di 2 caratteri and un massimo di 30")
        String password,

        @NotEmpty(message = "devi mettere un email")
        @Email(message = "L'email inserita non è valida")
        String email,

        UserRole role) {}
