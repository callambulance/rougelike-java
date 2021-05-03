package com.codecool.dungeoncrawl.ui;

import javafx.animation.PauseTransition;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class TextMsg extends Node {
    public String PickUpSome() { return "I'm taking this!"; }
    public String Nothing() { return "Nothing to pick up."; }
    public String UsePotion() { return "I'm feeling so good!"; }

    public void setPickUpTextBottom(String msg, Label gameMsg) {
        gameMsg.setText(msg);
        PauseTransition pause = new PauseTransition(Duration.seconds(4));
        pause.setOnFinished(ev -> gameMsg.setText(""));
        pause.play();

        gameMsg.setStyle("-fx-font-family: 'Viner Hand ITC'; -fx-font-size: 18px; -fx-text-fill: white; -fx-label-padding: 0 60");
    }




}
