package it.epicode.classi.gestione_mezzi.mezzo;

import it.epicode.classi.gestione_funzionalita_utente.biglietto.Biglietto;
import it.epicode.classi.gestione_mezzi.tratta.Tratta;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mezzi")
public abstract class Mezzo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @Column(name = "capienza_max", nullable = false)
    private int capienzaMax;
    
    @Enumerated(EnumType.STRING)
    private TipoMezzo tipoMezzo;

    @OneToMany(mappedBy = "mezzo")
    private List<Biglietto> biglietti = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "tratta_id")
    private Tratta tratta;


    public Mezzo(int capienzaMax, TipoMezzo tipoMezzo) {
        this.capienzaMax = capienzaMax;
        this.tipoMezzo = tipoMezzo;
    }
    
    public Mezzo() {
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public int getCapienzaMax() {
        return capienzaMax;
    }
    
    public void setCapienzaMax(int capienzaMax) {
        this.capienzaMax = capienzaMax;
    }
    
    public TipoMezzo getTipoMezzo() {
        return tipoMezzo;
    }
    
    public void setTipoMezzo(TipoMezzo tipoMezzo) {
        this.tipoMezzo = tipoMezzo;
    }

    public Tratta getTratta() {
        return tratta;
    }

    public void setTratta(Tratta tratta) {
        this.tratta = tratta;
    }

    @Override
    public String toString() {
        return "Mezzo{" +
                "id=" + id +
                ", capienzaMax=" + capienzaMax +
                ", tipoMezzo=" + tipoMezzo +
                '}';
    }
}
