package jsh.boggle.controller;

import java.net.URL;
import javafx.fxml.FXML;
import java.util.TreeSet;
import java.util.Optional;
import javafx.scene.Group;
import java.util.Observer;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import java.util.Observable;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import jsh.boggle.model.Model;
import javafx.stage.StageStyle;
import java.util.ResourceBundle;
import javafx.scene.paint.Color;
import javafx.fxml.Initializable;
import javafx.scene.text.FontWeight;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert.AlertType;

/**
 * @author Joël Hoekstra
 */
public class Controller implements Initializable, Observer {

    Model model;
    Cell[][] board;

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

    ObservableList<String> items = FXCollections.observableArrayList();

    public Controller() {
        model = new Model();
    }

    public void addObservable(Observable obs) {
//        obs.addObserver(this);
//        if (obs instanceof Model) {
//            this.model = (Model) obs;
//        }
//        render();
//        model = new Model();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        render();
    }

    @FXML
    public void handleMouseClick(MouseEvent e) {
        System.out.println("clicked on " + wordListView.getSelectionModel().getSelectedItem());
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

    @FXML
    public void showStats() {
        System.out.println("Showing stats in a new stage..?"); // @todo how to create a new stage etc..
    }

    @FXML
    public void runBoggleSearch() {
        System.out.println("Lala");
        addWordsToItemsList(model.getAllWordsInBoard());
        leftStatus.setText("Running the boggle search");
    }

    @FXML
    public void searchWordInFoundList() {
        TextInputDialog dialog = new TextInputDialog("...");
        dialog.setTitle("Search word in found list");
        dialog.setHeaderText("Search for a word in the found list");
        dialog.setContentText("The word:");

        leftStatus.setText("Searching for a word");
        Optional<String> result = dialog.showAndWait();
        // @todo use regex to only find [A-Za-z]
        result.ifPresent(name -> {
            System.out.println(name);
            leftStatus.setText("You have looked for '" + name + "'");
        });
    }

    @FXML
    public void doFullScreen() {
        System.out.println("Going fullscreen on your boggled ass");
    }

    public void render() {
        model.reset();
        mainView.getChildren().add(generateBoard());
        addWordsToItemsList(model.getAllWordsInBoard());
//        highLightRender();
    }

    private Group generateBoard() {
        Group group = new Group();
        char[][] tempBoard = model.getBoard();
        board = new Cell[tempBoard.length][tempBoard.length];
        Cell cell;
        Color color;
        for (int x = 0; x < tempBoard.length; x++) {
            for (int y = 0; y < tempBoard.length; y++) {
                if ((x + y) % 2 == 0) {
                    color = Color.GOLDENROD;
                } else {
                    color = Color.ALICEBLUE;
                }
                String textValue = Character.toString(tempBoard[y][x]);
                cell = new Cell(x * 100, y * 100, 100, textValue, color);
                group.getChildren().add(cell);
                board[x][y] = cell;
            }
        }
        wordListView.setItems(items);
        return group;
    }

    public void addWordsToItemsList(TreeSet<String> words) {
        items.clear();
        for (String word : words) {
            items.add(word);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        render();
    }

    private class Cell extends StackPane {

        boolean isHighLighted = false;

        public Cell(double x, double y, double size, String textValue, Color color) {

            Rectangle cell = new Rectangle(x, y, size, size);
            cell.setFill(color);
            cell.setStroke(Color.BLACK);

            Label text  = new Label(textValue);
            text.setFont(Font.font("Verdana", FontWeight.BOLD, 70));
            text.setAlignment(Pos.CENTER);
            text.setTextFill(Color.PURPLE);
            text.setUnderline(isHighLighted);
            if (isHighLighted) {
                // do nothing
            }

            setTranslateX(x);
            setTranslateY(y);
            getChildren().addAll(cell, text);
        }
    }
}
