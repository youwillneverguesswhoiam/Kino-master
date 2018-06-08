package sample;

import javafx.scene.control.TextField;

import javax.persistence.*;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table (name = "FILM")

public class Film implements Serializable{

    //Na początek, jako obiekty: film (nazwa, opis, czas trwania w minutach, limit wiekowy, dla chętnych - obrazek z ilustracją),
    // sala (number, liczba miejsc, typ sali). Trzeba zrobić jakąś formatkę pozwalającą na dodawanie filmów oraz sal.
    // Docelowo także edycja / usuwanie.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String nazwa;
    public String opis;
    public String czas;
    public String limit;
    public String kiedy;

    public String getKiedy() {
        return kiedy;
    }

    public String getSala() {
        return sala;
    }

    public String sala;
    //public cośtam obrazek;

    public void setKiedy(String kiedy) {
        this.kiedy = kiedy;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getNazwa() {

        return nazwa;
    }

    public void setNazwa(String nazwa) {

        this.nazwa = nazwa;
    }

    public String getOpis() {

        return opis;
    }

    public void setOpis(String opis) {

        this.opis = opis;
    }

    public String getCzas() {

        return czas;
    }

    public void setCzas(String czas) {

        this.czas = czas;
    }

    public String getLimit() {

        return limit;
    }

    public void setLimit(String limit) {

        this.limit = limit;
    }

    //public costam getObrazek() {
    //    return obrazek;
    //}

}
