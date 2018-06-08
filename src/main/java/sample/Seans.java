package sample;

import javafx.scene.control.TextField;

import java.time.LocalDate;

public class Seans {

    private String tnr_sali;
    private String nazwa;
    private String czas;
    private String limit;
    private LocalDate kiedy_jest;

    public Seans(String tnr_sali, String nazwa, String czas, String limit, LocalDate kiedy_jest) {
        this.tnr_sali = tnr_sali;
        this.nazwa = nazwa;
        this.czas = czas;
        this.limit = limit;
        this.kiedy_jest = kiedy_jest;
    }

    public void setTnr_sali(String tnr_sali) {
        this.tnr_sali = tnr_sali;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setCzas(String czas) {
        this.czas = czas;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public void setKiedy_jest(LocalDate kiedy_jest) {
        this.kiedy_jest = kiedy_jest;
    }

    public String getTnr_sali() {
        return tnr_sali;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getCzas() {
        return czas;
    }

    public String getLimit() {
        return limit;
    }

    public LocalDate getKiedy_jest() {
        return kiedy_jest;
    }
}
