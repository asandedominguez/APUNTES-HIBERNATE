package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pokemon")

public class Pokemon2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "nome", nullable = false, length = 50)
    public String nome;

    @Column(name = "nacemento")
    @Temporal(TemporalType.DATE)
    public Date nacemento;

    @ManyToOne
    @JoinColumn(name = "PokedexEntry")
    Pokedex pokedex;

    @ManyToOne
    @JoinColumn(name = "Adestrador")
    Adestrador adestrador;
}
