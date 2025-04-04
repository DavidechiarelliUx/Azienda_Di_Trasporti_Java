package it.epicode.amministratore.classi.gestione_mezzi.lista_manutenzione;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ListaManutenzioneDAO {
    private EntityManager em;

    public ListaManutenzioneDAO(EntityManager em) {
        this.em = em;
    }

    public void insert(ListaManutenzione listaManutenzione) {
        em.persist(listaManutenzione);
    }
    public ListaManutenzione findById(long id){
        return em.find(ListaManutenzione.class, id);
    }
    public void delete(ListaManutenzione listaManutenzione){
        em.remove(listaManutenzione);
    }

    public List<Object[]> getManutenzioniByMezzoId(Long mezzoId) {
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT m.dataInizioManutenzione, m.dataFineManutenzione, m.descrizioneManutenzione " +
                        "FROM ListaManutenzione m WHERE m.mezzo.id = :mezzoId",
                Object[].class
        );
        query.setParameter("mezzoId", mezzoId);

        return query.getResultList();
    }

}
