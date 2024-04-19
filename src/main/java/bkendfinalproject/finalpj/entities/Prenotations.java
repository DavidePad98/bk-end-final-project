package bkendfinalproject.finalpj.entities;

import jakarta.persistence.*;

@Entity
public class Prenotations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Events evento;
    @ManyToOne
    private User utente;
}
