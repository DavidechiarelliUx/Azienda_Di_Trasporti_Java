package it.epicode.classi.gestione_mezzi.tratta;

import it.epicode.classi.gestione_mezzi.mezzo.Mezzo;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "tratte")
public class Tratta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
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
    
    @OneToMany(mappedBy = "tratta",  cascade = CascadeType.PERSIST)
    private List<Mezzo> mezzo = new ArrayList<>();

    @Column(name = "tipo_tratta", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoTratta tipoTratta;

    @Column(name = "codiceidentificativo", nullable = false)
    private Long codiceIdentificativo;

    public Long getCodiceIdentificativo() {
        return codiceIdentificativo;
    }

    public void setCodiceIdentificativo(Long codiceIdentificativo) {
        this.codiceIdentificativo = codiceIdentificativo;
    }

    @Column
    (name = "numero_volte",  nullable = false, columnDefinition = "int default 0")
    private int numeroVolte = 0;

    public int getNumeroVolte() {
        return numeroVolte;
    }

    public void setNumeroVolte(int numeroVolte) {
        this.numeroVolte = numeroVolte;
    }

    public String getZonaPartenza() {
        return zonaPartenza;
    }
    
    public void setZonaPartenza(String zonaPartenza) {
        this.zonaPartenza = zonaPartenza;
    }
    
    public String getCapolinea() {
        return capolinea;
    }
    
    public Long getCodiceIdentificativo() {
        return codiceIdentificativo;
    }
    
    public void setCodiceIdentificativo(Long codiceIdentificativo) {
        this.codiceIdentificativo = codiceIdentificativo;
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
    
    public Tratta(String zonaPartenza, String capolinea, int tempoMedioPercorrenza, int trattaPercorsa,  TipoTratta tipoTratta, Long codiceIdentificativo) {
        this.zonaPartenza = zonaPartenza;
        this.capolinea = capolinea;
        this.tempoMedioPercorrenza = tempoMedioPercorrenza;
        this.trattaPercorsa = trattaPercorsa;
        this.tipoTratta = tipoTratta;
        this.codiceIdentificativo = codiceIdentificativo;
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
