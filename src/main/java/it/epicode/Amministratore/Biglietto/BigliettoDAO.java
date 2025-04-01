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

    public void update (Biglietto biglietto){
        em.merge(biglietto);
    }

    public static void setVidimato(Long idBiglietto, boolean vidimato) {
        Biglietto biglietto = findById(idBiglietto);
        biglietto.setVidimato(vidimato);
    }
}
