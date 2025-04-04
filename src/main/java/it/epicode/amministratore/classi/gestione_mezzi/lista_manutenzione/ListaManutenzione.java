package it.epicode.amministratore.classi.gestione_mezzi.lista_manutenzione;

import it.epicode.amministratore.classi.gestione_mezzi.mezzo.Mezzo;
import jakarta.persistence.*;
import jakarta.persistence.GenerationType;

import java.time.LocalDate;

@Entity
@Table(name = "lista_manutenzioni")
public class ListaManutenzione{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "data_inizio_manutenzione",  nullable = false)
    private LocalDate dataInizioManutenzione;
    @Column(name = "data_fine_manutenzione", nullable = false)
    private LocalDate dataFineManutenzione;
    @Column(name = "descrizione_manutenzione")
    private String descrizioneManutenzione;

    @ManyToOne
    @JoinColumn(name = "mezzo_id")
    private Mezzo mezzo;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
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

    public String getDescrizioneManutenzione() {
        return descrizioneManutenzione;
    }

    public void setDescrizioneManutenzione(String descrizioneManutenzione) {
        this.descrizioneManutenzione = descrizioneManutenzione;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

    public ListaManutenzione() {
    }
    public ListaManutenzione(Mezzo mezzo,LocalDate dataInizioManutenzione, LocalDate dataFineManutenzione, String descrizioneManutenzione) {
         this.mezzo = mezzo;
        this.dataInizioManutenzione = dataInizioManutenzione;
        this.dataFineManutenzione = dataFineManutenzione;
        this.descrizioneManutenzione = descrizioneManutenzione;
    }


}
