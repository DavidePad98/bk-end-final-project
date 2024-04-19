package bkendfinalproject.finalpj.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "events")
public class Events {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "events_id")
    private Long id;
    private String titolo;
    private String descrizione;
    private LocalDate data;
    private String luogo;
    private int postiDisponibili;
    @OneToMany(mappedBy = "evento_id")
    private List<Prenotations> prenotazioni = new ArrayList<>();

    public Events(String titolo, String descrizione, LocalDate data, String luogo, int postiDisponibili) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.data = data;
        this.luogo = luogo;
        this.postiDisponibili = postiDisponibili;
    }
}
