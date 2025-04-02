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
        Tratta tratta6 = new Tratta("Via Giulio Cesare", "Stazione di Vittorio Veneto", 30, 10, TipoTratta.SERA, 4L);
        //public Tratta(String zonaPartenza, String capolinea, int tempoMedioPercorrenza, int trattaPercorsa, List<Mezzo > mezzo, TipoTratta tipoTratta, int numeroVolte, Long codiceIdentificativo){
        em.persist(tratta);
        em.persist(tratta2);
        em.persist(tratta3);
        em.persist(tratta4);
        em.persist(tratta5);
        em.persist(tratta6);




        trattaDAO.contaVolteTratta(1);
        trattaDAO.contaVolteTratta(2);
        trattaDAO.contaVolteTratta(3);
        trattaDAO.contaVolteTratta(4);



        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
