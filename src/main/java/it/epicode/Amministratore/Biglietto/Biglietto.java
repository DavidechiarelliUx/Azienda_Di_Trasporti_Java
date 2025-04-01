package it.epicode.Amministratore.Biglietto;

import it.epicode.Amministratore.Biglietto.PuntoEmissione.PuntoDiEmissione;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "biglietti")
public class Biglietto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "punto_di_emissione_id", nullable = false)
    private PuntoDiEmissione puntoDiEmissione;
    
    @Column(name = "data_di_emissione", nullable = false)
    private LocalDate dataDiEmissione;
    
    @Column(name = "vidimato", nullable = false)
    private Boolean vidimato = false;
    
    public Biglietto() {}
    
    public Biglietto(PuntoDiEmissione puntoDiEmissione, LocalDate dataDiEmissione) {
        this.puntoDiEmissione = puntoDiEmissione;
        this.dataDiEmissione = dataDiEmissione;
        this.vidimato = false;
    }
    
    public Long getId() { return id; }
    
    public PuntoDiEmissione getPuntoDiEmissione() { return puntoDiEmissione; }
    
    public void setPuntoDiEmissione(PuntoDiEmissione puntoDiEmissione) { this.puntoDiEmissione = puntoDiEmissione; }
    
    public LocalDate getDataDiEmissione() { return dataDiEmissione; }
    
    public void setDataDiEmissione(LocalDate dataDiEmissione) { this.dataDiEmissione = dataDiEmissione; }
    
    public boolean isVidimato() { return Boolean.TRUE.equals(this.vidimato); }
    
    public void setVidimato(boolean vidimato) { this.vidimato = vidimato; }
    
    @Override
    public String toString() {
        return "Biglietto{" +
                "ID=" + id +
                ", PuntoEmissione=" + puntoDiEmissione +
                ", DataEmissione=" + dataDiEmissione +
                ", Vidimato=" + vidimato +
                '}';
    }
}
