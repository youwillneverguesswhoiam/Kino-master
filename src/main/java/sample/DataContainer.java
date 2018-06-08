package sample;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by pwilkin on 30-Nov-17.
 */
public class DataContainer {

    protected ObservableList<Film> filmy;
    private final Configuration conf;
    private final SessionFactory sessionFactory;

    public ObservableList<Film> getFilmy() {
        return filmy;
    }

    public void setFilmy(List<Film> filmy) {
        this.filmy = FXCollections.observableArrayList(filmy);
    }

    public DataContainer() {
        conf = new Configuration().configure("/hibernate.cfg.xml");
        sessionFactory = conf.buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        filmy = FXCollections.observableArrayList();
    }
}
