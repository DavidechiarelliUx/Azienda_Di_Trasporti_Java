package it.epicode.classi.gestione_funzionalita_utente.utente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

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
    
    public void update(Utente nuovoUtente) {
        em.merge(nuovoUtente);
    }
    
    public Utente findByName(String nomeUtente, String cognomeUtente) {
        TypedQuery<Utente> query = em.createQuery(
                "SELECT u FROM Utente u WHERE u.nome = :nome AND u.cognome = :cognome", Utente.class);
        
        query.setParameter("nome", nomeUtente);
        query.setParameter("cognome", cognomeUtente);
        
        return query.getResultStream().findFirst().orElse(null);
    }
}
