package it.epicode.Main;

import it.epicode.amministratore.classi.gestione_mezzi.lista_manutenzione.ListaManutenzione;
import it.epicode.amministratore.classi.gestione_mezzi.lista_manutenzione.ListaManutenzioneDAO;
import it.epicode.amministratore.classi.gestione_mezzi.stato_mezzo.MezzoInManutenzione;
import it.epicode.amministratore.classi.gestione_mezzi.stato_mezzo.MezzoInManutenzioneDAO;
import it.epicode.amministratore.classi.gestione_mezzi.stato_mezzo.MezzoInServizio;
import it.epicode.amministratore.classi.gestione_mezzi.stato_mezzo.MezzoInServizioDAO;
import it.epicode.amministratore.classi.gestione_mezzi.mezzo.TipoMezzo;
import it.epicode.amministratore.classi.gestione_mezzi.tratta.TipoTratta;
import it.epicode.amministratore.classi.gestione_mezzi.tratta.Tratta;
import it.epicode.amministratore.classi.gestione_mezzi.tratta.TrattaDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import it.epicode.amministratore.classi.biglietto.Biglietto;
import it.epicode.amministratore.classi.punto_di_emissione.distributore.Distributore;
import it.epicode.amministratore.classi.punto_di_emissione.rivenditore.Rivenditore;
import it.epicode.amministratore.classi.tessera.Tessera;
import it.epicode.amministratore.classi.utente.Utente;

import java.time.LocalDate;

public class MainNewDatabase {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        Utente utente1 = new Utente("Giovanni", "Rossi", null);
        Utente utente2 = new Utente("Maria", "Verdi", null);
        Utente utente3 = new Utente("Luca", "Bianchi", null);
        
        em.persist(utente1);
        em.persist(utente2);
        em.persist(utente3);
        
        Tessera tessera1 = new Tessera();
        tessera1.setDataScadenza(LocalDate.now().plusYears(1));
        em.persist(tessera1);
        utente1.setTessera(tessera1);
        
        Tessera tessera2 = new Tessera();
        tessera2.setDataScadenza(LocalDate.now().plusYears(1));
        em.persist(tessera2);
        utente2.setTessera(tessera2);
        
        Tessera tessera3 = new Tessera();
        tessera3.setDataScadenza(LocalDate.now().plusYears(1));
        em.persist(tessera3);
        utente3.setTessera(tessera3);
        
        Rivenditore rivenditore1 = new Rivenditore(1L, "Milano", 101, "Rivenditore A");
        Rivenditore rivenditore2 = new Rivenditore(2L, "Roma", 102, "Rivenditore B");
        Rivenditore rivenditore3 = new Rivenditore(3L, "Firenze", 103, "Rivenditore C");
        
        em.merge(rivenditore1);
        em.merge(rivenditore2);
        em.merge(rivenditore3);
        
        Distributore distributore1 = new Distributore(4L, "Napoli", true);
        Distributore distributore2 = new Distributore(5L, "Torino", false);
        Distributore distributore3 = new Distributore(6L, "Bologna", true);
        
        em.merge(distributore1);
        em.merge(distributore2);
        em.merge(distributore3);
        
        Biglietto biglietto1 = new Biglietto(distributore1, LocalDate.now());
        Biglietto biglietto2 = new Biglietto(distributore3, LocalDate.now().minusDays(1));
        Biglietto biglietto3 = new Biglietto(rivenditore1, LocalDate.now().minusDays(2));
        
        em.persist(biglietto1);
        em.persist(biglietto2);
        em.persist(biglietto3);
        
        MezzoInManutenzioneDAO mezzoInManutenzioneDAO = new MezzoInManutenzioneDAO(em);
        MezzoInServizioDAO mezzoInServizioDAO = new MezzoInServizioDAO(em);
        
        MezzoInManutenzione mezzoManutenzione1 = new MezzoInManutenzione(30, TipoMezzo.TRAM, LocalDate.now(), LocalDate.of(2025, 10, 10));
        MezzoInServizio mezzoServizio1 = new MezzoInServizio(40, TipoMezzo.TRAM, LocalDate.now(), LocalDate.of(2025, 10, 10));
        MezzoInManutenzione mezzoManutenzione2 = new MezzoInManutenzione(20, TipoMezzo.AUTOBUS, LocalDate.now(), LocalDate.of(2025, 12, 31));
        MezzoInServizio mezzoServizio2 = new MezzoInServizio(10, TipoMezzo.AUTOBUS, LocalDate.now(), LocalDate.of(2025, 1, 1));
        
