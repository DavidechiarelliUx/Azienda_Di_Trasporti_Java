package it.epicode.Mezzi.StatoMezzo;

import it.epicode.Mezzi.Mezzo;
import it.epicode.Mezzi.TipoMezzo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table  (name = "mezzi_in_manutenzione")
public class MezzoInManutenzione extends Mezzo {

    @Column ( nullable = false)
    private LocalDate  dataInizioManutenzione;

    @Column ( nullable = true)
    private LocalDate dataFineManutenzione;

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

    public MezzoInManutenzione(Long id, int capienzaMax, TipoMezzo tipoMezzo, LocalDate dataInizioManutenzione, LocalDate dataFineManutenzione) {
        super(id, capienzaMax, tipoMezzo);
        this.dataInizioManutenzione = dataInizioManutenzione;
        this.dataFineManutenzione = dataFineManutenzione;
    }

    public MezzoInManutenzione(LocalDate dataInizioManutenzione, LocalDate dataFineManutenzione) {
        this.dataInizioManutenzione = dataInizioManutenzione;
        this.dataFineManutenzione = dataFineManutenzione;
    }

    public MezzoInManutenzione() {
    }
}
