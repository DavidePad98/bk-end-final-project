package bkendfinalproject.finalpj.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "prenot")
public class Prenotations {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "utente_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Events evento;

    public Prenotations(User user, Events event) {
        this.user = user;
        this.evento = evento;
    }
}
