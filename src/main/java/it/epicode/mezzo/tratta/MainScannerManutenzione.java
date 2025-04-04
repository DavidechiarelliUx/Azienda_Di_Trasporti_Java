package it.epicode.mezzo.tratta;

import it.epicode.mezzo.lista_manutenzione.ListaManutenzioneDAO;
import it.epicode.mezzo.MezzoDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Scanner;

public class MainScannerManutenzione {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();

        MezzoDAO mezzoDAO = new MezzoDAO(em);
        ListaManutenzioneDAO listaManutenzioneDAO = new ListaManutenzioneDAO(em);
        Scanner scanner = new Scanner(System.in);

        System.out.println("inserisci l'id del mezzo : ");
        Long idMezzo = scanner.nextLong();

        scanner.nextLine();

        List<Object[]> numeroManutenzioni = listaManutenzioneDAO.getManutenzioniByMezzoId(idMezzo);
        System.out.println("il mezzo è stato in manutenzione : " + numeroManutenzioni.size() + " volte");
        for (Object[] result : numeroManutenzioni) {
            System.out.println("Data inizio manutenzione: " + result[0] + ", Data fine manutenzione: " + result[1] + " Descrizione manutenzione" + result[2]);
        }

        em.close();
        emf.close();

    }
}
