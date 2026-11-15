package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Pokemon")

public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "nome", nullable = false, length = 50)
    public String nome;

    @Column(name = "nacemento")
    @Temporal(TemporalType.DATE)
    public Date nacemento;

    @ManyToOne
    @JoinColumn(name = "Adestrador")
    Adestrador adestrador;

    @OneToOne
    @JoinColumn(name = "PokedexEntry")
    Pokedex pokedex;

    public Pokemon(){}

    public Pokemon(Long id, String nome, Date nacemento, Adestrador adestrador, Pokedex pokedex) {
        this.id = id;
        this.nome = nome;
        this.nacemento = nacemento;
        this.adestrador = adestrador;
        this.pokedex = pokedex;
    }

    public Pokemon (String nome, Date nacemento, Adestrador adestrador, Pokedex pokedex) {
        this.nome = nome;
        this.nacemento = nacemento;
        this.adestrador = adestrador;
        this.pokedex = pokedex;
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

    public Adestrador getAdestrador() {
        return adestrador;
    }

    public void setAdestrador(Adestrador adestrador) {
        this.adestrador = adestrador;
    }

    public Pokedex getPokedex() {
        return pokedex;
    }

    public void setPokedex(Pokedex pokedex) {
        this.pokedex = pokedex;
    }

    @Override
    public String toString() {
        return "id " + id + " nome " + nome + " nacemento " + nacemento + " adestrador " + adestrador + " entrada en la pokedex " + pokedex;
    }
}
