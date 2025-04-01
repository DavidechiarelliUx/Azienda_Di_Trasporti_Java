package it.epicode.Amministratore.Biglietto;

import jakarta.persistence.EntityManager;


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
    
    public void vidimaBiglietto(Long id) {
        Biglietto biglietto = findById(id);
        if (biglietto != null && !biglietto.isVidimato()) {
            biglietto.setVidimato(true);
            em.merge(biglietto);
            System.out.println("Biglietto " + id + " vidimato con successo!");
        } else {
            System.out.println("Errore: Biglietto non trovato o già vidimato.");
        }
    }
}
