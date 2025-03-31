package it.epicode.Main;

import it.epicode.Amministratore.Biglietto.Biglietto;
import it.epicode.Amministratore.Biglietto.PuntoEmissione.Distributori.Distributore;
import it.epicode.Amministratore.Biglietto.PuntoEmissione.Rivenditori.Rivenditore;
import it.epicode.Amministratore.Biglietto.PuntoEmissione.PuntoDiEmissione;
import it.epicode.Amministratore.Abbonamento.Tessera.Tessera;
import it.epicode.Amministratore.Utente.Utente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainProgramma {
    
    private static List<Utente> utenti = new ArrayList<>();
    private static List<PuntoDiEmissione> puntiDiEmissione = new ArrayList<>();
    private static Utente utenteLoggato = null;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        setupDB();
        
        while (true) {
            System.out.println("Benvenuto nel sistema di gestione dei trasporti!");
            if (utenteLoggato != null) {
                System.out.println("1. Visualizza informazioni utente");
                System.out.println("2. Interagisci con i punti di emissione");
                System.out.println("3. Logout");
                System.out.println("4. Esci");
            } else {
                System.out.println("1. Login come utente");
                System.out.println("2. Crea nuovo utente");
                System.out.println("3. Esci");
            }
            System.out.print("Scegli un'opzione: ");
            int scelta = scanner.nextInt();
            scanner.nextLine();
            
            switch (scelta) {
                case 1:
                    if (utenteLoggato != null) {
                        mostraInformazioniUtente();
                    } else {
                        loginUtente(scanner);
                    }
                    break;
                case 2:
                    if (utenteLoggato != null) {
                        interagisciConPuntiEmissione(scanner);
                    } else {
                        creaNuovoUtente(scanner);
                    }
                    break;
                case 3:
                    if (utenteLoggato != null) {
                        logoutUtente();
                    } else {
                        System.out.println("Arrivederci!");
                        scanner.close();
                        return;
                    }
                    break;
                case 4:
                    System.out.println("Arrivederci!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opzione non valida, riprova.");
            }
        }
    }
    
    private static void setupDB() {
        Utente utente1 = new Utente("Mario", "Rossi", new Tessera(1, LocalDate.now().plusYears(1), null, null));
        Utente utente2 = new Utente("Giulia", "Bianchi", new Tessera(2, LocalDate.now().plusYears(1), null, null));
        
        utenti.add(utente1);
        utenti.add(utente2);
        
        Distributore distributore1 = new Distributore(1L, "Roma", true);
        Distributore distributore2 = new Distributore(2L, "Milano", false);
        Rivenditore rivenditore1 = new Rivenditore(3L, "Napoli", 101, "Rivenditore A", false);
        Rivenditore rivenditore2 = new Rivenditore(4L, "Torino", 102, "Rivenditore B", true);
        
        puntiDiEmissione.add(distributore1);
        puntiDiEmissione.add(distributore2);
        puntiDiEmissione.add(rivenditore1);
        puntiDiEmissione.add(rivenditore2);
    }
    
    private static void loginUtente(Scanner scanner) {
        System.out.println("Login Utente:");
        System.out.print("Inserisci il nome: ");
        String nome = scanner.nextLine();
        System.out.print("Inserisci il cognome: ");
        String cognome = scanner.nextLine();
        
        for (Utente utente : utenti) {
            if (utente.getNome().equalsIgnoreCase(nome) && utente.getCognome().equalsIgnoreCase(cognome)) {
                utenteLoggato = utente;
                System.out.println("Login avvenuto con successo!");
                return;
            }
        }
        
        System.out.println("Utente non trovato.");
    }
    
    private static void mostraInformazioniUtente() {
        System.out.println("Informazioni utente:");
        System.out.println("Nome: " + utenteLoggato.getNome());
        System.out.println("Cognome: " + utenteLoggato.getCognome());
        System.out.println("Codice Tessera: " + utenteLoggato.getTessera().getCodiceTessera());
        System.out.println("Data di Scadenza: " + utenteLoggato.getTessera().getDataScadenza());
    }
    
    private static void logoutUtente() {
        System.out.println("Logout effettuato.");
        utenteLoggato = null;
    }
    
    private static void creaNuovoUtente(Scanner scanner) {
        System.out.println("Crea un nuovo utente:");
        System.out.print("Inserisci il nome: ");
        String nome = scanner.nextLine();
        System.out.print("Inserisci il cognome: ");
        String cognome = scanner.nextLine();
        System.out.print("Inserisci codice tessera (int): ");
        int codiceTessera = scanner.nextInt();
        scanner.nextLine();
        
        LocalDate dataScadenza = LocalDate.now().plusYears(1);
        
        Tessera tessera = new Tessera(codiceTessera, dataScadenza, null, null);
        Utente nuovoUtente = new Utente(nome, cognome, tessera);
        
        utenti.add(nuovoUtente);
        System.out.println("Nuovo utente creato con successo!");
    }
    
    private static void interagisciConPuntiEmissione(Scanner scanner) {
        while (true) {
            System.out.println("Punti di emissione disponibili:");
            for (int i = 0; i < puntiDiEmissione.size(); i++) {
                PuntoDiEmissione punto = puntiDiEmissione.get(i);
                if (punto instanceof Distributore) {
                    Distributore distributore = (Distributore) punto;
                    String statoDistributore = distributore.isAttivo() ? "Attivo" : "Distributore fuori servizio";
                    System.out.println(i + 1 + ". Distributore a " + distributore.getCitta() + " (" + statoDistributore + ")");
                } else if (punto instanceof Rivenditore) {
                    Rivenditore rivenditore = (Rivenditore) punto;
                    String statoRivenditore = rivenditore.isAttivo() ? "Attivo" : "Rivenditore fuori servizio";
                    System.out.println(i + 1 + ". Rivenditore a " + rivenditore.getCitta() + " (" + statoRivenditore + ")");
                }
            }
            
            System.out.print("Scegli il punto di emissione (numero, 0 per tornare indietro): ");
            int sceltaPunto = scanner.nextInt();
            scanner.nextLine();
            
            if (sceltaPunto == 0) {
                return;
            }
            
            PuntoDiEmissione puntoScelto = puntiDiEmissione.get(sceltaPunto - 1);
            
            if (!puntoScelto.isAttivo()) {
                System.out.println("Il punto di emissione scelto non è attivo.");
                continue;
            }
            
            Biglietto biglietto = new Biglietto();
            biglietto.setPuntoDiEmissione(puntoScelto);
            biglietto.setData_di_emissione(LocalDate.now());
            System.out.println("Biglietto emesso con successo!");
        }
    }
    
}