        em.persist(mezzoManutenzione1);
        em.persist(mezzoServizio1);
        em.persist(mezzoManutenzione2);
        em.persist(mezzoServizio2);
        
        ListaManutenzioneDAO listaManutenzioneDAO = new ListaManutenzioneDAO(em);
        listaManutenzioneDAO.insert(new ListaManutenzione(mezzoInManutenzioneDAO.findById(1L), LocalDate.of(2021, 5, 12), LocalDate.of(2025, 10, 10), "Rottura macchinario del caffè"));
        listaManutenzioneDAO.insert(new ListaManutenzione(mezzoInManutenzioneDAO.findById(1L), LocalDate.of(2023, 1, 4), LocalDate.of(2025, 2, 1), "Rottura macchinario del caffè"));
        listaManutenzioneDAO.insert(new ListaManutenzione(mezzoInManutenzioneDAO.findById(1L), LocalDate.of(2021, 1, 4), LocalDate.of(2022, 1, 31), "attrezzatura per la sicurezza mancante"));
        listaManutenzioneDAO.insert(new ListaManutenzione(mezzoInManutenzioneDAO.findById(1L), LocalDate.of(2020, 3, 4), LocalDate.of(2021, 2, 3), "guidatore personale influenzato"));
        listaManutenzioneDAO.insert(new ListaManutenzione(mezzoInManutenzioneDAO.findById(1L), LocalDate.of(2020, 1, 4), LocalDate.of(2021, 2, 3), "Troppo traffico a roma, mi guasto da solo"));
        listaManutenzioneDAO.insert(new ListaManutenzione(mezzoInManutenzioneDAO.findById(1L), LocalDate.of(2010, 2, 3), LocalDate.of(2025, 6, 23), "perdita di olio dal motore"));
        listaManutenzioneDAO.insert(new ListaManutenzione(mezzoInManutenzioneDAO.findById(3L), LocalDate.of(2014, 1, 10), LocalDate.of(2016, 2, 24), "esplosione di un passeggero"));
        listaManutenzioneDAO.insert(new ListaManutenzione(mezzoInManutenzioneDAO.findById(3L), LocalDate.of(2015, 1, 10), LocalDate.of(2016, 2, 24), "ubriaco al volante"));
        listaManutenzioneDAO.insert(new ListaManutenzione(mezzoInManutenzioneDAO.findById(3L), LocalDate.of(2016, 1, 10), LocalDate.of(2016, 2, 24), "eccesso di velocità e schianto contro un palo della luce"));
        
        TrattaDAO trattaDAO = new TrattaDAO(em);
        Tratta tratta = new Tratta("Piazza del Popolo", "Stazione Centrale", 30, 50, TipoTratta.MATTINA, 1L);
        Tratta tratta2 = new Tratta("Via Vittorio Veneto", "Via Roma", 30, 10, TipoTratta.POMERIGGIO, 2L);
        Tratta tratta3 = new Tratta("Via Giulio Cesare", "Stazione di Vittorio Veneto", 20, 40, TipoTratta.MATTINA, 3L);
        Tratta tratta4 = new Tratta("Via Giulio Cesare", "Stazione di Vittorio Veneto", 30, 13, TipoTratta.POMERIGGIO, 3L);
        Tratta tratta5 = new Tratta("Via Giulio Cesare", "Stazione di Vittorio Veneto", 30, 8, TipoTratta.SERA, 3L);
        Tratta tratta6 = new Tratta("Via Jacopo Sannazzaro", "Stazione della Vittoria", 30, 20, TipoTratta.SERA, 4L);
        Tratta tratta7 = new Tratta("Via Jacopo Sannazzaro", "Stazione della Vittoria", 30, 10, TipoTratta.MATTINA, 4L);
        
        em.persist(tratta);
        em.persist(tratta2);
        em.persist(tratta3);
        em.persist(tratta4);
        em.persist(tratta5);
        em.persist(tratta6);
        em.persist(tratta7);
        
        em.getTransaction().commit();
        
        System.out.println("Dati popolati nel database con successo!");
        
        em.close();
        emf.close();
    }
}
