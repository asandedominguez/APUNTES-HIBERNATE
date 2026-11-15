package model;

import javax.naming.Name;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Pokedex")

public class Pokedex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "nome", nullable = false, length = 50)
    public String nome;

    @Column(name = "peso", precision = 10, scale = 2)
    public BigDecimal peso;

    @Column(name = "misc", columnDefinition = "TEXT")
    public String misc;

    @OneToOne(mappedBy = "pokedex")
    public Pokemon pokemon;

    public Pokedex(){}

    public Pokedex(Long id, String nome, BigDecimal peso, String misc) {
        this.id = id;
        this.nome = nome;
        this.peso = peso;
        this.misc = misc;
    }

    public Pokedex(String nome, BigDecimal peso, String misc) {
        this.nome = nome;
        this.peso = peso;
        this.misc = misc;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public String getMisc() {
        return misc;
    }

    public void setMisc(String misc) {
        this.misc = misc;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    @Override
    public String toString() {
        return "id" + getId() + " nome" + getNome() + " peso" + getPeso() + " descripción" + getMisc();
    }
}
