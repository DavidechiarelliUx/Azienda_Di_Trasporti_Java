package it.epicode.amministratore.classi.abbonamento;

import it.epicode.amministratore.classi.punto_di_emissione.PuntoDiEmissione;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;

public class AbbonamentoDAO {
    
    private EntityManager em;
    
    public AbbonamentoDAO(EntityManager em) {
        this.em = em;
    }
    
    public void insert(Abbonamento abbonamento) {
        em.merge(abbonamento);
    }
    
    public void delete(Abbonamento abbonamento) {
        em.remove(abbonamento);
    }
    
    public Abbonamento findById(long id) {
        return em.find(Abbonamento.class, id);
    }
    
    public Abbonamento findAbbonamentoAttivo(Long tesseraId) {
        TypedQuery<Abbonamento> query = em.createQuery(
                "SELECT a FROM Abbonamento a WHERE a.tessera.id = :tesseraId AND a.dataFine >= :oggi ORDER BY a.dataInizio DESC",
                Abbonamento.class
        );
        query.setParameter("tesseraId", tesseraId);
        query.setParameter("oggi", LocalDate.now());
        
        List<Abbonamento> abbonamenti = query.getResultList();
        return abbonamenti.isEmpty() ? null : abbonamenti.get(0);
    }
    
    public int countAbbonamentiByPeriodo(LocalDate inizio, LocalDate fine) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(a) FROM Abbonamento a WHERE a.dataInizio >= :inizio AND a.dataFine <= :fine", Long.class
        );
        query.setParameter("inizio", inizio);
        query.setParameter("fine", fine);
        return query.getSingleResult().intValue();
    }
    
    public int countAbbonamentiByPuntoDiEmissioneAndPeriodo(PuntoDiEmissione puntoEmissione, LocalDate inizio, LocalDate fine) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(a) FROM Abbonamento a WHERE a.puntoEmissione = :puntoEmissione AND a.dataInizio >= :inizio AND a.dataFine <= :fine", Long.class
        );
        query.setParameter("puntoEmissione", puntoEmissione);
        query.setParameter("inizio", inizio);
        query.setParameter("fine", fine);
        return query.getSingleResult().intValue();
    }
    
    public void merge(Abbonamento nuovoAbbonamento) {
        em.merge(nuovoAbbonamento);
    }
}
