package it.epicode.Mezzi.Tratta;

import it.epicode.Mezzi.Mezzo;
import it.epicode.Mezzi.TipoMezzo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class ProvaTratta {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();

        TrattaDAO trattaDAO = new TrattaDAO(em);
        em.getTransaction().begin();
        Tratta tratta = new Tratta("Piazza del Popolo", "Stazione Centrale", 30, 50,  TipoTratta.MATTINA, 1L);
        Tratta tratta2 = new Tratta("Via Vittorio Veneto", "Via Roma", 30, 10, TipoTratta.POMERIGGIO ,2L);
        
        Tratta tratta3 = new Tratta("Via Giulio Cesare", "Stazione di Vittorio Veneto", 20, 40, TipoTratta.MATTINA ,  3L);
        Tratta tratta4 = new Tratta("Via Giulio Cesare", "Stazione di Vittorio Veneto", 30, 13,  TipoTratta.POMERIGGIO, 3L);
        Tratta tratta5 = new Tratta("Via Giulio Cesare", "Stazione di Vittorio Veneto", 30, 8, TipoTratta.SERA, 3L);
        
        Tratta tratta6 = new Tratta("Via Jacopo Sannazzaro", "Stazione della Vittoria", 30, 20, TipoTratta.SERA, 4L);
        Tratta tratta7 = new Tratta("Via Jacopo Sannazzaro", "Stazione della Vittoria", 30, 10, TipoTratta.MATTINA, 4L);
        
        
        em.persist(tratta);
        em.persist(tratta2);
        em.persist(tratta3);
        em.persist(tratta4);
        em.persist(tratta5);
        em.persist(tratta6);
        em.persist(tratta7);
        
        
        trattaDAO.contaVolteTratta(1);
        trattaDAO.contaVolteTratta(2);
        trattaDAO.contaVolteTratta(3);
        trattaDAO.contaVolteTratta(4);
        
        System.out.println("Media tempo tratta 1: " + trattaDAO.calcolaMediaTempo(1L));
        System.out.println("Media tempo tratta 2: " + trattaDAO.calcolaMediaTempo(2L));
        System.out.println("Media tempo tratta 3: " + trattaDAO.calcolaMediaTempo(3L));
        System.out.println("Media tempo tratta 4: " + trattaDAO.calcolaMediaTempo(4L));
        
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
