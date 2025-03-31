package it.epicode.Amministratore.Biglietto.PuntoEmissione.Rivenditori;

import it.epicode.Amministratore.Biglietto.PuntoEmissione.Distributori.Distributore;
import jakarta.persistence.EntityManager;

public class RivenditoreDAO {

    private EntityManager em;

    public RivenditoreDAO(EntityManager em) {
        this.em = em;
    }
    public Rivenditore findById(long id){
        return em.find(Rivenditore.class, id);
    }

    public void insert(Rivenditore rivenditore){
        em.persist(rivenditore);
    }

    public void delete(Rivenditore rivenditore){
        em.remove(rivenditore);
    }
}
