package it.epicode.classi.gestione_mezzi.tratta;

import it.epicode.classi.gestione_mezzi.mezzo.Mezzo;
import jakarta.persistence.*;

@Entity
@Table(name = "tratte")
public class Tratta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @Column(name = "zona_partenza", nullable = false)
    private String zonaPartenza;
    
    @Column(name = "capolinea", nullable = false)
    private String capolinea;
    
    @Column(name = "tempo_medio_percorrenza", nullable = false)
    private int tempoMedioPercorrenza;
    
    @ManyToOne
    @JoinColumn(name = "mezzo_id", nullable = true)
    private Mezzo mezzo;
    
    @Column(name = "tratta_percorsa")
    private int trattaPercorsa;
    
    @Column(name = "tipo_tratta", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoTratta tipoTratta;
    
    @Column(name = "numero_volte", nullable = false, columnDefinition = "int default 0")
    private int numeroVolte = 0;
    
    public Tratta(String zonaPartenza, String capolinea, int tempoMedioPercorrenza, int trattaPercorsa, TipoTratta tipoTratta, Mezzo mezzo) {
        this.zonaPartenza = zonaPartenza;
        this.capolinea = capolinea;
        this.tempoMedioPercorrenza = tempoMedioPercorrenza;
        this.trattaPercorsa = trattaPercorsa;
        this.tipoTratta = tipoTratta;
        this.mezzo = mezzo;
    }
    
    public Tratta() {
    }
    
    // Getter e Setter aggiornati
    public Mezzo getMezzo() {
        return mezzo;
    }
    
    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
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
    
    public int getNumeroVolte() {
        return numeroVolte;
    }
    
    public void setNumeroVolte(int numeroVolte) {
        this.numeroVolte = numeroVolte;
    }
    
    @Override
    public String toString() {
        return "Tratta{" +
                "zonaPartenza='" + zonaPartenza + '\'' +
                ", capolinea='" + capolinea + '\'' +
                ", tempoMedioPercorrenza=" + tempoMedioPercorrenza +
                ", trattaPercorsa=" + trattaPercorsa +
                ", mezzoId=" + (mezzo != null ? mezzo.getId() : "null") + // Usa l'ID del mezzo
                '}';
    }
}
