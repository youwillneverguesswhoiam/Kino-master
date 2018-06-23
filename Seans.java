package sample;

import javafx.scene.control.TextField;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

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
    @Column(name = "TIME")
protected LocalTime time;
    @Column(name = "NAZWA1")
protected String nazwa;// = f.getNazwa();
    @Column(name = "SALA1")
    protected String sala;// = f.getSala();
    @ManyToOne
    @JoinColumn(name = "FILM_ID")
    public Film getF() {
        return f;
    }
    public void setF(Film f) {
        this.f = f;
    }

    @JoinColumn(name = "SALE_ID")
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

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }
    public Seans (Film f, Sale s, LocalDate d, LocalTime t) {
        this.f = f;
        this.s=s;
        this.datum = d;
        this.time = t;


    }

}
