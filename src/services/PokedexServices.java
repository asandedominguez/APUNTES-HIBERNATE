package services;

import config.HibernateConfig;
import model.Pokedex;
import net.bytebuddy.asm.MemberSubstitution;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;

public class PokedexServices {
    public void InsertarPokedex(String nome, BigDecimal peso, String misc) {
        try (Session sesion = HibernateConfig.getSessionFactory().openSession()){
            Transaction transaction = sesion.beginTransaction();
            Pokedex pokedex = null;
            pokedex.set

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
