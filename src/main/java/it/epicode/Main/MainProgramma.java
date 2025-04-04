package it.epicode.Main;

import it.epicode.classi.gestione_funzionalita_utente.abbonamento.Abbonamento;
import it.epicode.classi.gestione_funzionalita_utente.abbonamento.AbbonamentoDAO;
import it.epicode.classi.gestione_funzionalita_utente.abbonamento.Tipologia;
import it.epicode.classi.gestione_funzionalita_utente.tessera.Tessera;
import it.epicode.classi.gestione_funzionalita_utente.tessera.TesseraDAO;
import it.epicode.classi.gestione_funzionalita_utente.biglietto.Biglietto;
import it.epicode.classi.gestione_funzionalita_utente.biglietto.BigliettoDAO;
import it.epicode.classi.gestione_funzionalita_utente.punto_di_emissione.distributore.Distributore;
import it.epicode.classi.gestione_funzionalita_utente.punto_di_emissione.distributore.DistributoreDAO;
import it.epicode.classi.gestione_funzionalita_utente.punto_di_emissione.PuntoDiEmissioneDAO;
import it.epicode.classi.gestione_funzionalita_utente.punto_di_emissione.rivenditore.Rivenditore;
import it.epicode.classi.gestione_funzionalita_utente.punto_di_emissione.PuntoDiEmissione;
import it.epicode.classi.gestione_funzionalita_utente.punto_di_emissione.rivenditore.RivenditoreDAO;
import it.epicode.classi.gestione_funzionalita_utente.utente.Utente;
import it.epicode.classi.gestione_funzionalita_utente.utente.UtenteDAO;
import it.epicode.classi.gestione_mezzi.lista_manutenzione.ListaManutenzioneDAO;
import it.epicode.classi.gestione_mezzi.mezzo.MezzoDAO;
import it.epicode.classi.gestione_mezzi.tratta.Tratta;
import it.epicode.classi.gestione_mezzi.tratta.TrattaDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MainProgramma {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();
        
        UtenteDAO utenteDAO = new UtenteDAO(em);
        TesseraDAO tesseraDAO = new TesseraDAO(em);
        AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO(em);
        BigliettoDAO bigliettoDAO = new BigliettoDAO(em);
        DistributoreDAO distributoreDAO = new DistributoreDAO(em);
        RivenditoreDAO rivenditoreDAO = new RivenditoreDAO(em);
        PuntoDiEmissioneDAO puntoDiEmissioneDAO = new PuntoDiEmissioneDAO(em);
        
        Scanner scanner = new Scanner(System.in);
        
        boolean loggedIn = false;
        Utente utenteLoggato = null;
        
        boolean programmaAttivo = true;
        while (programmaAttivo){
            while (!loggedIn) {
                System.out.println("~ . ~ Login ~ . ~");
                
                System.out.println("1. Login con account esistente");
                System.out.println("2. Crea un nuovo account");
                System.out.println("3. Accedi all'area amministratore");
                System.out.print("Scegli un'operazione: ");
                int sceltaLogin = scanner.nextInt();
                
                switch (sceltaLogin) {
                    case 1:
                        System.out.print("Inserisci il nome utente: ");
                        String nomeUtente = scanner.next();
                        System.out.print("Inserisci il cognome utente: ");
                        String cognomeUtente = scanner.next();
                        
                        utenteLoggato = utenteDAO.findByName(nomeUtente, cognomeUtente);
                        if (utenteLoggato != null) {
                            System.out.println("\nBenvenuto, " + nomeUtente + " " + cognomeUtente);
                            loggedIn = true;
                        } else {
                            System.out.println("Utente non trovato. Riprova.");
                        }
                        break;
                    
                    case 2:
                        System.out.print("Inserisci il nome del nuovo utente: ");
                        nomeUtente = scanner.next();
                        System.out.print("Inserisci il cognome del nuovo utente: ");
                        cognomeUtente = scanner.next();
                        
                        Utente nuovoUtente = new Utente(nomeUtente, cognomeUtente);
                        em.getTransaction().begin();
                        utenteDAO.insert(nuovoUtente);
                        em.getTransaction().commit();
                        
                        System.out.println("Account creato con successo!");
                        
                        Tessera nuovaTessera = new Tessera(nuovoUtente);
                        tesseraDAO.insert(nuovaTessera);
                        nuovoUtente.setTessera(nuovaTessera);
                        
                        em.getTransaction().begin();
                        utenteDAO.update(nuovoUtente);
                        em.getTransaction().commit();
                        
                        utenteLoggato = nuovoUtente;
                        System.out.println("\nBenvenuto, " + nomeUtente + " " + cognomeUtente);
                        loggedIn = true;
                        break;
                    
                    case 3:
                        System.out.println("Inserisci la password : ");
                        String password = scanner.next();
                        if (password.equals("Davidone99")) {
                            System.out.println("Accesso effettuato con successo!");
                            loggedIn = true;
                            while (loggedIn) {
                                System.out.println("~ . ~ Menu Amministratore ~ . ~");
                                System.out.println("1. Conta Biglietti e Abbonamenti");
                                System.out.println("2. Conta Biglietti vidimati");
                                System.out.println("3. Storico delle manutenzioni");
                                System.out.println("4. Numero di volte che un mezzo ha percorso una tratta");
                                System.out.println("5. Uscita area amministratore");
                                System.out.println("0. Uscita dal programma");
                                System.out.print("Scegli un'operazione: ");
                                
                                int scelta = scanner.nextInt();
                                
                                switch (scelta) {
                                    case 1:
                                        System.out.println("~ . ~ Conta Biglietti e Abbonamenti ~ . ~");
                                        
                                        try {
                                            System.out.print("Inserisci la data di inizio (formato YYYY-MM-DD): ");
                                            String dataInizioString = scanner.next();
                                            LocalDate dataInizio = LocalDate.parse(dataInizioString);
                                            
                                            LocalDate dataFine = dataInizio.plusYears(1);
                                            
                                            int totalBiglietti = bigliettoDAO.countBigliettiByPeriodo(dataInizio, dataFine);
                                            int totalAbbonamenti = abbonamentoDAO.countAbbonamentiByPeriodo(dataInizio, dataFine);
                                            
                                            System.out.println("~ . ~ Report Generale ~ . ~");
                                            System.out.println("Totale Biglietti emessi: " + totalBiglietti);
                                            System.out.println("Totale Abbonamenti emessi: " + totalAbbonamenti);
                                            System.out.println("Periodo considerato: " + dataInizio + " → " + dataFine);
                                            
                                            System.out.print("\nSeleziona un punto di emissione (ID): ");
                                            if (scanner.hasNextLong()) {
                                                long puntoEmissioneId = scanner.nextLong();
                                                PuntoDiEmissione puntoEmissione = puntoDiEmissioneDAO.findById(puntoEmissioneId);
                                                
                                                if (puntoEmissione != null) {
                                                    int bigliettiPerPunto = bigliettoDAO.countBigliettiByPuntoDiEmissioneAndPeriodo(puntoEmissione, dataInizio, dataFine);
                                                    int abbonamentiPerPunto = abbonamentoDAO.countAbbonamentiByPuntoDiEmissioneAndPeriodo(puntoEmissione, dataInizio, dataFine);
                                                    
                                                    System.out.println("~ . ~ Report per Punto di Emissione: " + puntoEmissione.getCitta() + " ~ . ~");
                                                    System.out.println("Biglietti emessi: " + bigliettiPerPunto);
                                                    System.out.println("Abbonamenti emessi: " + abbonamentiPerPunto);
                                                } else {
                                                    System.out.println("Errore: Punto di emissione non trovato.");
                                                }
                                            } else {
                                                System.out.println("Errore: Inserisci un ID valido.");
                                                scanner.next();
                                            }
                                        } catch (Exception e) {
                                            System.out.println("Errore nel formato della data. Assicurati di inserire una data valida nel formato YYYY-MM-DD.");
                                        }
                                        break;
                                    
                                    
                                    case 2:
                                        System.out.println("Inserisci il mezzo dove vuoi contare i biglietti vidimati:");
                                        Long mezzoId = scanner.nextLong();
                                        scanner.nextLine();
                                        
                                        int numeroBiglietti = bigliettoDAO.numeroTotBiglietti(mezzoId);
                                        System.out.println("Il numero totale di biglietti vidimati è: " + numeroBiglietti);
                                        break;
                                    
                                    case 3:
                                        MezzoDAO mezzoDAO = new MezzoDAO(em);
                                        ListaManutenzioneDAO listaManutenzioneDAO = new ListaManutenzioneDAO(em);
                                        Scanner scanner2 = new Scanner(System.in);
                                        
                                        System.out.println("Inserisci l'id del mezzo: ");
                                        Long idMezzo = scanner2.nextLong();
                                        scanner.nextLine();
                                        
                                        List<Object[]> numeroManutenzioni = listaManutenzioneDAO.getManutenzioniByMezzoId(idMezzo);
                                        System.out.println("Il mezzo è stato in manutenzione " + numeroManutenzioni.size() + " volte");
                                        
                                        for (Object[] result : numeroManutenzioni) {
                                            System.out.println("Data inizio manutenzione: " + result[0] + ", Data fine manutenzione: " + result[1] + ", Descrizione manutenzione: " + result[2]);
                                        }
                                        break;
                                    
                                    case 4:
                                        System.out.println("Inserisci l'id della tratta: ");
                                        Long idTratta = scanner.nextLong();
                                        scanner.nextLine();
                                        TrattaDAO trattaDAO = new TrattaDAO(em);
                                        
                                        Tratta tratta = trattaDAO.findById(idTratta);
                                        
                                        if (tratta != null) {
                                            System.out.println("Il mezzo è partito da: " + tratta.getZonaPartenza() + " con capolinea: " + tratta.getCapolinea());
                                            
                                            // Conta quante volte il mezzo ha percorso questa tratta
                                            trattaDAO.contaVolteTratta(idTratta);
                                            
                                            Long idMezzo1 = tratta.getMezzo().getId();
                                            System.out.println("Media tempo tratta: " + trattaDAO.calcolaMediaTempo(idMezzo1));
                                        } else {
                                            System.out.println("Tratta non trovata.");
                                        }
                                        break;
                                    
                                    
                                    case 5:
                                        System.out.println("Uscita dall'area amministratore.");
                                        loggedIn = false;
                                        break;
                                    
                                    case 0:
                                        System.out.println("Uscita dal programma.");
                                        System.exit(0);
                                        break;
                                    
                                    default:
                                        System.out.println("Opzione non valida. Riprova.");
                                        break;
                                }
                            }
                        } else {
                            System.out.println("Password non valida. Riprova.");
                        }
                        break;
                    default:
                        System.out.println("Opzione non valida. Riprova.");
                        break;
                }
            }
            
            boolean running = true;
            while (running) {
                System.out.println("~ . ~ Menu ~ . ~");
                System.out.println("1. Emissione Biglietto da Distributore");
                System.out.println("2. Emissione Biglietto da Rivenditore");
                System.out.println("3. Emissione Abbonamento");
                System.out.println("4. Verifica validità tessera");
                System.out.println("5. Vidimazione biglietto");
                System.out.println("6. Logout");
                System.out.println("0. Uscita programma");
                System.out.print("Scegli un'operazione: ");
                
                int scelta = scanner.nextInt();
                
                switch (scelta) {
                    case 1:
                        List<Distributore> distributori = distributoreDAO.getDistributori();
                        
                        if (distributori.isEmpty()) {
                            System.out.println("Nessun distributore disponibile.");
                            break;
                        }
                        
                        System.out.println("Elenco Distributori:");
                        for (int i = 0; i < distributori.size(); i++) {
                            System.out.println((i + 1) + ". " + distributori.get(i).getCitta() + " (Attivo: " + distributori.get(i).isAttivo() + ")");
                        }
                        
                        System.out.print("Seleziona un distributore (1-" + distributori.size() + "): ");
                        int distributoreScelta = scanner.nextInt();
                        if (distributoreScelta < 1 || distributoreScelta > distributori.size()) {
                            System.out.println("Scelta non valida.");
                            break;
                        }
                        
                        Distributore distributoreSelezionato = distributori.get(distributoreScelta - 1);
                        if (!distributoreSelezionato.isAttivo()) {
                            System.out.println("Errore: Il distributore è fuori servizio.");
                            break;
                        }
                        
                        Biglietto bigliettoDistributore = new Biglietto(distributoreSelezionato, LocalDate.now());
                        em.getTransaction().begin();
                        bigliettoDAO.insert(bigliettoDistributore);
                        em.getTransaction().commit();
                        
                        System.out.println("Biglietto emesso con successo! ID: " + bigliettoDistributore.getId());
                        break;
                    
                    case 2:
                        List<Rivenditore> rivenditori = rivenditoreDAO.getRivenditori();
                        if (rivenditori.isEmpty()) {
                            System.out.println("Nessun rivenditore disponibile.");
                            break;
                        }
                        
                        System.out.println("Elenco Rivenditori:");
                        for (int i = 0; i < rivenditori.size(); i++) {
                            System.out.println((i + 1) + ". " + rivenditori.get(i).getNomeRivenditore() + " - " + rivenditori.get(i).getCitta());
                        }
                        
                        System.out.print("Seleziona un rivenditore (1-" + rivenditori.size() + "): ");
                        int rivenditoreScelta = scanner.nextInt();
                        if (rivenditoreScelta < 1 || rivenditoreScelta > rivenditori.size()) {
                            System.out.println("Scelta non valida.");
                            break;
                        }
                        
                        Rivenditore rivenditoreSelezionato = rivenditori.get(rivenditoreScelta - 1);
                        Biglietto bigliettoRivenditore = new Biglietto(rivenditoreSelezionato, LocalDate.now());
                        
                        em.getTransaction().begin();
                        bigliettoDAO.insert(bigliettoRivenditore);
                        em.getTransaction().commit();
                        
                        System.out.println("Biglietto emesso con successo! ID: " + bigliettoRivenditore.getId());
                        break;
                    
                    case 3:
                        if (utenteLoggato.getTessera() == null) {
                            System.out.println("Errore: Devi avere una tessera per acquistare un abbonamento.");
                            break;
                        }
                        
                        Abbonamento abbonamentoAttivo = abbonamentoDAO.findById(utenteLoggato.getTessera().getCodiceTessera());
                        if (abbonamentoAttivo != null) {
                            System.out.println("Hai già un abbonamento attivo fino al " + abbonamentoAttivo.getDataFine());
                            break;
                        }
                        
                        System.out.println("~ . ~ Seleziona Punto di Emissione ~ . ~");
                        System.out.println("1. Distributore");
                        System.out.println("2. Rivenditore");
                        System.out.print("Scegli un punto di emissione (1-2): ");
                        int puntoEmissioneScelta = scanner.nextInt();
                        
                        if (puntoEmissioneScelta == 1) {
                            List<Distributore> distributoriList = distributoreDAO.getDistributori();
                            if (distributoriList.isEmpty()) {
                                System.out.println("Nessun distributore disponibile.");
                                break;
                            }
                            
                            System.out.println("Elenco Distributori:");
                            for (int i = 0; i < distributoriList.size(); i++) {
                                System.out.println((i + 1) + ". " + distributoriList.get(i).getCitta() + " (Attivo: " + distributoriList.get(i).isAttivo() + ")");
                            }
                            
                            System.out.print("Seleziona un distributore (1-" + distributoriList.size() + "): ");
                            int distributoreSceltaAbbo = scanner.nextInt();
                            if (distributoreSceltaAbbo < 1 || distributoreSceltaAbbo > distributoriList.size()) {
                                System.out.println("Scelta non valida.");
                                break;
                            }
                            
                            Distributore distributoreSelezionatoAbbo = distributoriList.get(distributoreSceltaAbbo - 1);
                            if (!distributoreSelezionatoAbbo.isAttivo()) {
                                System.out.println("Errore: Il distributore è fuori servizio.");
                                break;
                            }
                            
                            System.out.print("Inserisci il tipo di abbonamento (1 per SETTIMANALE, 2 per MENSILE): ");
                            int tipoAbbonamento = scanner.nextInt();
                            
                            LocalDate dataInizio = LocalDate.now();
                            LocalDate dataFine;
                            
                            switch (tipoAbbonamento) {
                                case 1:
                                    dataFine = dataInizio.plusWeeks(1);
                                    break;
                                case 2:
                                    dataFine = dataInizio.plusMonths(1);
                                    break;
                                default:
                                    System.out.println("Errore: Tipo di abbonamento non valido.");
                                    return;
                            }
                            
                            Abbonamento nuovoAbbonamento = new Abbonamento(utenteLoggato.getTessera().getCodiceTessera(), Tipologia.valueOf(tipoAbbonamento == 1 ? "SETTIMANALE" : "MENSILE"), dataInizio, dataFine, distributoreSelezionatoAbbo);
                            
                            em.getTransaction().begin();
                            abbonamentoDAO.merge(nuovoAbbonamento);
                            em.getTransaction().commit();
                            
                            System.out.println("Abbonamento emesso con successo! Scadenza: " + dataFine);
                        } else if (puntoEmissioneScelta == 2) {
                            List<Rivenditore> rivenditoriList = rivenditoreDAO.getRivenditori();
                            if (rivenditoriList.isEmpty()) {
                                System.out.println("Nessun rivenditore disponibile.");
                                break;
                            }
                            
                            System.out.println("Elenco Rivenditori:");
                            for (int i = 0; i < rivenditoriList.size(); i++) {
                                System.out.println((i + 1) + ". " + rivenditoriList.get(i).getNomeRivenditore() + " - " + rivenditoriList.get(i).getCitta());
                            }
                            
                            System.out.print("Seleziona un rivenditore (1-" + rivenditoriList.size() + "): ");
                            int rivenditoreSceltaAbbo = scanner.nextInt();
                            if (rivenditoreSceltaAbbo < 1 || rivenditoreSceltaAbbo > rivenditoriList.size()) {
                                System.out.println("Scelta non valida.");
                                break;
                            }
                            
                            Rivenditore rivenditoreSelezionatoAbbo = rivenditoriList.get(rivenditoreSceltaAbbo - 1);
                            
                            System.out.print("Inserisci il tipo di abbonamento (1 per SETTIMANALE, 2 per MENSILE): ");
                            int tipoAbbonamento = scanner.nextInt();
                            
                            LocalDate dataInizio = LocalDate.now();
                            LocalDate dataFine;
                            
                            switch (tipoAbbonamento) {
                                case 1:
                                    dataFine = dataInizio.plusWeeks(1);
                                    break;
                                case 2:
                                    dataFine = dataInizio.plusMonths(1);
                                    break;
                                default:
                                    System.out.println("Errore: Tipo di abbonamento non valido.");
                                    return;
                            }
                            
                            Abbonamento nuovoAbbonamento = new Abbonamento(utenteLoggato.getTessera().getCodiceTessera(), Tipologia.valueOf(tipoAbbonamento == 1 ? "SETTIMANALE" : "MENSILE"), dataInizio, dataFine, rivenditoreSelezionatoAbbo);
                            
                            em.getTransaction().begin();
                            abbonamentoDAO.merge(nuovoAbbonamento);
                            em.getTransaction().commit();
                            
                            System.out.println("Abbonamento emesso con successo! Scadenza: " + dataFine);
                        } else {
                            System.out.println("Scelta non valida.");
                            break;
                        }
                        break;
                    
                    case 4:
                        System.out.print("Inserisci il numero della tessera: ");
                        int codiceTessera = scanner.nextInt();
                        
                        Tessera tesseraControllata = tesseraDAO.findById(codiceTessera);
                        if (tesseraControllata != null) {
                            System.out.println("Tessera valida: " + tesseraControllata.getCodiceTessera() + " - " + tesseraControllata.getUtente().getNome());
                        } else {
                            System.out.println("Tessera non valida.");
                        }
                        break;
                    
                    case 5:
                        em.getTransaction().begin();
                        System.out.print("Inserisci l'ID del biglietto da vidimare: ");
                        Long codiceBiglietto2 = scanner.nextLong();
                        scanner.nextLine();
                        System.out.println("inserisci il numero del mezzo: ");
                        Long mezzoId2 = scanner.nextLong();
                        scanner.nextLine();
                        System.out.println("verifica del biglietto in corso ....");
                        bigliettoDAO.vidimaBiglietto(codiceBiglietto2, mezzoId2);
                        em.getTransaction().commit();
                        break;
                    
                    case 6:
                        System.out.println("Logout effettuato per " + utenteLoggato.getNome());
                        loggedIn = false;
                        running = false;
                        break;
                        
                    case 0:
                        System.out.println("Uscita dal programma.");
                        running = false;
                        loggedIn = false;
                        programmaAttivo = false;
                        break;
                    
                    default:
                        System.out.println("Opzione non valida. Riprova.");
                        break;
                }
            }
        }
        scanner.close();
        em.close();
        emf.close();
    }
}
