package com.codecool.dungeoncrawl.ui;

import com.codecool.dungeoncrawl.logic.actors.Player;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class UI extends GridPane {
    public static VBox imageBox = new VBox(10);
    public static Label healthLabel = new Label();
    public static Label strengthLabel = new Label();
    public static Label heroName = new Label("Player Name");
    public static Label pointsLabel = new Label();

    public void setUpUI(UI ui){
        Label heroNameLabel = new Label("Hero name: ");
        Label inventoryLabel = new Label("Inventory");
        Label healthName = new Label("Health --->");
        Label attackLabel = new Label("Attack --->");
        Label pointsName = new Label("Points: ");

        heroNameLabel.setFont(new Font(12));
        String styleParameters = "-fx-text-fill: white; -fx-font-size: 16px; -fx-font-family: 'Viner Hand ITC'";
        heroNameLabel.setStyle(styleParameters);
        pointsName.setStyle(styleParameters);
        pointsLabel.setStyle(styleParameters);
        heroName.setStyle(styleParameters);
        healthLabel.setStyle(styleParameters);
        strengthLabel.setStyle(styleParameters);
        inventoryLabel.setStyle(styleParameters);
        healthName.setStyle(styleParameters);
        attackLabel.setStyle(styleParameters);

        Tooltip changeNameTooltip = new Tooltip("Click to change name");

        ui.setPrefWidth(250);
        ui.setPadding(new Insets(10));
        ui.setHgap(10);
        ui.setVgap(5);
        ui.setStyle("-fx-background-color: #472d3c;");

        ui.add(heroNameLabel,0,1);

        changeNameTooltip.setShowDelay(Duration.ZERO);
        heroName.setTooltip(changeNameTooltip);
        ui.add(heroName, 1,1);

        ui.add(imageBox, 0, 12);

        ui.add(healthName, 0,5);
        ui.add(healthLabel, 1, 5);

        ui.add(attackLabel,0,6);
        ui.add(strengthLabel,1,6);

        ui.add(pointsName, 0,7);
        ui.add(pointsLabel, 1,7);

        ui.add(inventoryLabel, 0, 10);

    }
}
