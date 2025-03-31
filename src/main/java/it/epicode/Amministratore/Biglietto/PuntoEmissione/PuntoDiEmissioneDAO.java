package it.epicode.Amministratore.Biglietto.PuntoEmissione;

import it.epicode.Amministratore.Biglietto.Biglietto;
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
