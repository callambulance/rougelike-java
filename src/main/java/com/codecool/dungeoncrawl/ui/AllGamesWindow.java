package com.codecool.dungeoncrawl.ui;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.model.GameState;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.List;

public class AllGamesWindow {

        public static void showAllSavedGames(List<GameState> allGames){
            BorderPane startPane = new BorderPane();
            Stage allGamesStage = new Stage();
            allGamesStage.setTitle("Dungeon Crawl: Saved Games");

            Scene scene = new Scene(startPane, 600, 600);

            Label listOfGames = new Label("Saved games");
            listOfGames.setStyle("-fx-background-color: #472d3c; -fx-text-fill: white; -fx-font-size: 32px; -fx-font-family: 'Viner Hand ITC'; -fx-pref-width: 600; -fx-alignment: center; -fx-padding: 50 0 20");

            startPane.setCenter(listOfGames(allGames, allGamesStage));
            startPane.setTop(listOfGames);

            allGamesStage.setScene(scene);
            allGamesStage.show();

        }


        public static Node listOfGames(List<GameState> allGames, Stage allGamesStage){

            VBox gamesList = new VBox();
            HBox listLabel = new HBox();
            ScrollPane listScroller = new ScrollPane(gamesList);
            listScroller.setFitToWidth(true);
            listScroller.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
            listScroller.setStyle("-fx-background-insets: 100; -fx-padding: 0; -fx-fit-to-height: true");

            String style = "-fx-text-fill: white; -fx-pref-width: 90; -fx-padding: 0 0 10 0; -fx-font-size: 14px";

            Label playerLabel = new Label("Player Name");
            playerLabel.setStyle(style);
            Label gameDateLabel = new Label("Date Played");
            gameDateLabel.setStyle(style);
            Label blank = new Label("");
            blank.setStyle("-fx-pref-width: 75");

            listLabel.getChildren().addAll(blank, playerLabel, gameDateLabel);
            listLabel.setStyle("-fx-spacing: 20");

            gamesList.getChildren().addAll(listLabel);


            for (int i = 0; i<allGames.size(); i++) {
                Button btn = new Button("Load Game");
                String name = allGames.get(i).getPlayer().getPlayerName();
                int score = allGames.get(i).getPlayer().getScore();
                btn.setOnAction(e -> {
                    Main m = new Main();
                    allGamesStage.close();
                    Window openedStage = Stage.getWindows().stream().filter(Window::isShowing).findFirst().orElse(null);
                    ((Stage) openedStage).close();
                    m.loadSavedGame(name);
                });

                Label playerName = new Label(name);
                playerName.setStyle("-fx-text-fill: white; -fx-pref-width: 90");
                Label savedDate = new Label(String.valueOf(allGames.get(i).getSavedAt()).substring(0,10));
                savedDate.setStyle("-fx-text-fill: white");
                Label savedScore = new Label(score + " Points");
                savedScore.setStyle("-fx-text-fill: white");

                HBox gameData = new HBox(btn, playerName, savedDate, savedScore);
                gameData.setStyle("-fx-spacing: 20");
                gameData.setAlignment(Pos.CENTER_LEFT);

                gamesList.getChildren().add(gameData);
                gamesList.setStyle("-fx-background-color: #472d3c; -fx-padding: 30 50; -fx-spacing: 10");

            }

            return listScroller;
        }
}
