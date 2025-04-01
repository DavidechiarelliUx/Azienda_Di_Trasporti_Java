package it.epicode.Main;


import it.epicode.Amministratore.Abbonamento.Abbonamenti.AbbonamentoDAO;
import it.epicode.Amministratore.Abbonamento.Tessera.TesseraDAO;
import it.epicode.Amministratore.Biglietto.Biglietto;
import it.epicode.Amministratore.Biglietto.BigliettoDAO;
import it.epicode.Amministratore.Utente.UtenteDAO;
import it.epicode.Mezzi.MezzoDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


import java.util.Scanner;

public class MainProvaCorsa {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();

        UtenteDAO utenteDAO = new UtenteDAO(em);
        TesseraDAO tesseraDAO = new TesseraDAO(em);
        AbbonamentoDAO  abbonamentoDAO = new AbbonamentoDAO(em);
        BigliettoDAO bigliettoDAO = new BigliettoDAO(em);
        MezzoDAO mezzoDAO = new MezzoDAO(em);

        Scanner scanner = new Scanner(System.in);
        boolean running = false;

        while(!running){

            System.out.println("Ciao stai aspettando un mezzo ? ");
            System.out.println("1. Si");
            System.out.println("2. No");
            System.out.println("Altri numeri. Esci");
            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta){
                case 1 :
                    System.out.println("che mezzo stai aspettando : ");
                    System.out.println("1. Tram");
                    System.out.println("2. Bus");
                    int sceltaMezzo = scanner.nextInt();
                    scanner.nextLine();

                    switch (sceltaMezzo){
                        case 1 :
                            System.out.println("hai scelto tram");
                            System.out.println("Hai la tessera o il biglietto ?");

                            System.out.println("1. tessera");
                            System.out.println("2. biglietto");
                            System.out.println("3. esci");
                            int sceltaTesseraBiglietto = scanner.nextInt();
                            scanner.nextLine();
                            switch (sceltaTesseraBiglietto){
                                case 1 :
                                    System.out.println("hai scelto tessera");
                                    System.out.println("inserisci il codice della tessera");
                                    int codiceTessera = scanner.nextInt();
                                    scanner.nextLine();
                                    //puoi procedere tranquillamente se l'abbonamento è valido
                                    break;
                                case 2 :
                                    System.out.println("hai scelto biglietto");
                                    System.out.println("inserisci il codice del biglietto");
                                    Long codiceBiglietto = scanner.nextLong();

                                    scanner.nextLine();
                                    System.out.println("verifica del biglietto in corso ...." + codiceBiglietto + " " + Biglietto.isVidimato());
                                    //trasforma il biglietto vidimato da true a false
                                    Biglietto.setVidimato(true);
                                    em.getTransaction().begin();
                                    BigliettoDAO.setVidimato(codiceBiglietto, true);
                                    em.getTransaction().commit();



                                    System.out.println("Il seguente biglietto è stato vidimato : " + codiceBiglietto +" :  "+ Biglietto.isVidimato());
                                    System.out.println("biglietto vidimato puoi procedere con la corsa");

                                    System.out.println(" sta arrivando il controllore : ");
                                    System.out.println("1. controllo in corso");
                                    if (Biglietto.isVidimato()){
                                        System.out.println("perfetto puoi tornare a casa");
                                    }else{
                                        System.out.println(" ti faccio una bella multa perchè il biglietto non è stato timbrato");
                                    }
                                    System.out.println("2. controllo finito");
                                    break;
                                case 3 :
                                    System.out.println("esci");
                                    break;
                                default:
                                    System.out.println("scelta non valida");
                                    break;
                            }
                            break;
                        case 2 :
                            System.out.println("hai scelto bus");

                            break;
                        case 3 :
                            System.out.println("esci");
                            break;
                        default:
                            System.out.println("scelta non valida");
                            break;
                    }
                    break;
                case 2 :
                    System.out.println("torna a fare ciò che vuoi ma non aspettare davanti la fermata che occupi spazio");
                    break;
                    default:
                        System.out.println("esci");
                        running = true;
            }
        }
        em.close();
        emf.close();

    }
}
