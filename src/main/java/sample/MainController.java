package sample;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainController implements HierarchicalController<MainController> {

    public Pane srodek;

    protected DataContainer dataContainer;

    public DataContainer getDataContainer() {
        return dataContainer;
    }

    public MainController() {
        dataContainer = new DataContainer();
    }

    public void daneOsobowe(ActionEvent actionEvent) {
        loadIntoPane("/fxml/film.fxml");
    }

    private void loadIntoPane(String fxml) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        try {
            final BorderPane load = loader.load();
            srodek.getChildren().clear();
            srodek.getChildren().add(load);
            HierarchicalController<MainController> daneController = loader.getController();
            daneController.setParentController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        @Override
    public MainController getParentController() {
        return this;
    }

    @Override
    public void setParentController(MainController parent) {

    }
    public void tabelafilmowa(ActionEvent actionEvent) {
        loadIntoPane("/fxml/film.fxml");
    }
    public void sala(ActionEvent actionEvent) {
        loadIntoPane("/fxml/sala.fxml");
    }

}
