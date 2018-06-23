package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Region;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Seanse implements HierarchicalController<MainController> {
    private boolean wczytano=false;
    public TextField tnazwa;
    public TextField tnr_sali;
    public TextField tkiedy;
    public TextField ttime;
    public TableView<Seans> tabelka;
    public ComboBox<String> filmson;
    public ComboBox<String> salson;
    public DatePicker datownik;
    public LocalTime t;
    public ChoiceBox <LocalTime> godzina1 = new ChoiceBox<>();
    public Seans s = new Seans (null, null, null, null);
    private final Configuration conf=new Configuration().configure("/hibernate.cfg.xml");
    private final SessionFactory sessionFactory= conf.buildSessionFactory();
    protected ObservableList<Sale> sale= FXCollections.observableArrayList();
    protected ObservableList<Film> filmy = FXCollections.observableArrayList();
    protected ObservableList<Seans> seanse= FXCollections.observableArrayList();
    private MainController parentController;

    @Override
    public MainController getParentController() {
        return parentController;
    }

    @Override
    public void setParentController(MainController parentController) {
        this.parentController = parentController;
        tabelka.setEditable(true);
        //tabelka.getItems().addAll(parentController.getDataContainer().getStudents());
        tabelka.setItems(parentController.getDataContainer().getSeanse());
    }
    public void loadFilmFromDatabase(javafx.scene.input.MouseEvent mouseEvent) {
        filmson.getItems().clear();
        try (Session ses = sessionFactory.openSession()) {

            ses.beginTransaction();
            Query<Film> query = ses.createQuery("from Film", Film.class);
            if (filmy.isEmpty())
            filmy.addAll(query.list());
            ObservableList<String> movies = FXCollections.observableArrayList();
            for (Film fifi : filmy) {
                movies.add( fifi.getNazwa());
            }
            filmson.getItems().addAll(movies);

            s.setF(filmy.get(0));
            s.setNazwa(filmson.getValue());
            ses.getTransaction().commit();
            ses.close();

        } catch (HibernateException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
    }
    public void loadSalaFromDatabase() {
        try (Session ses = sessionFactory.openSession()) {
            salson.getItems().clear();
            ses.beginTransaction();
            Query<Sale> query = ses.createQuery("from Sale", Sale.class);
            if (sale.isEmpty())
            sale.addAll(query.list());
            ObservableList<String> movies = FXCollections.observableArrayList();
            for (Sale fifi : sale) {
                movies.add( fifi.getNazwa());
            }
            salson.getItems().addAll(movies);
            s.setS(sale.get(0));
            s.setSala(salson.getValue());
            ses.getTransaction().commit();

            godzina1.setItems(FXCollections.observableArrayList(LocalTime.of(13,00),LocalTime.of(17,00),LocalTime.of(21,00)));
            //System.out.println("salesalesale:" + s.getSala()+ ":salesalesale");
        } catch (HibernateException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
    }
    public void choiceB() {
         //FXCollections.observableArrayList(
        //LocalTime.of(13,00),LocalTime.of(17,00),LocalTime.of(21,00))
        godzina1.setItems(FXCollections.observableArrayList(LocalTime.of(13,00),LocalTime.of(17,00),LocalTime.of(21,00)));

    }
    public void loadF (){
        t = godzina1.getSelectionModel().getSelectedItem();

        s = new Seans (filmy.get(0),sale.get(0),datownik.getValue(),t );
        s.setNazwa(filmy.get(0).getNazwa());
        s.setSala(sale.get(0).getNazwa());
        /*s.setS(sale.get(0));
        s.setSala(salson.getValue());*/

        /*s.setF(filmy.get(0));
        s.setNazwa(filmson.getValue());*/

        /*s.setDatum(datownik.getValue());
        s.setTime(godzina1.getValue());*/
        tabelka.getItems().add(s);

        //s.setTime();
    }
    private void loadSeansFromDatabase() {


        List<Film> juzsa = new ArrayList<>();
        String nazwa=filmy.get(0).getNazwa();
        try (Session ses = sessionFactory.openSession()) {
            ses.beginTransaction();
            Query queryF = ses.createQuery("from Film film where film.nazwa = :nazwa");
            queryF.setParameter("nazwa", nazwa);
            //List listF = queryF.list();
            Film fi = (Film) queryF.list().get(0);
            juzsa.addAll(queryF.list());
            ses.getTransaction().commit();
            //System.out.println ("Dlugi String a w nim" + fi.getNazwa() + " pierwszy film jego tytul:"+ juzsa.isEmpty() );

        } catch (HibernateException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
    }
    public void initialize() {

        for (TableColumn<Seans, ?> studentTableColumn : tabelka.getColumns()) {

            if ("tnazwa".equals(studentTableColumn.getId())) {

                studentTableColumn.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
            } else if ("tnr_sali".equals(studentTableColumn.getId())) {
                studentTableColumn.setCellValueFactory(new PropertyValueFactory<>("sala"));
            } else if ("tkiedy".equals(studentTableColumn.getId())) {
                studentTableColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
            }
                else if ("ttime".equals(studentTableColumn.getId())) {
                studentTableColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
            }
        }

    }

}
