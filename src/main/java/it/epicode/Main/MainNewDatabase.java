package it.epicode.Main;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import it.epicode.Amministratore.Biglietto.PuntoEmissione.Distributori.Distributore;
import it.epicode.Amministratore.Biglietto.PuntoEmissione.Rivenditori.Rivenditore;
import it.epicode.Amministratore.Utente.Utente;
import it.epicode.Amministratore.Abbonamento.Tessera.Tessera;

import java.time.LocalDate;

public class MainNewDatabase {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        Utente utente1 = new Utente("Giovanni", "Rossi", null);
        Utente utente2 = new Utente("Maria", "Verdi", null);
        
        Tessera tessera1 = new Tessera();
        tessera1.setDataScadenza(LocalDate.now().plusYears(1));
        em.persist(tessera1);
        utente1.setTessera(tessera1);
        
        Tessera tessera2 = new Tessera();
        tessera2.setDataScadenza(LocalDate.now().plusYears(1));
        em.persist(tessera2);
        utente2.setTessera(tessera2);
        
        em.persist(utente1);
        em.persist(utente2);
        
        Rivenditore rivenditore1 = new Rivenditore(1L, "Milano", 101, "Rivenditore A");
        Rivenditore rivenditore2 = new Rivenditore(2L, "Roma", 102, "Rivenditore B");
        
        em.merge(rivenditore1);
        em.merge(rivenditore2);
        
        Distributore distributore1 = new Distributore(3L, "Napoli", true);
        Distributore distributore2 = new Distributore(4L, "Torino", false);
        
        em.merge(distributore1);
        em.merge(distributore2);
        
        em.getTransaction().commit();
        
        System.out.println("Dati popolati nel database con successo!");
        
        em.close();
        emf.close();
    }
}
