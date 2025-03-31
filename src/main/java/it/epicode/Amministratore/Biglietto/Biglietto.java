package it.epicode.Amministratore.Biglietto;

import it.epicode.Amministratore.Biglietto.Distributori.Distributore;
import it.epicode.Amministratore.Biglietto.Rivenditori.Rivenditore;
import jakarta.persistence.*;
import jakarta.persistence.GenerationType;

import java.time.LocalDate;

@Entity
@Table(name = "biglietti")
public class Biglietto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "zona_di_emissione", nullable = false)
    private String zona_di_emissione;
    @Column(name = "data_di_emissione", nullable = false)
    private LocalDate data_di_emissione;

    //private Rivenditore rivenditore;
    //    private Distributore distributore;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZona_di_emissione() {
        return zona_di_emissione;
    }

    public void setZona_di_emissione(String zona_di_emissione) {
        this.zona_di_emissione = zona_di_emissione;
    }

    public LocalDate getData_di_emissione() {
        return data_di_emissione;
    }

    public void setData_di_emissione(LocalDate data_di_emissione) {
        this.data_di_emissione = data_di_emissione;
    }

    //public Rivenditore getRivenditore() {
    //        return rivenditore;
    //    }
    //
    //    public void setRivenditore(Rivenditore rivenditore) {
    //        this.rivenditore = rivenditore;
    //    }
    //
    //    public Distributore getDistributore() {
    //        return distributore;
    //    }
    //
    //    public void setDistributore(Distributore distributore) {
    //        this.distributore = distributore;
    //    }

    public Biglietto() {
    }
    public Biglietto(Long id, String zona_di_emissione, LocalDate data_di_emissione) {
        this.id = id;
        this.zona_di_emissione = zona_di_emissione;
        this.data_di_emissione = data_di_emissione;
        //this.rivenditore = rivenditore;
        // this.distributore = distributore;
    }
}
