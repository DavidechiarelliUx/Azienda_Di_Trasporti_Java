package it.epicode.Amministratore.Biglietto.PuntoEmissione.Rivenditori;

import it.epicode.Amministratore.Biglietto.PuntoEmissione.PuntoDiEmissione;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "rivenditori")
public class Rivenditore extends PuntoDiEmissione {
    
    @Column(name = "codice_identificativo", nullable = false)
    private int codiceIdentificativo;
    
    @Column(name = "nome_rivenditore", nullable = false)
    private String nomeRivenditore;
    
    @Column(name = "attivo", nullable = false)
    private boolean attivo;
    
    public int getCodiceIdentificativo() {
        return codiceIdentificativo;
    }
    
    public void setCodiceIdentificativo(int codiceIdentificativo) {
        this.codiceIdentificativo = codiceIdentificativo;
    }
    
    public String getNomeRivenditore() {
        return nomeRivenditore;
    }
    
    public void setNomeRivenditore(String nomeRivenditore) {
        this.nomeRivenditore = nomeRivenditore;
    }
    
    public Rivenditore() {
    }
    
    public Rivenditore(Long id, String citta, int codiceIdentificativo, String nomeRivenditore) {
        super(id, citta);
        this.codiceIdentificativo = codiceIdentificativo;
        this.nomeRivenditore = nomeRivenditore;
    }
    
    @Override
    public String toString() {
        return "Rivenditore{" +
                "codiceIdentificativo=" + codiceIdentificativo +
                ", nomeRivenditore='" + nomeRivenditore + '\'' +
                ", citta=" + getCitta() +
                '}';
    }
}

