package com.codecool.dungeoncrawl.ui;

import com.codecool.dungeoncrawl.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class BottomPanel extends HBox {
    private UiButton howToPlayButton = new UiButton("How to Play");
    private UiButton newGameButton = new UiButton("New Game");
    public UiButton saveGameButton = new UiButton("Save Game");
    private UiButton exportGame = new UiButton("Export Game");
    private Label gameMessage = new Label(" ");

    public Label getGameMessage() {
        return gameMessage;
    }

    public void setBottomPanel(BottomPanel bottomPanel){
        howToPlayButton.setUpHowToPlayButton(howToPlayButton);
        newGameButton.setUpNewGameButton(newGameButton);
        saveGameButton.setSaveGameButton(saveGameButton);
        exportGame.setExportGame(exportGame);

        saveGameButton.setDisable(false);


        bottomPanel.getChildren().addAll(newGameButton, howToPlayButton, saveGameButton, exportGame, gameMessage);
        bottomPanel.setPadding(new Insets(10));
        bottomPanel.setSpacing(10);
        bottomPanel.setAlignment(Pos.CENTER_LEFT);
        bottomPanel.setStyle("-fx-font-size: 12px; -fx-background-color: #472d3c; -fx-padding: 10");
    }
}
