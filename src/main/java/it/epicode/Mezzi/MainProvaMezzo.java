package it.epicode.Mezzi;

import it.epicode.Mezzi.ListaManutenzioni.ListaManutenzione;
import it.epicode.Mezzi.ListaManutenzioni.ListaManutenzioneDAO;
import it.epicode.Mezzi.StatoMezzo.MezzoInManutenzione;
import it.epicode.Mezzi.StatoMezzo.MezzoInManutenzioneDAO;
import it.epicode.Mezzi.StatoMezzo.MezzoInServizio;
import it.epicode.Mezzi.StatoMezzo.MezzoInServizioDAO;
import it.epicode.Mezzi.Tratta.Tratta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class MainProvaMezzo {
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();
        
        MezzoInManutenzioneDAO mezzoInManutenzioneDAO = new MezzoInManutenzioneDAO(em);
        MezzoInServizioDAO mezzoInServizioDAO = new MezzoInServizioDAO(em);

        
        em.getTransaction().begin();
        
        mezzoInManutenzioneDAO.insert(new MezzoInManutenzione(30, TipoMezzo.TRAM, LocalDate.now(), LocalDate.of(2025, 10, 10)));
        mezzoInServizioDAO.insert(new MezzoInServizio(40, TipoMezzo.TRAM, LocalDate.now(), LocalDate.of(2025, 10, 10)));
        mezzoInManutenzioneDAO.insert(new MezzoInManutenzione(20, TipoMezzo.AUTOBUS, LocalDate.now(), LocalDate.of(2025, 12, 31)));
        mezzoInServizioDAO.insert(new MezzoInServizio(10, TipoMezzo.AUTOBUS, LocalDate.now(), LocalDate.of(2025, 1, 1)));

        ListaManutenzioneDAO  listaManutenzioneDAO = new ListaManutenzioneDAO(em);
        listaManutenzioneDAO.insert(new ListaManutenzione(mezzoInManutenzioneDAO.findById(1L),LocalDate.of(2021,5,12), LocalDate.of(2025, 10, 10),  "Rottura macchinario del caffè"));
        listaManutenzioneDAO.insert(new ListaManutenzione(mezzoInManutenzioneDAO.findById(1L), LocalDate.of(2023,1,4), LocalDate.of(2025, 2, 1), "Rottura macchinario del caffè"));
        listaManutenzioneDAO.insert(new ListaManutenzione(mezzoInManutenzioneDAO.findById(1L), LocalDate.of(2021,1,4), LocalDate.of(2022, 1, 31), "attrezzatura per la sicurezza mancante"));
        listaManutenzioneDAO.insert(new ListaManutenzione(mezzoInManutenzioneDAO.findById(1L), LocalDate.of(2020,3,4), LocalDate.of(2021, 2, 3), "guidatore personale influenzato"));
        listaManutenzioneDAO.insert(new ListaManutenzione(mezzoInManutenzioneDAO.findById(1L), LocalDate.of(2020, 1, 4), LocalDate.of(2021, 2, 3), "Troppo traffico a roma, mi guasto da solo"));
        listaManutenzioneDAO.insert(new ListaManutenzione(mezzoInManutenzioneDAO.findById(1L), LocalDate.of(2010,2,3), LocalDate.of(2025, 6, 23), "perdita di olio dal motore"));
        listaManutenzioneDAO.insert(new ListaManutenzione(mezzoInManutenzioneDAO.findById(3L), LocalDate.of(2014,1,10), LocalDate.of(2016, 2, 24), "esplosione di un passeggero"));
        listaManutenzioneDAO.insert(new ListaManutenzione(mezzoInManutenzioneDAO.findById(3L), LocalDate.of(2015, 1, 10), LocalDate.of(2016, 2, 24), "ubriaco al volante"));
        listaManutenzioneDAO.insert(new ListaManutenzione(mezzoInManutenzioneDAO.findById(3L), LocalDate.of(2016, 1, 10), LocalDate.of(2016, 2, 24), "eccesso di velocità e schianto contro un palo della luce"));


        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
