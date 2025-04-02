package it.epicode.Mezzi;

import it.epicode.Mezzi.StatoMezzo.MezzoInManutenzione;
import it.epicode.Mezzi.StatoMezzo.MezzoInManutenzioneDAO;
import it.epicode.Mezzi.StatoMezzo.MezzoInServizio;
import it.epicode.Mezzi.StatoMezzo.MezzoInServizioDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class MainProvaMezzo {
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();
        
        MezzoInManutenzioneDAO mezzoInManutenzioneDAO = new MezzoInManutenzioneDAO(em);
        MezzoInServizioDAO mezzoInServizioDAO = new MezzoInServizioDAO(em);
        
        em.getTransaction().begin();
        
        mezzoInManutenzioneDAO.insert(new MezzoInManutenzione(30, TipoMezzo.TRAM, LocalDate.now(), LocalDate.of(2025, 10, 10)));
        mezzoInServizioDAO.insert(new MezzoInServizio(40, TipoMezzo.TRAM, LocalDate.now(), LocalDate.of(2025, 10, 10)));
        mezzoInManutenzioneDAO.insert(new MezzoInManutenzione(20, TipoMezzo.AUTOBUS, LocalDate.now(), LocalDate.of(2025, 12, 31)));
        mezzoInServizioDAO.insert(new MezzoInServizio(10, TipoMezzo.AUTOBUS, LocalDate.now(), LocalDate.of(2025, 1, 1)));
        
        em.getTransaction().commit();
        
        em.close();
        emf.close();
    }
}
