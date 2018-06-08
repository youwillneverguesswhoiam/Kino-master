package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;

public class Sala implements HierarchicalController<MainController> {

    public TextField tnazwa;
    public TextField tnr_sali;
    public TextField tkiedy;
    public TableView<Film> tabelka;
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
        tabelka.setItems(parentController.getDataContainer().getFilmy());
    }


    public void dodaj(ActionEvent actionEvent) {
        Film st = new Film();
        st.setNazwa(tnazwa.getText());
        st.setSala(tnr_sali.getText());
        System.out.println ("sala"+tnr_sali.getText());
        //st.setLimit(typ.getText().isEmpty() ? null : Double.parseDouble(ocena.getText()));
        st.setKiedy(tkiedy.getText());
        tabelka.getItems().add(st);
    }
    public void initialize() {
        for (TableColumn<Film, ?> studentTableColumn : tabelka.getColumns()) {
            //System.out.println ("kolumna: "+studentTableColumn.getId());
            if ("tnazwa".equals(studentTableColumn.getId())) {

                TableColumn<Film, String> numerCol = (TableColumn<Film, String>) studentTableColumn;
                numerCol.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
                numerCol.setCellFactory(TextFieldTableCell.forTableColumn());
                numerCol.setOnEditCommit((val) -> {
                    val.getTableView().getItems().get(val.getTablePosition().getRow()).setNazwa(val.getNewValue());
                });
            } else if ("tnr_sali".equals(studentTableColumn.getId())) {
                System.out.println ("w salach "+studentTableColumn.getId());
                studentTableColumn.setCellValueFactory(new PropertyValueFactory<>("sala"));
                //System.out.println ("w salach2 "+new PropertyValueFactory<>("nr_sali").getProperty());
                ((TableColumn<Film, String>) studentTableColumn).setCellFactory(TextFieldTableCell.forTableColumn());
            } else if ("tkiedy".equals(studentTableColumn.getId())) {
                System.out.println ("w kiedach "+studentTableColumn.getId());
                TableColumn<Film, String> typCol = (TableColumn<Film, String>) studentTableColumn;
                typCol.setCellValueFactory(new PropertyValueFactory<>("kiedy"));
                typCol.setCellFactory(TextFieldTableCell.forTableColumn());
                typCol.setOnEditCommit((val) -> {
                    val.getTableView().getItems().get(val.getTablePosition().getRow()).setKiedy(val.getNewValue().toString());
                });
            }
        }

    }

   /* public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    public int getMiejsca() {
        return miejsca;
    }

    public void setMiejsca(int miejsca) {
        this.miejsca = miejsca;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }*/
}
