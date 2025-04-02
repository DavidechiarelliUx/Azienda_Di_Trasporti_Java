package it.epicode.Amministratore.Biglietto;

import it.epicode.Amministratore.Biglietto.PuntoEmissione.PuntoDiEmissione;
import it.epicode.Mezzi.Mezzo;
import it.epicode.Mezzi.MezzoDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;

public class BigliettoDAO {
    private EntityManager em;
    
    public BigliettoDAO(EntityManager em) {
        this.em = em;
    }
    
    public void insert(Biglietto biglietto) {
        em.persist(biglietto);
    }
    
    public Biglietto findById(Long id) {
        return em.find(Biglietto.class, id);
    }

    public void update (Biglietto biglietto){
        em.merge(biglietto);
    }
    
    public int countBigliettiByPeriodo(LocalDate inizio, LocalDate fine) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(b) FROM Biglietto b WHERE b.dataDiEmissione >= :inizio AND b.dataDiEmissione <= :fine", Long.class
        );
        query.setParameter("inizio", inizio);
        query.setParameter("fine", fine);
        
        Long result = query.getSingleResult();
        if (result != null) {
            return result.intValue();
        }
        return 0;
    }
    
    public int countBigliettiByPuntoDiEmissioneAndPeriodo(PuntoDiEmissione puntoEmissione, LocalDate inizio, LocalDate fine) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(b) FROM Biglietto b WHERE b.puntoDiEmissione = :puntoDiEmissione AND b.dataDiEmissione >= :inizio AND b.dataDiEmissione <= :fine", Long.class
        );
        query.setParameter("puntoDiEmissione", puntoEmissione);
        query.setParameter("inizio", inizio);
        query.setParameter("fine", fine);
        
        Long result = query.getSingleResult();
        if (result != null) {
            return result.intValue();
        }
        return 0;
    }

    public void vidimaBiglietto(Long bigliettoId, Long mezzoId) {
        Biglietto biglietto = findById(bigliettoId);
        Mezzo mezzo = em.find(Mezzo.class, mezzoId);

        if (biglietto == null) {
            System.out.println("Errore: Biglietto non trovato.");
            return;
        }
        if (mezzo == null) {
            System.out.println("Errore: Mezzo non trovato.");
            return;
        }
        if (biglietto.isVidimato()) {
            System.out.println("Errore: Il biglietto è già stato vidimato.");
            return;
        }


        biglietto.setVidimato(true);
        biglietto.setMezzo(mezzo);
        em.merge(biglietto);


        System.out.println("Biglietto " + bigliettoId + " vidimato con successo sul mezzo " + mezzoId);
    }

    public int numeroTotBiglietti(Long mezzoId){

        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(b) FROM Biglietto b WHERE b.mezzo.id = :mezzoId AND b.vidimato = true ", Long.class
        );
        query.setParameter("mezzoId", mezzoId);

        Long result = query.getSingleResult();
        if (result != null) {
            return result.intValue();
        }
        return 0;
    }

}
