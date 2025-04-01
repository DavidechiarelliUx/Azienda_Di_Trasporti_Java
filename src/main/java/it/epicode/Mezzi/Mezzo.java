package it.epicode.Mezzi;

import jakarta.persistence.*;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "mezzi")
public class Mezzo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "capienza_max",  nullable = false)
    private int capienzaMax;
    @Column(name = "stato_servizio", nullable = false)
    private boolean statoServizio;
    @Enumerated(EnumType.STRING)
    private TipoMezzo tipoMezzo;

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

    public boolean isStatoServizio() {
        return statoServizio;
    }

    public void setStatoServizio(boolean statoServizio) {
        this.statoServizio = statoServizio;
    }

    public TipoMezzo getTipoMezzo() {
        return tipoMezzo;
    }

    public void setTipoMezzo(TipoMezzo tipoMezzo) {
        this.tipoMezzo = tipoMezzo;
    }
    public Mezzo(Long id, int capienzaMax, boolean statoServizio, TipoMezzo tipoMezzo) {
        this.id = id;
        this.capienzaMax = capienzaMax;
        this.statoServizio = statoServizio;
        this.tipoMezzo = tipoMezzo;
    }
    public Mezzo() {
    }
    @Override
    public String toString() {
        return "Mezzo{" +
                "id=" + id +
                ", capienzaMax=" + capienzaMax +
                ", statoServizio=" + statoServizio +
                ", tipoMezzo=" + tipoMezzo +
                '}';
    }
}
