package it.epicode.Amministratore.Abbonamento.Abbonamenti;

import it.epicode.Amministratore.Abbonamento.Tessera.Tessera;
import it.epicode.Amministratore.Biglietto.PuntoEmissione.PuntoDiEmissione;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "abbonamenti")
public class Abbonamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tipologia tipologia;
    
    @Column(name = "data_inizio", nullable = false)
    private LocalDate dataInizio;
    
    @Column(name = "data_fine", nullable = false)
    private LocalDate dataFine;
    
    @OneToOne
    @JoinColumn(name = "tessera_id")
    private Tessera tessera;
    
    @ManyToOne
    @JoinColumn(name = "punto_emissione_id")
    private PuntoDiEmissione puntoEmissione;
    
    public Abbonamento(long id, Tipologia tipologia, LocalDate dataInizio, LocalDate dataFine, PuntoDiEmissione puntoEmissione) {
        this.id = id;
        this.tipologia = tipologia;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.puntoEmissione = puntoEmissione;
    }

    public Tipologia getTipologia() {
        return tipologia;
    }

    public void setTipologia(Tipologia tipologia) {
        this.tipologia = tipologia;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public Abbonamento() {
    }
    
    @Override
    public String toString() {
        return "Abbonamento{" +
                "id=" + id +
                ", tipologia=" + tipologia +
                ", dataInizio=" + dataInizio +
                ", dataFine=" + dataFine +
                ", puntoEmissione=" + (puntoEmissione != null ? puntoEmissione.getCitta() : "Non specificato") +
                '}';
    }
}
