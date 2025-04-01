package it.epicode.Mezzi.StatoMezzo;

import it.epicode.Mezzi.Mezzo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table (name = "mezzi_in_servizio")
public class MezzoInServizio extends Mezzo {

    @Column  (nullable = false)
    private LocalDate dataInizioServizio;

    @Column  (nullable = true)
    private LocalDate dataFineServizio;


    public LocalDate getDataFineServizio() {
        return dataFineServizio;
    }

    public void setDataFineServizio(LocalDate dataFineServizio) {
        this.dataFineServizio = dataFineServizio;
    }

    public LocalDate getDataInizioServizio() {
        return dataInizioServizio;
    }

    public void setDataInizioServizio(LocalDate dataInizioServizio) {
        this.dataInizioServizio = dataInizioServizio;
    }


    public MezzoInServizio(LocalDate dataFineServizio, LocalDate dataInizioServizio) {

        this.dataFineServizio = dataFineServizio;
        this.dataInizioServizio = dataInizioServizio;
    }

    public MezzoInServizio () {
    }
    public MezzoInServizio(Long id, int capienzaMax, LocalDate dataInizioServizio, LocalDate dataFineServizio) {
        super(id, capienzaMax);
        this.dataInizioServizio = dataInizioServizio;
        this.dataFineServizio = dataFineServizio;
    }
}
