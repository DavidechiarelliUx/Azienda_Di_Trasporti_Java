package it.epicode.Main;

import it.epicode.Amministratore.Abbonamento.Abbonamenti.Abbonamento;
import it.epicode.Amministratore.Abbonamento.Abbonamenti.AbbonamentoDAO;
import it.epicode.Amministratore.Abbonamento.Abbonamenti.Tipologia;
import it.epicode.Amministratore.Abbonamento.Tessera.Tessera;
import it.epicode.Amministratore.Abbonamento.Tessera.TesseraDAO;
import it.epicode.Amministratore.Biglietto.Biglietto;
import it.epicode.Amministratore.Biglietto.BigliettoDAO;
import it.epicode.Amministratore.Biglietto.PuntoEmissione.Distributori.Distributore;
import it.epicode.Amministratore.Biglietto.PuntoEmissione.Distributori.DistributoreDAO;
import it.epicode.Amministratore.Biglietto.PuntoEmissione.PuntoDiEmissioneDAO;
import it.epicode.Amministratore.Biglietto.PuntoEmissione.Rivenditori.Rivenditore;
import it.epicode.Amministratore.Biglietto.PuntoEmissione.PuntoDiEmissione;
import it.epicode.Amministratore.Biglietto.PuntoEmissione.Rivenditori.RivenditoreDAO;
import it.epicode.Amministratore.Utente.Utente;
import it.epicode.Amministratore.Utente.UtenteDAO;
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
        
        while (!loggedIn) {
            System.out.println("--- Login ---");
            System.out.println("1. Login con account esistente");
            System.out.println("2. Crea un nuovo account");
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
                        System.out.println("Benvenuto, " + nomeUtente + " " + cognomeUtente);
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
                    System.out.println("Benvenuto, " + nomeUtente + " " + cognomeUtente);
                    loggedIn = true;
                    break;
                
                default:
                    System.out.println("Opzione non valida. Riprova.");
                    break;
            }
        }
        
        boolean running = true;
        while (running) {
            System.out.println("--- Menu ---");
            System.out.println("1. Emissione Biglietto da Distributore");
            System.out.println("2. Emissione Biglietto da Rivenditore");
            System.out.println("3. Emissione Abbonamento");
            System.out.println("4. Verifica validità tessera");
            System.out.println("5. Vidimazione biglietto");
            System.out.println("6. Conta Biglietti e Abbonamenti");
            System.out.println("0. Uscita");
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
                    
                    System.out.println("--- Seleziona Punto di Emissione ---");
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
                        
                        System.out.print("Inserisci il tipo di abbonamento (SETTIMANALE/MENSILE): ");
                        String tipoAbbonamento = scanner.next().toUpperCase();
                        
                        LocalDate dataInizio = LocalDate.now();
                        LocalDate dataFine;
                        
                        if (tipoAbbonamento.equals("SETTIMANALE")) {
                            dataFine = dataInizio.plusWeeks(1);
                        } else if (tipoAbbonamento.equals("MENSILE")) {
                            dataFine = dataInizio.plusMonths(1);
                        } else {
                            System.out.println("Errore: Tipo di abbonamento non valido.");
                            break;
                        }
                        
                        Abbonamento nuovoAbbonamento = new Abbonamento(utenteLoggato.getTessera().getCodiceTessera(), Tipologia.valueOf(tipoAbbonamento), dataInizio, dataFine, distributoreSelezionatoAbbo);
                        
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
                        
                        System.out.print("Inserisci il tipo di abbonamento (SETTIMANALE/MENSILE): ");
                        String tipoAbbonamento = scanner.next().toUpperCase();
                        
                        LocalDate dataInizio = LocalDate.now();
                        LocalDate dataFine;
                        
                        if (tipoAbbonamento.equals("SETTIMANALE")) {
                            dataFine = dataInizio.plusWeeks(1);
                        } else if (tipoAbbonamento.equals("MENSILE")) {
                            dataFine = dataInizio.plusMonths(1);
                        } else {
                            System.out.println("Errore: Tipo di abbonamento non valido.");
                            break;
                        }
                        
                        Abbonamento nuovoAbbonamento = new Abbonamento(utenteLoggato.getTessera().getCodiceTessera(), Tipologia.valueOf(tipoAbbonamento), dataInizio, dataFine, rivenditoreSelezionatoAbbo);
                        
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
                        System.out.println("Tessera valida: " + tesseraControllata);
                    } else {
                        System.out.println("Tessera non valida.");
                    }
                    break;
                
                case 5:
                    System.out.print("Inserisci l'ID del biglietto da vidimare: ");
                    Long idBiglietto = scanner.nextLong();
                    if (idBiglietto == null || idBiglietto <= 0) {
                        System.out.println("ID non valido.");
                        break;
                    }
                    em.getTransaction().begin();
                    bigliettoDAO.vidimaBiglietto(idBiglietto);
                    em.getTransaction().commit();
                    break;
                
                case 6:
                    System.out.println("--- Conta Biglietti e Abbonamenti ---");
                    System.out.print("Inserisci la data di inizio (formato YYYY-MM-DD): ");
                    String dataInizioString = scanner.next();
                    LocalDate dataInizio = LocalDate.parse(dataInizioString);
                    
                    System.out.print("Inserisci la data di fine (formato YYYY-MM-DD): ");
                    String dataFineString = scanner.next();
                    LocalDate dataFine = LocalDate.parse(dataFineString);
                    
                    int totalBiglietti = bigliettoDAO.countBigliettiByPeriodo(dataInizio, dataFine);
                    int totalAbbonamenti = abbonamentoDAO.countAbbonamentiByPeriodo(dataInizio, dataFine);
                    
                    System.out.println("Totale Biglietti emessi: " + totalBiglietti);
                    System.out.println("Totale Abbonamenti emessi: " + totalAbbonamenti);
                    
                    System.out.print("Seleziona un punto di emissione (ID): ");
                    long puntoEmissioneId = scanner.nextLong();
                    PuntoDiEmissione puntoEmissione = puntoDiEmissioneDAO.findById(puntoEmissioneId);
                    
                    if (puntoEmissione != null) {
                        int bigliettiPerPunto = bigliettoDAO.countBigliettiByPuntoDiEmissioneAndPeriodo(puntoEmissione, dataInizio, dataFine);
                        int abbonamentiPerPunto = abbonamentoDAO.countAbbonamentiByPuntoDiEmissioneAndPeriodo(puntoEmissione, dataInizio, dataFine);
                        
                        System.out.println("Biglietti emessi dal punto di emissione (" + puntoEmissione.getCitta() + "): " + bigliettiPerPunto);
                        System.out.println("Abbonamenti emessi dal punto di emissione (" + puntoEmissione.getCitta() + "): " + abbonamentiPerPunto);
                    } else {
                        System.out.println("Punto di emissione non trovato.");
                    }
                    break;
                
                case 0:
                    running = false;
                    break;
                
                default:
                    System.out.println("Opzione non valida. Riprova.");
                    break;
            }
        }
        
        scanner.close();
        em.close();
        emf.close();
    }
}
