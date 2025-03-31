package it.epicode.Amministratore.Abbonamento.Abbonamenti;

import it.epicode.Amministratore.Abbonamento.Tessera.Tessera;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "abbonamenti")
public class Abbonamento {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private long id;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tipologia tipologia;
    
    private LocalDate dataInizio;
    private LocalDate dataFine;

    @OneToOne
    @JoinColumn (name = "tessera_id")
    private Tessera tessera;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Abbonamento(long id, Tipologia tipologia, LocalDate dataInizio, LocalDate dataFine) {
        this.id = id;
        this.tipologia = tipologia;
        this.dataInizio = dataInizio;
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
                '}';
    }
}
