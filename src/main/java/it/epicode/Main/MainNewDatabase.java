package it.epicode.Main;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import it.epicode.Amministratore.Biglietto.Biglietto;
import it.epicode.Amministratore.Biglietto.PuntoEmissione.Distributori.Distributore;
import it.epicode.Amministratore.Biglietto.PuntoEmissione.Rivenditori.Rivenditore;
import it.epicode.Amministratore.Abbonamento.Abbonamenti.Abbonamento;
import it.epicode.Amministratore.Abbonamento.Tessera.Tessera;
import it.epicode.Amministratore.Abbonamento.Abbonamenti.Tipologia;
import it.epicode.Amministratore.Utente.Utente;

import java.time.LocalDate;

public class MainNewDatabase {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        Utente utente1 = new Utente("Giovanni", "Rossi", null);
        Utente utente2 = new Utente("Maria", "Verdi", null);
        Utente utente3 = new Utente("Luca", "Bianchi", null);
        
        Tessera tessera1 = new Tessera();
        tessera1.setDataScadenza(LocalDate.now().plusYears(1));
        em.persist(tessera1);
        utente1.setTessera(tessera1);
        
        Tessera tessera2 = new Tessera();
        tessera2.setDataScadenza(LocalDate.now().plusYears(1));
        em.persist(tessera2);
        utente2.setTessera(tessera2);
        
        Tessera tessera3 = new Tessera();
        tessera3.setDataScadenza(LocalDate.now().plusYears(1));
        em.persist(tessera3);
        utente3.setTessera(tessera3);
        
        em.persist(utente1);
        em.persist(utente2);
        em.persist(utente3);
        
        Rivenditore rivenditore1 = new Rivenditore(1L, "Milano", 101, "Rivenditore A");
        Rivenditore rivenditore2 = new Rivenditore(2L, "Roma", 102, "Rivenditore B");
        Rivenditore rivenditore3 = new Rivenditore(3L, "Firenze", 103, "Rivenditore C");
        
        em.merge(rivenditore1);
        em.merge(rivenditore2);
        em.merge(rivenditore3);
        
        Distributore distributore1 = new Distributore(4L, "Napoli", true);
        Distributore distributore2 = new Distributore(5L, "Torino", false);
        Distributore distributore3 = new Distributore(6L, "Bologna", true);
        
        em.merge(distributore1);
        em.merge(distributore2);
        em.merge(distributore3);
        
        Biglietto biglietto1 = new Biglietto(distributore1, LocalDate.now());
        Biglietto biglietto2 = new Biglietto(distributore3, LocalDate.now().minusDays(1));
        Biglietto biglietto3 = new Biglietto(rivenditore1, LocalDate.now().minusDays(2));
        
        em.persist(biglietto1);
        em.persist(biglietto2);
        em.persist(biglietto3);
        
        em.getTransaction().commit();
        
        System.out.println("Dati popolati nel database con successo!");
        
        em.close();
        emf.close();
    }
}
