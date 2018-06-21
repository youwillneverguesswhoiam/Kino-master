package sample;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "SALE")
public class Sale implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected Integer id;

    @Column(name = "NAZWA2")
    protected String nazwa;
    @Column(name = "SALA2")
    protected String sala;
    @Column(name = "KIEDY2")
    protected String kiedy;
    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "salki")
    private Collection<Film> filmiki = new ArrayList<Film>();
    /*@ManyToMany
    @JoinTable(name = "GROUPS_FOR_STUDENTS", joinColumns = @JoinColumn(name = "GROUP_ID"), inverseJoinColumns = @JoinColumn(name = "STUDENT_ID"))
    protected List<Student> students;*/

/*protected List<Film> Films;

    public List<Film> getFilms() {
        return Films;
    }

    public void setFilms(List<Film> Films) {
        this.Films = Films;
    }*/

    public Collection<Film> getFilmiki() {
        return filmiki;
    }

    public void setFilmiki(Collection<Film> filmiki) {
        this.filmiki = filmiki;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return nazwa + " - " + kiedy;
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

    public String getKiedy() {
        return kiedy;
    }

    public void setKiedy(String kiedy) {
        this.kiedy = kiedy;
    }
}

