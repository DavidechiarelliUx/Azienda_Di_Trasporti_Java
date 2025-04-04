package it.epicode.amministratore.classi.punto_di_emissione.distributore;

import it.epicode.amministratore.classi.punto_di_emissione.PuntoDiEmissione;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "distributori")
public class Distributore extends PuntoDiEmissione {
    
    @Column(name = "attivo", nullable = false)
    private boolean attivo;
    
    public boolean isAttivo() {
        return attivo;
    }
    
    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
    }
    
    public Distributore() {
    }
    
    public Distributore(Long id, String citta) {
        super(id, citta);
    }
    
    public Distributore(boolean attivo) {
        this.attivo = attivo;
    }
    
    public Distributore(Long id, String citta, boolean attivo) {
        super(id, citta);
        this.attivo = attivo;
    }
    
    @Override
    public String toString() {
        return "Distributore{" +
                "attivo=" + attivo +
                '}';
    }
}