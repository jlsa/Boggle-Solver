package jsh.boggle.controller;

import java.net.URL;
import javafx.fxml.FXML;

import java.util.TreeSet;
import java.util.Optional;

import javafx.stage.Stage;
import jsh.boggle.view.View;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import jsh.boggle.model.Model;
import javafx.stage.StageStyle;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert.AlertType;

/**
 * @author Joël Hoekstra
 */
public class Controller implements Initializable {

    private Model model;
    private View view;

    @FXML
    VBox root;

    @FXML
    Label leftStatus;

    @FXML
    Label rightStatus;

    @FXML
    AnchorPane mainView;

    @FXML
    ListView wordListView;

    @FXML
    Label wordsFoundLabel;

    ObservableList<String> items = FXCollections.observableArrayList();

    public Controller() { }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void handleMouseClick(MouseEvent e) {
        view.deActivateAllDice();
        Object target = wordListView.getSelectionModel().getSelectedItem();
        if (target != null) {
            view.activateDice(target.toString());
        }
    }

    @FXML
    public void closeApplication() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Close Application");
        alert.setHeaderText("Closing Application");
        alert.setContentText("Are you sure you want to close the Application?");

        leftStatus.setText("Confirm application close!");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) root.getScene().getWindow();
            stage.close();
        } else {
            leftStatus.setText("...");
        }
    }

    @FXML
    public void aboutClicked() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("About Boggle Board");
        alert.setHeaderText("About Boggle Board");
        alert.setContentText("This app was created by Joël Hoekstra.\nClass F @ Hanze HBO-ICT");
        alert.showAndWait();
    }

    @FXML
    public void restartBoggle() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Restart Boggle?");
        alert.setHeaderText("You are going to restart Boggle");
        alert.setContentText("Are you sure you want to do this?");
        leftStatus.setText("Confirm the Boggle restart.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            leftStatus.setText("Boggle board is restarted");
            render();
        } else {
            leftStatus.setText("...");
        }
    }

    public void render() {
        model.reset();
        mainView.getChildren().add(view.getBoardGroup());


        addWordsToItemsList(model.getAllWordsInBoard());
        wordListView.setItems(items);
        wordsFoundLabel.setText("Words found: " + items.size());
    }

    public void addWordsToItemsList(TreeSet<String> words) {
        items.clear();
        for (String word : words) {
            items.add(word);
        }
    }
}
