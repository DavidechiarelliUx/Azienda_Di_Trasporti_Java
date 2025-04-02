package it.epicode.Mezzi.Tratta;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ProvaTratta {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        Tratta tratta = new Tratta("Piazza del Popolo", "Stazione Centrale", 30, 50, null, TipoTratta.MATTINA, 1L);
        Tratta tratta2 = new Tratta("Via Vittorio Veneto", "Via Roma", 30, 50, null, TipoTratta.POMERIGGIO , 2L);
        Tratta tratta3 = new Tratta("Via Giulio Cesare", "Stazione di Vittorio Veneto", 30, 50, null, TipoTratta.MATTINA , 3L);
        Tratta tratta4 = new Tratta("Via Giulio Cesare", "Stazione di Vittorio Veneto", 30, 50, null, TipoTratta.POMERIGGIO, 3L);
        Tratta tratta5 = new Tratta("Via Giulio Cesare", "Stazione di Vittorio Veneto", 30, 50, null, TipoTratta.SERA, 3L);
        
        em.persist(tratta);
        em.persist(tratta2);
        em.persist(tratta3);
        em.persist(tratta4);
        em.persist(tratta5);
        
        em.getTransaction().commit();
    }
}
