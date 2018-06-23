package sample;

import javafx.scene.control.TextField;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "FILM")

public class Film implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected Integer id;
    @Column (name = "NAZWA")
    protected String nazwa;
    @Column (name = "OPIS")
    protected String opis;
    @Column (name = "CZAS")
    protected String czas;
    @Column (name = "LIMIT")
    protected String limit;
    @Column (name = "KIEDY")
    protected String kiedy;
    @Column (name = "SALA")
    public String sala;

    public Set<Seans> getsSeans() {
        return sSeans;
    }

    public void setsSeans(Set<Seans> sSeans) {
        this.sSeans = sSeans;
    }

    @OneToMany
    private Set<Seans> sSeans = new HashSet<>(0);

    /*@ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(name = "FplusS", joinColumns = @JoinColumn(name = "FILM_ID"), inverseJoinColumns = @JoinColumn(name = "SALE_ID"))*/


    public String getKiedy() {
        return kiedy;
    }

    public String getSala() {
        return sala;
    }


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
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    //public costam getObrazek() {
    //    return obrazek;
    //}

}
