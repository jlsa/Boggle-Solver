package jsh.boggle;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import jsh.boggle.view.View;
import javafx.fxml.FXMLLoader;
import jsh.boggle.model.Model;
import javafx.application.Application;
import jsh.boggle.controller.Controller;

/**
 * @author Joël Hoekstra
 */
public class App extends Application {
    private Model model;
    private View view;

    @Override
    public void start(Stage stage) throws Exception {
        view = new View();
        model = new Model(view);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/fxml/app.fxml"));
        Parent root = loader.load();
        Controller ctrl = loader.getController();
        ctrl.setView(view);
        ctrl.setModel(model);
        ctrl.render();
        Scene scene = new Scene(root, 1024, 768);
        stage.setScene(scene);

        stage.setTitle("Boggle Board");
        stage.setOnCloseRequest(e -> {
            ctrl.closeApplication();
            e.consume(); // if not closing make sure it is consumed.
        });
        stage.show();
    }
}
