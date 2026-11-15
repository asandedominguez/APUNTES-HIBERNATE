package model;

import jdk.jfr.DataAmount;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Adestrador")

public class Adestrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "nome", nullable = false, length = 50)
    public String nome;

    @Column(name = "nacemento")
    @Temporal(TemporalType.DATE)
    public Date nacemento;

    @OneToMany(mappedBy = "adestrador", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    List<Pokemon> pokemonList;

    public Adestrador(){}

    public Adestrador(Long id, String nome, Date nacemento) {
        this.id = id;
        this.nome = nome;
        this.nacemento = nacemento;
    }

    public Adestrador(String nome, Date nacemento) {
        this.nome = nome;
        this.nacemento = nacemento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getNacemento() {
        return nacemento;
    }

    public void setNacemento(Date nacemento) {
        this.nacemento = nacemento;
    }

    public List<Pokemon> getPokemonList() {
        return pokemonList;
    }

    public void setPokemonList(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

    @Override
    public String toString() {
        return "Id " + id + " nome " + nome + " nacemento " + nacemento;
    }
}
