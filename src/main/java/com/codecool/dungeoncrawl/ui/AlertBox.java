package com.codecool.dungeoncrawl.ui;

import com.codecool.dungeoncrawl.Main;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.stage.Window;

import java.util.ArrayList;
import java.util.List;

public class AlertBox {

    public static void display(String title, String message){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(350);
        window.setMinHeight(250);
        VBox layout = new VBox();
        layout.getChildren().clear();

        Text label = new Text(message);
        layout.getChildren().add(label);

        ArrayList<String> developers = new ArrayList<>(List.of("Mateusz", "Dawid", "Valeri"));

        if(title.equals("This is the end.") || title.equals("End credits")) {
            newGame(layout, window);
        } else if (title.equals("Welcome to the Game")) {
            TextField setName = new TextField("Write Your name here");
            setName.setAlignment(Pos.BASELINE_CENTER);
            setName.setMaxWidth(150);
            setName.setOnKeyPressed(ke -> {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    setHeroName(window, developers, setName);
                }
            });
            layout.getChildren().add(setName);
            if (setName.getText() != null) {
                Button closeButton = new Button("Close Message");
                closeButton.setOnAction(e -> setHeroName(window, developers, setName));
                layout.getChildren().addAll(closeButton);

            }
        } else {
            Button closeButton = new Button("Close Message");
            closeButton.setOnAction(e -> window.close());
            layout.getChildren().addAll(closeButton);
        }


        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(15));
        layout.setSpacing(20);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }

    private static void setHeroName(Stage window, ArrayList<String> developers, TextField setName) {

        UI.heroName.setText(setName.getText());
        if (setName.getText().equals("Batman")) {
            Main.batmanMode = true;
        } else if (developers.contains(setName.getText())) {
            Main.godMode = true;
        } else {
            Main.godMode = false;
            Main.batmanMode = false;
        }
        window.close();
    }


    public static void newGame(VBox layout, Stage window){
        Button restartGame = new Button("New Game");
        Button endGame = new Button("Close Game");
        restartGame.setOnAction(e -> {
            Stage stage = new Stage();
            window.hide();
            Window openedStage = Stage.getWindows().stream().filter(Window::isShowing).findFirst().orElse(null);
            assert openedStage != null;
            ((Stage) openedStage).close();
            Main main = new Main();
            try {
                main.start(stage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        endGame.setOnAction(e -> {
            Platform.exit();
            System.exit(0);
        });

        layout.getChildren().addAll(restartGame, endGame);
    }

}
