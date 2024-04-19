package bkendfinalproject.finalpj.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record EventsDTO(

        @NotEmpty(message = "devi mettere un titolo all'evento")
        @Size(min = 2, max = 30, message = "il titolo deve contenere da un minimo di 2 caratteri and un massimo di 30")
        String titolo,

        @NotEmpty(message = "devi mettere una descrizione")
        @Size(min = 2, max = 30, message = "la descrizione deve contenere da un minimo di 2 caratteri and un massimo di 30")
        String descrizione,

        @NotEmpty(message = "devi mettere una data all'evento")
        @Size(min = 2, max = 30, message = "il titolo deve contenere da un minimo di 2 caratteri and un massimo di 30")
        LocalDate data,

        @NotEmpty(message = "devi mettere una descrizione")
        @Size(min = 2, max = 30, message = "la descrizione deve contenere da un minimo di 2 caratteri and un massimo di 30")
        String luogo,

        @NotEmpty(message = "devi mettere un titolo all'evento")
        @Size(min = 2, max = 30, message = "il titolo deve contenere da un minimo di 2 caratteri and un massimo di 30")
        int postiDisponibili

) {}
