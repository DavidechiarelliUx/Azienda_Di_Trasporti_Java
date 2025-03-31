package it.epicode.Amministratore.Utente;

import jakarta.persistence.EntityManager;

public class UtenteDAO {

    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    private EntityManager em;

    public void insert (Utente utente){
        em.persist(utente);
    }

    public void delete (Utente utente){
        em.remove(utente);
    }

    public Utente findById(long id){
        return em.find(Utente.class, id);
    }
}
