package it.epicode.Mezzi;

import it.epicode.Mezzi.StatoMezzo.MezzoInManutenzione;
import it.epicode.Mezzi.StatoMezzo.MezzoInManutenzioneDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;


public class MainProvaMezzo {
    public static void main(String[] args) {

        EntityManagerFactory  emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();
        /*Mezzo mezzo = new Mezzo(null, 200, TipoMezzo.AUTOBUS );*/
        em.getTransaction().begin();
        /*MezzoDAO mezzoDAO = new MezzoDAO(em);*/
        MezzoInManutenzioneDAO mezzoInManutenzioneDAO = new MezzoInManutenzioneDAO(em);

  /*      mezzoDAO.insert(mezzo);*/
        mezzoInManutenzioneDAO.insert(new MezzoInManutenzione(null, 30, TipoMezzo.TRAM, LocalDate.now(), LocalDate.of(2025, 10, 10) ));
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
