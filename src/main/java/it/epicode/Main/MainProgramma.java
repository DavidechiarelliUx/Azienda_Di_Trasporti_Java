package it.epicode.Main;

import it.epicode.Amministratore.Abbonamento.Abbonamenti.Abbonamento;
import it.epicode.Amministratore.Abbonamento.Abbonamenti.AbbonamentoDAO;
import it.epicode.Amministratore.Abbonamento.Abbonamenti.Tipologia;
import it.epicode.Amministratore.Abbonamento.Tessera.Tessera;
import it.epicode.Amministratore.Abbonamento.Tessera.TesseraDAO;
import it.epicode.Amministratore.Biglietto.Biglietto;
import it.epicode.Amministratore.Biglietto.BigliettoDAO;
import it.epicode.Amministratore.Biglietto.PuntoEmissione.Distributori.Distributore;
import it.epicode.Amministratore.Biglietto.PuntoEmissione.Rivenditori.Rivenditore;
import it.epicode.Amministratore.Biglietto.PuntoEmissione.PuntoDiEmissione;
import it.epicode.Amministratore.Utente.Utente;
import it.epicode.Amministratore.Utente.UtenteDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Scanner;

public class MainProgramma {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();
        
        UtenteDAO utenteDAO = new UtenteDAO(em);
        TesseraDAO tesseraDAO = new TesseraDAO(em);
        AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO(em);
        BigliettoDAO bigliettoDAO = new BigliettoDAO(em);
        
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
            System.out.println("5. Uscita");
            System.out.print("Scegli un'operazione: ");
            
            int scelta = scanner.nextInt();
            
            switch (scelta) {
                case 1:
                    System.out.print("Inserisci la città del distributore: ");
                    String cittaDistributore = scanner.next();
                    System.out.print("Il distributore è attivo? (true/false): ");
                    boolean attivoDistributore = scanner.nextBoolean();
                    
                    Distributore distributore = new Distributore(1L, cittaDistributore);
                    distributore.setAttivo(attivoDistributore);
                    
                    Biglietto bigliettoDistributore = new Biglietto(distributore, LocalDate.now());
                    em.getTransaction().begin();
                    bigliettoDAO.insert(bigliettoDistributore);
                    em.getTransaction().commit();
                    
                    System.out.println("Biglietto emesso con successo: " + bigliettoDistributore);
                    break;
                
                case 2:
                    System.out.print("Inserisci la città del rivenditore: ");
                    String cittaRivenditore = scanner.next();
                    System.out.print("Inserisci il nome del rivenditore: ");
                    String nomeRivenditore = scanner.next();
                    
                    Rivenditore rivenditore = new Rivenditore(1L, cittaRivenditore, 12345, nomeRivenditore);
                    
                    Biglietto bigliettoRivenditore = new Biglietto(rivenditore, LocalDate.now());
                    em.getTransaction().begin();
                    bigliettoDAO.insert(bigliettoRivenditore);
                    em.getTransaction().commit();
                    
                    System.out.println("Biglietto emesso con successo: " + bigliettoRivenditore);
                    break;
                
                case 3:
                    System.out.print("Inserisci il tipo di abbonamento (SETTIMANALE/MENSILE): ");
                    String tipoAbbonamento = scanner.next().toUpperCase();
                    
                    System.out.print("Inserisci la data di inizio (yyyy-mm-dd): ");
                    String dataInizioString = scanner.next();
                    LocalDate dataInizio = LocalDate.parse(dataInizioString);
                    
                    System.out.print("Inserisci la data di fine (yyyy-mm-dd): ");
                    String dataFineString = scanner.next();
                    LocalDate dataFine = LocalDate.parse(dataFineString);
                    
                    Tipologia tipologiaAbbonamento = Tipologia.valueOf(tipoAbbonamento);
                    Abbonamento abbonamento = new Abbonamento(0L, tipologiaAbbonamento, dataInizio, dataFine);
                    em.getTransaction().begin();
                    abbonamentoDAO.insert(abbonamento);
                    em.getTransaction().commit();
                    
                    System.out.println("Abbonamento emesso con successo: " + abbonamento);
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
                    running = false;
                    System.out.println("Arrivederci!");
                    break;
                
                default:
                    System.out.println("Opzione non valida. Riprova.");
                    break;
            }
        }
        
        em.close();
        emf.close();
    }
}
