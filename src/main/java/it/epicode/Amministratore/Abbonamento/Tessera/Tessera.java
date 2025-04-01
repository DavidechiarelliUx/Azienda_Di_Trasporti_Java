package it.epicode.Amministratore.Abbonamento.Tessera;

import it.epicode.Amministratore.Abbonamento.Abbonamenti.Abbonamento;
import it.epicode.Amministratore.Utente.Utente;
import jakarta.persistence.*;
import jakarta.persistence.GenerationType;

import java.time.LocalDate;

@Entity
@Table(name = "tessera")
public class Tessera {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int codiceTessera;
    
    @Column(nullable = false)
    private LocalDate dataScadenza;
    
    @OneToOne
    @JoinColumn(name = "abbonamento_id")
    private Abbonamento abbonamento;
    
    @OneToOne(mappedBy = "tessera", cascade = CascadeType.ALL)
    private Utente utente;
    
    public int getCodiceTessera() {
        return codiceTessera;
    }

    public void setCodiceTessera(int codiceTessera) {
        this.codiceTessera = codiceTessera;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public Abbonamento getAbbonamento() {
        return abbonamento;
    }

    public void setAbbonamento(Abbonamento abbonamento) {
        this.abbonamento = abbonamento;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Tessera(int codiceTessera, LocalDate dataScadenza, Abbonamento abbonamento, Utente utente) {
        this.codiceTessera = codiceTessera;
        this.dataScadenza = dataScadenza;
        this.abbonamento = abbonamento;
        this.utente = utente;
    }

    public Tessera() {
    }
    
    public Tessera(Utente utente) {
        this.utente = utente;
        this.dataScadenza = LocalDate.now().plusYears(1);
    }

    @Override
    public String toString() {
        return "Tessera{" +
                "codiceTessera=" + codiceTessera +
                ", dataScadenza=" + dataScadenza +
                ", abbonamento=" + abbonamento +
                ", utente=" + utente +
                '}';
    }
}
