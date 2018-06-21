package sample;

import javafx.scene.control.TextField;

import javax.persistence.*;
import java.time.LocalDate;

import static org.hibernate.sql.InFragment.NULL;

@Entity
@Table(name = "SEANS")
public class Seans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected Integer Seans_id;
    @Column(name = "FILM1")
protected Film f;
    @Column(name = "SALACLS")
protected Sale s;
    @Column(name = "DATUM")
protected LocalDate datum;
    @Column(name = "NAZWA1")
protected String nazwa;// = f.getNazwa();
    @Column(name = "SALA1")
    protected String sala;// = f.getSala();
    public Film getF() {
        return f;
    }

    public Seans () {
        this.f = new Film();
        this.s=new Sale();
        this.datum=LocalDate.now();
        if (f.getNazwa()!=NULL)
        this.nazwa = f.getNazwa();
        if (f.getSala()!=NULL)
        this.sala = f.getSala();
    }

    public void setF(Film f) {
        this.f = f;
    }

    public Sale getS() {
        return s;
    }

    public void setS(Sale s) {
        this.s = s;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public Integer getSeans_id() {
        return Seans_id;
    }

    public void setSeans_id(Integer seans_id) {
        Seans_id = seans_id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }
}
