package it.epicode.amministratore.classi.gestione_mezzi.stato_mezzo;

import jakarta.persistence.EntityManager;

public class MezzoInManutenzioneDAO {

    EntityManager  em;

    public MezzoInManutenzioneDAO(EntityManager em) {
        this.em = em;
    }

    public MezzoInManutenzione findById(long id){
        return em.find(MezzoInManutenzione.class, id);
    }

    public void insert (MezzoInManutenzione mezzoInManutenzione){
        em.persist(mezzoInManutenzione);
    }

    public void delete (MezzoInManutenzione mezzoInManutenzione){
        em.remove(mezzoInManutenzione);
    }
}
