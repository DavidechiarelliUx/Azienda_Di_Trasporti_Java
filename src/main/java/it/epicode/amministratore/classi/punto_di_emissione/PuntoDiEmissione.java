package it.epicode.amministratore.classi.punto_di_emissione;

import it.epicode.amministratore.classi.biglietto.Biglietto;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "punti_di_emissione")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PuntoDiEmissione {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @Column(name = "citta", nullable = false)
    private String citta;
    
    @OneToMany(mappedBy = "puntoDiEmissione")
    private List<Biglietto> biglietti = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public PuntoDiEmissione() {
    }

    public PuntoDiEmissione(Long id, String citta) {
        this.id = id;
        this.citta = citta;
    }

    @Override
    public String toString() {
        return "PuntoDiEmissione{" +
                "id=" + id +
                ", citta='" + citta + '\'' +
                '}';
    }
    
    public boolean isAttivo() {
        return true;
    }
}
