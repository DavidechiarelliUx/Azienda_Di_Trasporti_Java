package it.epicode.classi.gestione_mezzi.stato_mezzo;

import it.epicode.classi.gestione_mezzi.mezzo.Mezzo;
import it.epicode.classi.gestione_mezzi.mezzo.TipoMezzo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "mezzi_in_servizio")
public class MezzoInServizio extends Mezzo {
    
    @Column(nullable = true)
    private LocalDate dataInizioServizio;
    
    @Column(nullable = true)
    private LocalDate dataFineServizio;
    
    public MezzoInServizio(int capienzaMax, TipoMezzo tipoMezzo, LocalDate dataInizioServizio, LocalDate dataFineServizio) {
        super(capienzaMax, tipoMezzo);
        this.dataInizioServizio = dataInizioServizio;
        this.dataFineServizio = dataFineServizio;
    }
    
    public MezzoInServizio() {
    }
    
    public LocalDate getDataInizioServizio() {
        return dataInizioServizio;
    }
    
    public void setDataInizioServizio(LocalDate dataInizioServizio) {
        this.dataInizioServizio = dataInizioServizio;
    }
    
    public LocalDate getDataFineServizio() {
        return dataFineServizio;
    }
    
    public void setDataFineServizio(LocalDate dataFineServizio) {
        this.dataFineServizio = dataFineServizio;
    }
    
    @Override
    public String toString() {
        return "MezzoInServizio{" +
                "id=" + getId() +
                ", capienzaMax=" + getCapienzaMax() +
                ", tipoMezzo=" + getTipoMezzo() +
                ", dataInizioServizio=" + dataInizioServizio +
                ", dataFineServizio=" + dataFineServizio +
                '}';
    }
}
