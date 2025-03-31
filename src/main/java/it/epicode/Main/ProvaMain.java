package it.epicode.Main;

import it.epicode.Amministratore.Biglietto.Biglietto;
import it.epicode.Amministratore.Biglietto.PuntoEmissione.Distributori.Distributore;
import it.epicode.Amministratore.Biglietto.PuntoEmissione.Rivenditori.Rivenditore;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class ProvaMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Distributore distributore = new Distributore(null, "Roma", true);
        em.persist(distributore);

        Rivenditore rivenditore = new Rivenditore(null, "Milano",123, "Cosimo", true);
        em.persist(rivenditore);


        em.getTransaction().commit();

        //em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
