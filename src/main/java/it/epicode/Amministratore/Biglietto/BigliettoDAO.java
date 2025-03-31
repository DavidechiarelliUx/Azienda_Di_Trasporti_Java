package it.epicode.Amministratore.Biglietto;

import jakarta.persistence.EntityManager;


public class BigliettoDAO {

private EntityManager em;

    public BigliettoDAO(EntityManager em) {
        this.em = em;
    }
    public Biglietto findById(long id){
        return em.find(Biglietto.class, id);
    }
    public void insert (Biglietto biglietto){
      em.persist(biglietto);
    }
    public void delete (Biglietto biglietto){

        em.remove(biglietto);
    }
}
