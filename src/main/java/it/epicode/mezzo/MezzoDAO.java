package it.epicode.mezzo;

import jakarta.persistence.EntityManager;

public class MezzoDAO {

    EntityManager em;

    public MezzoDAO(EntityManager em) {
        this.em = em;
    }
    public Mezzo findById(long id){
        return em.find(Mezzo.class, id);
    }
    public void insert (Mezzo mezzo){
        em.persist(mezzo);
    }
    public void delete (Mezzo mezzo){
        em.remove(mezzo);
    }
}
