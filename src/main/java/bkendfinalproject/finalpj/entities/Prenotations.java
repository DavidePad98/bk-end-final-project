package bkendfinalproject.finalpj.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Prenotations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Events evento;
    @ManyToOne
    @JoinColumn(name = "utente_id")
    private User utente;
    private int postiPrenotati;

    public Prenotations() {
    }
}
