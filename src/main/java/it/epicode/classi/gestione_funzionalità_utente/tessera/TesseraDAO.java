package it.epicode.classi.gestione_funzionalità_utente.tessera;

import jakarta.persistence.EntityManager;

public class TesseraDAO {
    
    private EntityManager em;
    
    public TesseraDAO(EntityManager em) {
        this.em = em;
    }
    
    public void insert(Tessera tessera) {
        em.persist(tessera);
    }
    
    public void delete(Tessera tessera) {
        em.remove(tessera);
    }
    
    public Tessera findById(int id) {
        return em.find(Tessera.class, id);
    }
}
