package com.codecool.dungeoncrawl.ui;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.logic.JasonDataGame;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

public class UiButton extends Button {


    public UiButton(String name) {
        super(name);
    }

    public void setUpHowToPlayButton(UiButton button){
        Hint hint = new Hint();
        button.setFocusTraversable(false);
        button.setOnMouseClicked(e -> AlertBox.display(hint.getHowToPlayTitle(), hint.getGetHowToPlayHint()));
    }

    public void setUpNewGameButton(UiButton button){
        button.setFocusTraversable(false);
        button.setOnAction(e -> {
            Window stage = Stage.getWindows().stream().filter(Window::isShowing).findFirst().orElse(null);
            Main main = new Main();
            try {
                main.start((Stage) stage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

    }

    public void setSaveGameButton(UiButton button) {
        button.setFocusTraversable(false);
    }

    public void setExportGame(UiButton saveGame) {
        saveGame.setFocusTraversable(false);
        saveGame.setOnAction(e -> {
            JasonDataGame.jsonDataGameSaver();
            AlertBox.display("Saved Data", JasonDataGame.jsonDataGameSaver());
        });
    }
}
