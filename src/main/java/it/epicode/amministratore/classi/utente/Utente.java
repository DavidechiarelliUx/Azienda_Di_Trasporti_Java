package it.epicode.amministratore.classi.utente;

import it.epicode.amministratore.classi.tessera.Tessera;
import jakarta.persistence.*;

@Entity
@Table(name = "utenti")
public class Utente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)  // Generazione automatica dell'ID
    private long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String cognome;
    
    @OneToOne
    @JoinColumn(name = "tessera_id")
    private Tessera tessera;
    
    public Utente(String nomeUtente, String cognomeUtente) {
        this.nome = nomeUtente;
        this.cognome = cognomeUtente;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }

    public Utente(String nome, String cognome, Tessera tessera) {
        this.nome = nome;
        this.cognome = cognome;
        this.tessera = tessera;
    }

    public Utente() {
    }
    
    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", tessera_id=" + (tessera != null ? tessera.getCodiceTessera() : "N/A") +
                '}';
    }
}
