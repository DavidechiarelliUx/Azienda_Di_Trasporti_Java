package it.epicode.Amministratore.Biglietto.PuntoEmissione.Distributori;

import it.epicode.Amministratore.Biglietto.PuntoEmissione.PuntoDiEmissione;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

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
    
    public List<Distributore> getDistributori() {
        String query = "SELECT d FROM Distributore d";
        TypedQuery<Distributore> typedQuery = em.createQuery(query, Distributore.class);
        return typedQuery.getResultList();
    }
}
