package it.epicode.Mezzi.Tratta;


import jakarta.persistence.EntityManager;

public class TrattaDAO {
    EntityManager em;
    public TrattaDAO(EntityManager em){
        this.em = em;
    }
    public void insert(Tratta tratta){
        em.persist(tratta);
    }
    public void delete(Tratta tratta){
        em.remove(tratta);
    }
    public Tratta findById(long id){
        return em.find(Tratta.class, id);
    }

}
