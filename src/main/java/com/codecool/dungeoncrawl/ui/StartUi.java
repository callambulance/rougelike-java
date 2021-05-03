package com.codecool.dungeoncrawl.ui;

import com.codecool.dungeoncrawl.Main;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

public class StartUi extends VBox {
    public Image logo = new Image("/DC.png");
    public Button start = new Button("Start new Game");
    public Button load = new Button("Load Game");
    public Button credits = new Button("Credits");
    public Label gameInfo = new Label("2021, DC Team - Codecool, May 2020");

    public void SetUpMenu(StartUi startUi) {
        gameInfo.setStyle("-fx-text-fill: white; -fx-padding: 50 0");

        ImageView logoPNG = new ImageView(logo);
        logoPNG.setFitWidth(570);


        startUi.setPrefWidth(250);
        startUi.setSpacing(10);
        startUi.setStyle("-fx-background-color: #472d3c; -fx-padding: 50 0");
        startUi.setAlignment(Pos.BASELINE_CENTER);

        startUi.getChildren().addAll(logoPNG,start, load, credits, gameInfo);

        start.setOnAction(e -> {
            System.out.println("Start button");
            Stage primaryStage = new Stage();
            Main main = new Main();
            Window openedStage = Stage.getWindows().stream().filter(Window::isShowing).findFirst().orElse(null);
            ((Stage) openedStage).close();
            main.startGame(primaryStage, false);
        });
    }

    public void main(String[] args) {
        Main.main(args);
    }

}
