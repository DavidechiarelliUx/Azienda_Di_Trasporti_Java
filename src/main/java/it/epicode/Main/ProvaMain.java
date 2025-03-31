package it.epicode.Main;

import it.epicode.Amministratore.Biglietto.Biglietto;
import it.epicode.Amministratore.Biglietto.BigliettoDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class ProvaMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();

        Biglietto biglietto = new Biglietto(null , "Roma", LocalDate.of(2023, 12, 12));

        em.getTransaction().begin();
        em.persist(biglietto);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
