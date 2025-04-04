package it.epicode.amministratore.classi.gestione_mezzi.tratta;


import it.epicode.amministratore.classi.gestione_mezzi.mezzo.Mezzo;
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

    public void collegaMezzoTratta(long idMezzo, long idTratta) {
        Mezzo mezzo = em.find(Mezzo.class, idMezzo);
        Tratta tratta = em.find(Tratta.class, idTratta);

        if (mezzo != null && tratta != null) {
            mezzo.setTratta(tratta);
            List<Mezzo> mezzi = new ArrayList<>();
            mezzi.add(mezzo);
            tratta.setMezzo(mezzi);
            em.merge(mezzo);
            em.merge(tratta);
        } else {
            if (mezzo == null) {
                System.out.println("Mezzo con ID " + idMezzo + " non trovato.");
            }
            if (tratta == null) {
                System.out.println("Tratta con ID " + idTratta + " non trovata.");
            }
        }
    }
    
    public void contaVolteTratta(long idTratta) {
        Tratta tratta = em.find(Tratta.class, idTratta);

        if (tratta != null) {
            tratta.setNumeroVolte(tratta.getNumeroVolte() + 1);
            em.merge(tratta);
            System.out.println("La tratta numero " + idTratta + " viene percorsa: " + tratta.getNumeroVolte() + " volte");
        } else {
            System.out.println("Tratta con ID " + idTratta + " non trovata.");
        }
    }
    
    public double calcolaMediaTempo(Long codiceIdentificativo) {
        Double media = em.createQuery(
                        "SELECT AVG(t.trattaPercorsa) FROM Tratta t WHERE t.codiceIdentificativo = :codice",
                        Double.class)
                .setParameter("codice", codiceIdentificativo)
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
