package it.epicode.classi.gestione_funzionalita_utente.punto_di_emissione.distributore;

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
