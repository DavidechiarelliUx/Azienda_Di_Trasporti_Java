package it.epicode.Amministratore.Biglietto.PuntoEmissione.Rivenditori;

import it.epicode.Amministratore.Biglietto.PuntoEmissione.PuntoDiEmissione;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rivenditori")
public class Rivenditore extends PuntoDiEmissione {

    @Column(name = "codice_identificativo", nullable = false)
    private int codiceIdentificativo;

    @Column(name = "nome_rivenditore")
    private String nome_rivenditore;

    public int getCodiceIdentificativo() {
        return codiceIdentificativo;
    }

    public void setCodiceIdentificativo(int codiceIdentificativo) {
        this.codiceIdentificativo = codiceIdentificativo;
    }

    public String getNome_rivenditore() {
        return nome_rivenditore;
    }

    public void setNome_rivenditore(String nome_rivenditore) {
        this.nome_rivenditore = nome_rivenditore;
    }

    public Rivenditore() {
    }

    public Rivenditore(Long id, String citta, int codiceIdentificativo, String nome_rivenditore) {
        super(id, citta);
        this.codiceIdentificativo = codiceIdentificativo;
        this.nome_rivenditore = nome_rivenditore;
    }

    @Override
    public String toString() {
        return "Rivenditore{" +
                "codiceIdentificativo=" + codiceIdentificativo +
                ", nome_rivenditore='" + nome_rivenditore + '\'' +
                '}';
    }
}
