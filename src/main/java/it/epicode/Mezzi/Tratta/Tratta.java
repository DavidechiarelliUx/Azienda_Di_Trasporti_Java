package it.epicode.Mezzi.Tratta;

import it.epicode.Mezzi.Mezzo;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;

import java.util.List;


@Entity
@Table(name = "tratte")
public class Tratta {
    @Column
    (name = "zona_partenza", nullable = false)
    private String zonaPartenza;
    @Column
    (name = "capolinea", nullable = false)
    private String capolinea;
    @Column
    (name = "tempo_medio_percorrenza", nullable = false)
    private int tempoMedioPercorrenza;

    @Column
    (name = "tratta_percorsa")
    private int trattaPercorsa;

    @OneToMany(mappedBy = "tratta",  cascade = CascadeType.ALL)
    @JoinColumn(name = "mezzo_id", nullable = false)
    private List<Mezzo> mezzo;

    public String getZonaPartenza() {
        return zonaPartenza;
    }

    public void setZonaPartenza(String zonaPartenza) {
        this.zonaPartenza = zonaPartenza;
    }

    public String getCapolinea() {
        return capolinea;
    }

    public void setCapolinea(String capolinea) {
        this.capolinea = capolinea;
    }

    public int getTempoMedioPercorrenza() {
        return tempoMedioPercorrenza;
    }

    public void setTempoMedioPercorrenza(int tempoMedioPercorrenza) {
        this.tempoMedioPercorrenza = tempoMedioPercorrenza;
    }

    public int getTrattaPercorsa() {
        return trattaPercorsa;
    }

    public void setTrattaPercorsa(int trattaPercorsa) {
        this.trattaPercorsa = trattaPercorsa;
    }

    public List<Mezzo> getMezzo() {
        return mezzo;
    }

    public void setMezzo(List<Mezzo> mezzo) {
        this.mezzo = mezzo;
    }

    public Tratta(String zonaPartenza, String capolinea, int tempoMedioPercorrenza, int trattaPercorsa, List<Mezzo> mezzo) {
        this.zonaPartenza = zonaPartenza;
        this.capolinea = capolinea;
        this.tempoMedioPercorrenza = tempoMedioPercorrenza;
        this.trattaPercorsa = trattaPercorsa;
        this.mezzo = mezzo;
    }

    public Tratta() {
    }
    @Override
    public String toString() {
        return "Tratta{" +
                "zonaPartenza='" + zonaPartenza + '\'' +
                ", capolinea='" + capolinea + '\'' +
                ", tempoMedioPercorrenza=" + tempoMedioPercorrenza +
                ", trattaPercorsa=" + trattaPercorsa +
                ", mezzo=" + mezzo +
                '}';
    }
}
