package bkendfinalproject.finalpj.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record EventsDTO(

        @NotEmpty(message = "devi mettere un titolo all'evento")
        @Size(min = 2, max = 30, message = "il titolo deve contenere da un minimo di 2 caratteri and un massimo di 30")
        String titolo,

        @NotEmpty(message = "devi mettere una descrizione")
        @Size(min = 2, max = 30, message = "la descrizione deve contenere da un minimo di 2 caratteri and un massimo di 30")
        String descrizione,

        @NotNull(message = "Devi inserire una data per l'evento")
        LocalDate data,

        @NotEmpty(message = "devi mettere una descrizione")
        @Size(min = 2, max = 30, message = "la descrizione deve contenere da un minimo di 2 caratteri and un massimo di 30")
        String luogo,

        @NotNull(message = "Il numero di posti disponibili non può essere nullo")
        int postiDisponibili

) {}
