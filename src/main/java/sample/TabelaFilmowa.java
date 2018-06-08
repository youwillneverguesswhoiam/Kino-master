package sample;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Table;
import org.hibernate.cfg.Configuration;

import javax.persistence.Id;

/*Szanowni Państwo,

Zbierałem się trochę do kontynuacji pracy domowej, więc teraz dalszy opis:

* po pierwsze, jeśli jeszcze tego Państwo nie zrobili - filmy i sale trzeba zapisywać do bazy
* dochodzi nowy obiekt - seans. Seans łączy ze sobą film i salę, dodatkowo jest w określonym dniu i godzinie (można wykorzystać LocalDate albo Date)
* chcemy zrobić testy. Za pomocą testów powinniśmy: dodać kilka przykładowych filmów, dodać kilka przykładowych sal, dodać kilka przykładowych seansów, sprawdzić, czy uda się wyszukać dodane filmy po nazwie, sale po numerze, seanse po filmie i sali



Myślimy powoli nad interfejsem użytkownika, czyli jak administrować kinem (dodawać/usuwać sale i filmy, tworzyć/edytować/usuwać seanse).

Pozdrawiam, */

public class TabelaFilmowa implements HierarchicalController<MainController> {

    public TextField nazwa;
    public TextField opis;
    public TextField czas;
    public TextField limit;
    public TableView<Film> tabelka;
    private MainController parentController;

    public void dodaj(ActionEvent actionEvent) {
        Film film = new Film();
        film.setNazwa(nazwa.getText());
        film.setOpis(opis.getText());
        film.setCzas(czas.getText());
        film.setLimit(limit.getText());
        tabelka.getItems().add(film);

/*        SessionFactory sessionFactory = new Configuration()
                .configure("/hibernate.cfg.xml") // configures settings from hibernate.cfg.xml
                .buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Film film1 = (Film) session.load(Film.class, new Integer(1));
        if(film1 != null)
            System.out.println(film1.getNazwa());*/
    }

    public void setParentController(MainController parentController) {
        this.parentController = parentController;
        //tabelka.getItems().addAll(parentController.getDataContainer().getStudents());
        tabelka.setItems(parentController.getDataContainer().getFilmy());
    }

    public void usunZmiany() {
        tabelka.getItems().clear();
        tabelka.getItems().addAll(parentController.getDataContainer().getFilmy());
    }

    public MainController getParentController() {
        return parentController;
    }

    public void initialize() {
        for (TableColumn<Film, ?> studentTableColumn : tabelka.getColumns()) {
            if ("nnazwa".equals(studentTableColumn.getId())) {

                studentTableColumn.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
            } else if ("opis".equals(studentTableColumn.getId())) {
                studentTableColumn.setCellValueFactory(new PropertyValueFactory<>("opis"));
            } else if ("czas".equals(studentTableColumn.getId())) {
                studentTableColumn.setCellValueFactory(new PropertyValueFactory<>("czas"));
            } else if ("limit".equals(studentTableColumn.getId())) {
                studentTableColumn.setCellValueFactory(new PropertyValueFactory<>("limit"));
            }
        }

    }

    public void zapisz(ActionEvent actionEvent) {
        ArrayList<Film> listaFilmowa = new ArrayList<>(tabelka.getItems());
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.obj"))) {
            oos.writeObject(listaFilmowa);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Uwaga na serializację: https://sekurak.pl/java-vs-deserializacja-niezaufanych-danych-i-zdalne-wykonanie-kodu-czesc-i/ */
    public void wczytaj(ActionEvent actionEvent) {
        ArrayList<Film> listaFilmowa;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.obj"))) {
            listaFilmowa = (ArrayList<Film>) ois.readObject();
            tabelka.getItems().clear();
            tabelka.getItems().addAll(listaFilmowa);
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String id;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
