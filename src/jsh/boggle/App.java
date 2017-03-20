package jsh.boggle;

import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Optional;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.application.Application;
import javafx.scene.control.ButtonType;

/**
 * @author Joël Hoekstra
 */
public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/fxml/app.fxml"));
//        Parent root = (Parent) loader.load();
        Parent root = loader.load();
//        Controller ctrl = loader.getController();
//        ctrl.addObservable(model);
//        ctrl.render();
        Scene scene = new Scene(root, 1024, 768);
        stage.setScene(scene);

        stage.setTitle("Boggle Board");
        stage.setOnCloseRequest(e -> {
            boolean allowedToClose = askToClose(stage);
            if (allowedToClose) {
                stage.close();
            } else {
                e.consume();
            }
        });
        stage.setTitle("Boggle");
//        stage.setScene(new Scene(root, 1024, 768));
        stage.show();
    }

    private boolean askToClose(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close Application");
        alert.setHeaderText("Closing Application");
        alert.setContentText("Are you sure you want to close the Application?");

        Optional<ButtonType> result = alert.showAndWait();

        return result.get() == ButtonType.OK;
    }
}
