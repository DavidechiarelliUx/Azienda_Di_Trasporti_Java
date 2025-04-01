package it.epicode.Mezzi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class MainProvaMezzo {
    public static void main(String[] args) {

        EntityManagerFactory  emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();
        Mezzo mezzo = new Mezzo(null, 200, true, TipoMezzo.AUTOBUS );
        em.getTransaction().begin();
        MezzoDAO mezzoDAO = new MezzoDAO(em);

        mezzoDAO.insert(mezzo);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
