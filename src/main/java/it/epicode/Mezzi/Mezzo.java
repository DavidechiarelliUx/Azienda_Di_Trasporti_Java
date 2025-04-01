package it.epicode.Mezzi;

import it.epicode.Mezzi.StatoMezzo.MezzoInManutenzione;
import it.epicode.Mezzi.StatoMezzo.MezzoInServizio;
import jakarta.persistence.*;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "mezzi")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Mezzo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "capienza_max",  nullable = false)
    private int capienzaMax;

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


    public TipoMezzo getTipoMezzo() {
        return tipoMezzo;
    }

    public void setTipoMezzo(TipoMezzo tipoMezzo) {
        this.tipoMezzo = tipoMezzo;
    }
    public Mezzo(Long id, int capienzaMax,  TipoMezzo tipoMezzo) {
        this.id = id;
        this.capienzaMax = capienzaMax;
        this.tipoMezzo = tipoMezzo;
    }
    public Mezzo() {
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
