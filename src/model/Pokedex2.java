package model;

import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "pokedex")

public class Pokedex2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "nome", nullable = false, length = 50)
    public String nome;

    @Column(name = "peso", scale = 10, precision = 2)
    public BigDecimal peso;

    @Column(name = "misc", columnDefinition = "TEXT")
    public String misc;


}
