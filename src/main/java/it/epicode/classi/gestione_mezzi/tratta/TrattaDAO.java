package it.epicode.classi.gestione_mezzi.tratta;


import it.epicode.classi.gestione_mezzi.mezzo.Mezzo;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

public class TrattaDAO {
    EntityManager em;
    public TrattaDAO(EntityManager em){
        this.em = em;
    }
    public void insert(Tratta tratta){
        em.persist(tratta);
    }
    public void delete(Tratta tratta){
        em.remove(tratta);
    }
    public Tratta findById(long id){
        return em.find(Tratta.class, id);
    }
    
    public void contaVolteTratta(long idTratta) {
        Tratta tratta = em.find(Tratta.class, idTratta);
        
        if (tratta != null && tratta.getMezzo() != null) {
            Long conteggio = em.createQuery(
                            "SELECT COUNT(t) FROM Tratta t WHERE t.mezzo.id = :mezzoId",
                            Long.class)
                    .setParameter("mezzoId", tratta.getMezzo().getId())
                    .getSingleResult();
            
            tratta.setNumeroVolte(conteggio.intValue());
            
            em.merge(tratta);
            
            System.out.println("La tratta con ID " + idTratta + " e mezzo ID " + tratta.getMezzo().getId()
                    + " viene percorsa: " + tratta.getNumeroVolte() + " volte");
        } else {
            System.out.println("Tratta con ID " + idTratta + " non trovata o non ha un mezzo associato.");
        }
    }
    
    public double calcolaMediaTempo(Long mezzoId) {
        Double media = em.createQuery(
                        "SELECT AVG(t.trattaPercorsa) FROM Tratta t WHERE t.mezzo.id = :mezzoId",
                        Double.class)
                .setParameter("mezzoId", mezzoId)
                .getSingleResult();
        
        return (media != null) ? media : 0;
    }
    
    public String partenzaTratta(long idTratta) {
        Tratta tratta = findById(idTratta);

        if (tratta != null) {
            return tratta.getZonaPartenza();
        } else {
            return "Tratta non trovata";
        }
    }

    public String capolineaTratta(long idTratta) {
        Tratta tratta = findById(idTratta);

        if (tratta != null) {
            return tratta.getCapolinea();
        } else {
            return "Tratta non trovata";
        }
    }
}
