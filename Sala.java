package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Region;
import javafx.util.StringConverter;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class Sala implements HierarchicalController<MainController> {

    public TextField tnazwa;
    public TextField tnr_sali;
    public TextField tkiedy;
    public TableView<Sale> tabelka;
    private MainController parentController;
    /*private MainController parentController;
    public TextField tnazwa;
    public TextField tnr_sali;
    public TextField tkiedy;
    public TableView<Sala> tabelka;
*/

    @Override
    public MainController getParentController() {
        return parentController;
    }

    @Override
    public void setParentController(MainController parentController) {
        this.parentController = parentController;
        tabelka.setEditable(true);
        //tabelka.getItems().addAll(parentController.getDataContainer().getStudents());
        tabelka.setItems(parentController.getDataContainer().getSale());
    }

    public void dodaj(ActionEvent actionEvent) {
        Sale st = new Sale();
        st.setNazwa(tnazwa.getText());
        st.setSala(tnr_sali.getText());
        //st.setLimit(typ.getText().isEmpty() ? null : Double.parseDouble(ocena.getText()));
        st.setKiedy(tkiedy.getText());
        //System.out.println ("hash:"+st.getFilmiki().size() );
        dodajDoBazy(st);
        tabelka.getItems().add(st);
    }
    private void dodajDoBazy(Sale st) {
        try (Session ses = parentController.getDataContainer().getSessionFactory().openSession()){//.getSessionFactory().openSession()) {
            ses.beginTransaction();
            ses.persist(st);
            ses.getTransaction().commit();
        } catch (HibernateException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
    }
    public void initialize() {
        for (TableColumn<Sale, ?> groupTableColumn : tabelka.getColumns()) {
            if ("tnazwa".equals(groupTableColumn.getId())) {
                TableColumn<Sale, String> nazwaColumn = (TableColumn<Sale, String>) groupTableColumn;
                nazwaColumn.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
                nazwaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                nazwaColumn.setOnEditCommit((val) -> {
                    Sale editedGroup = val.getTableView().getItems().get(val.getTablePosition().getRow());
                    editedGroup.setNazwa(val.getNewValue());
                    //updateDatabaseValue(editedGroup);
                });
            } else if ("tnr_sali".equals(groupTableColumn.getId())) {
                TableColumn<Sale, String> salColumn = (TableColumn<Sale, String>) groupTableColumn;
                salColumn.setCellValueFactory(new PropertyValueFactory<>("sala"));
                salColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                salColumn.setOnEditCommit((val) -> {
                    Sale editedGroup = val.getTableView().getItems().get(val.getTablePosition().getRow());
                    editedGroup.setSala(val.getNewValue());
                    //updateDatabaseValue(editedGroup);
                });
            } else if ("tkiedy".equals(groupTableColumn.getId())) {
                TableColumn<Sale, String> kiedyColumn = (TableColumn<Sale, String>) groupTableColumn;
                kiedyColumn.setCellValueFactory(new PropertyValueFactory<>("kiedy"));
                kiedyColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                kiedyColumn.setOnEditCommit((val) -> {
                    Sale editedGroup = val.getTableView().getItems().get(val.getTablePosition().getRow());
                    editedGroup.setKiedy(val.getNewValue());
                    //updateDatabaseValue(editedGroup);
                });
            }

        }
    }
}
