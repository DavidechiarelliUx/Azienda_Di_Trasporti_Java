package it.epicode.amministratore.classi.gestione_mezzi.stato_mezzo;

import jakarta.persistence.EntityManager;

public class MezzoInServizioDAO {

    EntityManager em;

    public MezzoInServizioDAO(EntityManager em) {
        this.em = em;
    }

    public MezzoInServizio findById(long id){
        return em.find(MezzoInServizio.class, id);
    }

    public void insert (MezzoInServizio mezzoInServizio){
        em.persist(mezzoInServizio);
    }

    public void delete (MezzoInServizio mezzoInServizio){
        em.remove(mezzoInServizio);
    }
}
