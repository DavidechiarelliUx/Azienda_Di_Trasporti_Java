package it.epicode.Amministratore.Biglietto;

import it.epicode.Amministratore.Biglietto.PuntoEmissione.PuntoDiEmissione;
import jakarta.persistence.*;
import jakarta.persistence.GenerationType;

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
    private LocalDate data_di_emissione;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PuntoDiEmissione getPuntoDiEmissione() {
        return puntoDiEmissione;
    }

    public void setPuntoDiEmissione(PuntoDiEmissione puntoDiEmissione) {
        this.puntoDiEmissione = puntoDiEmissione;
    }

    public LocalDate getData_di_emissione() {
        return data_di_emissione;
    }

    public void setData_di_emissione(LocalDate data_di_emissione) {
        this.data_di_emissione = data_di_emissione;
    }

    public Biglietto() {
    }

    public Biglietto(Long id, PuntoDiEmissione puntoDiEmissione, LocalDate data_di_emissione) {
        this.id = id;
        this.puntoDiEmissione = puntoDiEmissione;
        this.data_di_emissione = data_di_emissione;
    }

    @Override
    public String toString() {
        return "Biglietto{" +
                "id=" + id +
                ", puntoDiEmissione=" + puntoDiEmissione +
                ", data_di_emissione=" + data_di_emissione +
                '}';
    }
}
