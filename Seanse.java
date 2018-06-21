package sample;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class Seanse implements HierarchicalController<MainController> {
    private boolean wczytano=false;
    public TextField tnazwa;
    public TextField tnr_sali;
    public TextField tkiedy;
    public TableView<Seans> tabelka;
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
    public void initialize() {

        if (wczytano == false) {

            wczytano = true;
        }
        for (TableColumn<Seans, ?> studentTableColumn : tabelka.getColumns()) {

            if ("tnazwa".equals(studentTableColumn.getId())) {

                studentTableColumn.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
            } else if ("tnr_sali".equals(studentTableColumn.getId())) {
                studentTableColumn.setCellValueFactory(new PropertyValueFactory<>("sala"));
            } else if ("tkiedy".equals(studentTableColumn.getId())) {
                studentTableColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
            }
        }

    }

}
