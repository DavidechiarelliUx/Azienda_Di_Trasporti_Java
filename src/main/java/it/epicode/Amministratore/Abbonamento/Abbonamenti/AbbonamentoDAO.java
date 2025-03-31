package it.epicode.Amministratore.Abbonamento.Abbonamenti;

import jakarta.persistence.EntityManager;

public class AbbonamentoDAO {


    private EntityManager em;
    public AbbonamentoDAO(EntityManager em) {
        this.em = em;
    }

    public void insert (Abbonamento abbonamento) {
        em.persist(abbonamento);
    }
    public void delete (Abbonamento abbonamento) {
        em.remove(abbonamento);
    }
    public Abbonamento findById(long id){
        return em.find(Abbonamento.class, id);
    }
}
