package it.epicode.classi.gestione_mezzi.stato_mezzo;

import it.epicode.classi.gestione_mezzi.mezzo.Mezzo;
import it.epicode.classi.gestione_mezzi.mezzo.TipoMezzo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "mezzi_in_manutenzione")
public class MezzoInManutenzione extends Mezzo {
    
    @Column(nullable = true)
    private LocalDate dataInizioManutenzione;
    
    @Column(nullable = true)
    private LocalDate dataFineManutenzione;
    
    public MezzoInManutenzione( int capienzaMax, TipoMezzo tipoMezzo,  LocalDate dataInizioManutenzione, LocalDate dataFineManutenzione) {
        super(capienzaMax, tipoMezzo);
        this.dataInizioManutenzione = dataInizioManutenzione;
        this.dataFineManutenzione = dataFineManutenzione;
    }
    
    public MezzoInManutenzione() {
    }
    
    public LocalDate getDataInizioManutenzione() {
        return dataInizioManutenzione;
    }
    
    public void setDataInizioManutenzione(LocalDate dataInizioManutenzione) {
        this.dataInizioManutenzione = dataInizioManutenzione;
    }
    
    public LocalDate getDataFineManutenzione() {
        return dataFineManutenzione;
    }
    
    public void setDataFineManutenzione(LocalDate dataFineManutenzione) {
        this.dataFineManutenzione = dataFineManutenzione;
    }
    
    @Override
    public String toString() {
        return "MezzoInManutenzione{" +
                "id=" + getId() +
                ", capienzaMax=" + getCapienzaMax() +
                ", tipoMezzo=" + getTipoMezzo() +
                ", dataInizioManutenzione=" + dataInizioManutenzione +
                ", dataFineManutenzione=" + dataFineManutenzione +
                '}';
    }
}
