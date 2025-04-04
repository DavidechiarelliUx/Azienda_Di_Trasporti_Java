package it.epicode.amministratore.biglietto.punto_di_emissione;

import jakarta.persistence.EntityManager;

public class PuntoDiEmissioneDAO {

    private EntityManager em;

    public PuntoDiEmissioneDAO(EntityManager em) {
        this.em = em;
    }
    public PuntoDiEmissione findById(long id){
        return em.find(PuntoDiEmissione.class, id);
    }

    public void insert(PuntoDiEmissione puntoDiEmissione){
        em.persist(puntoDiEmissione);
    }

    public void delete(PuntoDiEmissione puntoDiEmissione){
        em.remove(puntoDiEmissione);
    }

}
