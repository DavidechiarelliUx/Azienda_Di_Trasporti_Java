package it.epicode.Amministratore.Biglietto;

import it.epicode.Amministratore.Biglietto.PuntoEmissione.PuntoDiEmissione;
import it.epicode.Mezzi.MezzoDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;

public class BigliettoDAO {
    private EntityManager em;
    
    public BigliettoDAO(EntityManager em) {
        this.em = em;
    }
    
    public void insert(Biglietto biglietto) {
        em.persist(biglietto);
    }
    
    public Biglietto findById(Long id) {
        return em.find(Biglietto.class, id);
    }

    public void update (Biglietto biglietto){
        em.merge(biglietto);
    }
    
    public int countBigliettiByPeriodo(LocalDate inizio, LocalDate fine) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(b) FROM Biglietto b WHERE b.dataDiEmissione >= :inizio AND b.dataDiEmissione <= :fine", Long.class
        );
        query.setParameter("inizio", inizio);
        query.setParameter("fine", fine);
        
        Long result = query.getSingleResult();
        if (result != null) {
            return result.intValue();
        }
        return 0;
    }
    
    public int countBigliettiByPuntoDiEmissioneAndPeriodo(PuntoDiEmissione puntoEmissione, LocalDate inizio, LocalDate fine) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(b) FROM Biglietto b WHERE b.puntoDiEmissione = :puntoDiEmissione AND b.dataDiEmissione >= :inizio AND b.dataDiEmissione <= :fine", Long.class
        );
        query.setParameter("puntoDiEmissione", puntoEmissione);
        query.setParameter("inizio", inizio);
        query.setParameter("fine", fine);
        
        Long result = query.getSingleResult();
        if (result != null) {
            return result.intValue();
        }
        return 0;
    }
    
    public void vidimaBiglietto(Long id) {Biglietto biglietto = findById(id);if (biglietto != null && !biglietto.isVidimato()) {
       biglietto.setVidimato(true);
       em.merge(biglietto);
       System.out.println("Biglietto " + id + " vidimato con successo!");
   } else {
       System.out.println("Errore: Biglietto non trovato o già vidimato.");
      }
    }


}
