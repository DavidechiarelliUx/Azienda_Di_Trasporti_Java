package it.epicode.Amministratore.Biglietto.PuntoEmissione.Distributori;

import it.epicode.Amministratore.Biglietto.PuntoEmissione.PuntoDiEmissione;
import jakarta.persistence.EntityManager;

public class DistributoreDAO {

    private EntityManager em;

    public DistributoreDAO(EntityManager em) {
        this.em = em;
    }
    public Distributore findById(long id){
        return em.find(Distributore.class, id);
    }

    public void insert(Distributore distributore){
        em.persist(distributore);
    }

    public void delete(Distributore distributore){
        em.remove(distributore);
    }
}
