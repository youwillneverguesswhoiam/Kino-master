package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 * Created by pwilkin on 30-Nov-17.
 */
public class DataContainer {
    protected ObservableList<Sale> sale;
    protected ObservableList<Film> filmy;
    protected ObservableList<Seans> seanse;
    private final Configuration conf;
    private final SessionFactory sessionFactory;

    public ObservableList<Film> getFilmy() {
        return filmy;
    }
    public ObservableList<Sale> getSale() {
        return sale;
    }
    public ObservableList<Seans> getSeanse() {
        return seanse;
    }
    public void setFilmy(List<Film> filmy) {
        this.filmy = FXCollections.observableArrayList(filmy);
    }
    public void setSale(List<Sale> sale) {
        this.sale = FXCollections.observableArrayList(sale);
    }

    public DataContainer() {
        conf = new Configuration().configure("/hibernate.cfg.xml");
        sessionFactory = conf.buildSessionFactory();

        //Session session = sessionFactory.openSession();
        //session.beginTransaction();
        filmy = FXCollections.observableArrayList();
        sale = FXCollections.observableArrayList();
        seanse = FXCollections.observableArrayList();
        loadFilmFromDatabase();
        loadSalaFromDatabase();
        loadSeansFromDatabase();
        //mergeData();
    }
    public void loadFilmFromDatabase() {
        try (Session ses = sessionFactory.openSession()) {
            ses.beginTransaction();
            Query<Film> query = ses.createQuery("from Film", Film.class);
            filmy.addAll(query.list());
            ses.getTransaction().commit();
        } catch (HibernateException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
    }
    private void loadSalaFromDatabase() {
        try (Session ses = sessionFactory.openSession()) {
            ses.beginTransaction();
            Query<Sale> query = ses.createQuery("from Sale", Sale.class);
            sale.addAll(query.list());
            ses.getTransaction().commit();
        } catch (HibernateException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
    }
    private void loadSeansFromDatabase() {
        Seans seansik = new Seans(null, null, null, null);
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
        } catch (HibernateException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
    }

    /*private void mergeData(){
        for (Film f: filmy) {
            for (Sale s: sale) {
                f.getSalki().add(s);
            }
        }
        try (Session ses = sessionFactory.openSession()) {
            ses.beginTransaction();
            Query quer = ses.createQuery("select * from FplusS ");
            seanse.addAll(quer.list());
            ses.getTransaction().commit();

        } catch (HibernateException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }

    }*/
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }


}
