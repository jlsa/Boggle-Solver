package jsh.boggle.application;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import jsh.boggle.util.Config;
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

    private int width = 1024;
    private int height = 768;

    @Override
    public void start(Stage stage) throws Exception {
        view = new View();
        model = new Model(view);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/fxml/app.fxml"));
        Parent root = loader.load();
        Controller ctrl = loader.getController();
        ctrl.setView(view);
        ctrl.setModel(model);
        ctrl.render();
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);

        stage.setTitle(Config.APP_NAME);
        stage.setOnCloseRequest(e -> {
            ctrl.closeApplication();
            e.consume(); // if not closing make sure it is consumed.
        });
        stage.setResizable(false);
        stage.show();
    }
}
