package services;

import config.HibernateConfig;
import model.Adestrador;
import model.Pokedex;
import model.Pokemon;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class PokemonServices {
    public void InsertarPokemon(String nome, Date nacemento, Adestrador adestrador, Pokedex pokedex) {
        try (Session sesion = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = sesion.beginTransaction();
            Pokemon pokemon = new Pokemon(nome, nacemento, adestrador, pokedex);
            sesion.save(pokemon);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Pokemon> Leer () {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("FROM Pokemon", Pokemon.class).getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void Actualizar (Long id, String Nome, Date nacemento, Pokedex pokedexEntry, Adestrador adestrador) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Pokemon actualizar = session.get(Pokemon.class, id);
            if (actualizar != null) {
                actualizar.setNome(Nome);
                actualizar.setNacemento(nacemento);
                actualizar.setAdestrador(adestrador);
                actualizar.setPokedex(pokedexEntry);

                session.update(actualizar);
            }
            else {
                System.out.println("No se encontró ningún pokemon");
            }
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void Eliminar (Long id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Pokemon pokemon = session.get(Pokemon.class, id);
            if (pokemon != null) {
                session.delete(pokemon);
            }
            else {
                System.out.println("No se encontró el pokemon");
            }
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Pokemon leerPorId(Long id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.get(Pokemon.class, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Pokemon> leerPorNome(String nome) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("FROM Pokemon WHERE nome = :nome", Pokemon.class)
                    .setParameter("nome", nome)
                    .list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
