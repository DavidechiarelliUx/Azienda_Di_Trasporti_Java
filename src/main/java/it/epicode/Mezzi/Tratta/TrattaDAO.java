package it.epicode.Mezzi.Tratta;


import it.epicode.Mezzi.Mezzo;
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
            List<Mezzo> mezzi = new ArrayList<>(); // Crea una lista MUTABILE
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
}
