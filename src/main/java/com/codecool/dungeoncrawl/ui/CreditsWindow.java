package com.codecool.dungeoncrawl.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreditsWindow {

    private static final VBox vbox1 = new VBox();
    private static final VBox vbox2 = new VBox();
    private static final VBox vbox3 = new VBox();
    private static final VBox vbox4 = new VBox();
    private static final VBox vbox5 = new VBox();

    public static VBox[] vBoxes = new VBox[] {
         vbox1,vbox2,vbox3,vbox4,vbox5
    };

    public static VBox createBox(int pageIndex) {
        VBox pageBox = new VBox();

        pageBox.setStyle("-fx-font-size: 30; -fx-alignment: CENTER");

        for (int i = pageIndex; i < pageIndex+1; i++) {
            pageBox.getChildren().add(vBoxes[i]);
        }

        return pageBox;
    }

    public static void showCredits() {

        Stage creditsStage = new Stage();
        creditsStage.setTitle("Dungeon Crawl: Credits");
        creditsStage.setMinWidth(800);
        creditsStage.setMinHeight(800);

        //Page 1
        String whiteFont = "-fx-text-fill: white; -fx-alignment: center; -fx-min-width: 400;";

        Label v1Title = new Label("Java Adventure Team");
        v1Title.setPrefWidth(Double.MAX_VALUE);
        v1Title.setStyle("-fx-text-fill: white; -fx-font-size: 60px; -fx-padding: 30 0 80; -fx-alignment: center;");
        Label dName = new Label("Dawid Budziński");
        dName.setStyle(whiteFont);
        Label vName = new Label("Valeriia Kosenko");
        vName.setStyle(whiteFont);
        Label mName = new Label("Mateusz Rosiek");
        mName.setStyle(whiteFont);
        ImageView imageOne = new ImageView(new Image("/DC_Player.png"));
        ImageView imageTwo = new ImageView(new Image("/DC_Batman.png"));
        ImageView imageThree = new ImageView(new Image("/DC_GodMode.png"));

        HBox theTeam = new HBox();
        theTeam.setSpacing(50);
        theTeam.setStyle("-fx-padding: 0 0 200;");
        theTeam.setAlignment(Pos.BASELINE_CENTER);

        VBox dawidBox = new VBox();
        dawidBox.setSpacing(40);
        dawidBox.setAlignment(Pos.BASELINE_CENTER);
        VBox valeriiaBox = new VBox();
        valeriiaBox.setSpacing(40);
        valeriiaBox.setAlignment(Pos.BASELINE_CENTER);
        VBox mateuszBox = new VBox();
        mateuszBox.setSpacing(40);
        mateuszBox.setAlignment(Pos.BASELINE_CENTER);

        dawidBox.getChildren().addAll(dName,imageOne);
        valeriiaBox.getChildren().addAll(vName,imageTwo);
        mateuszBox.getChildren().addAll(mName,imageThree);

        theTeam.getChildren().add(dawidBox);
        theTeam.getChildren().add(valeriiaBox);
        theTeam.getChildren().add(mateuszBox);

        //Page 2

        Label v2Title = new Label("The Game");
        v2Title.setPrefWidth(Double.MAX_VALUE);
        v2Title.setStyle("-fx-text-fill: white; -fx-font-size: 60px; -fx-padding: 30 0 80; -fx-alignment: center;");

        Label about = new Label("A dungeon crawl is a type of scenario in fantasy role-playing games\n" +
                " in which heroes navigate a labyrinth environment (a \"dungeon\"),\n" +
                " battling various monsters, avoiding traps, solving puzzles,\n" +
                " and looting any treasure they may find.");
        about.setStyle(whiteFont);

        ImageView aboutMap = new ImageView(new Image("/DC_map.jpg"));

        HBox aboutGame = new HBox();
        aboutGame.getChildren().add(about);
        aboutGame.getChildren().add(aboutMap);
        aboutGame.setSpacing(100);
        aboutGame.setAlignment(Pos.CENTER);
        aboutGame.setPadding(new Insets(10, 10, 200, 10));

        //Page 3

        Label v3Title = new Label("Tools");
        v3Title.setPrefWidth(Double.MAX_VALUE);
        v3Title.setStyle("-fx-text-fill: white; -fx-font-size: 60px; -fx-padding: 30 0 80; -fx-alignment: center;");

        HBox toolsBox = new HBox();
        ImageView github = new ImageView(new Image("/github_logo.png"));
        github.setFitHeight(300);
        github.setFitWidth(300);
        ImageView trello = new ImageView(new Image("/trello_logo.png"));
        trello.setFitHeight(300);
        trello.setFitWidth(300);
        ImageView java = new ImageView(new Image("/java_logo.png"));
        java.setFitHeight(400);
        java.setFitWidth(400);

        toolsBox.getChildren().addAll(github, java, trello);
        toolsBox.setAlignment(Pos.CENTER);
        toolsBox.setSpacing(50);

        Label summary = new Label("193 Commits, 44 Pull Requests");
        summary.setPrefWidth(Double.MAX_VALUE);
        summary.setStyle("-fx-text-fill: white; -fx-font-size: 40px; -fx-padding: 50 0 80; -fx-alignment: center;");

        //Page 4

        Label v4Title = new Label("Game Tasks Done");
        v4Title.setPrefWidth(Double.MAX_VALUE);
        v4Title.setStyle("-fx-text-fill: white; -fx-font-size: 60px; -fx-padding: 30 0 80; -fx-alignment: center;");

        ImageView gameGif = new ImageView(new Image("/DC_DEMO.gif"));
        gameGif.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 15, 0, 0, 0); -fx-padding: 10;");

        Label tasks = new Label("- Extra Levels\n- Moving Creatures\n-Special Modes\n-Camera move\n-Game Music\n" +
                "- Player Name\n- Save/Load Game\n - Tests");
        tasks.setStyle("-fx-text-fill: white");
        HBox gifBox = new HBox();
        gifBox.getChildren().add(gameGif);
        gifBox.getChildren().add(tasks);
        gifBox.setAlignment(Pos.CENTER);
        gifBox.setSpacing(40);
        gifBox.setPadding(new Insets(20, 10, 200, 10));

        //Page 5

        Label v5Title = new Label("We shall show You The Game now!");
        v5Title.setPrefWidth(Double.MAX_VALUE);
        v5Title.setStyle("-fx-text-fill: white; -fx-font-size: 80px; -fx-padding: 30 0 80; -fx-alignment: center;");
        Button game = new Button("Let's Play");
        HBox gameButton = new HBox();
        gameButton.getChildren().add(game);
        gameButton.setAlignment(Pos.CENTER);

        game.setOnAction(e -> {
            creditsStage.close();
        });

        //Pagination Pages - All
        vbox1.getChildren().addAll(v1Title,theTeam);
        vbox2.getChildren().addAll(v2Title,aboutGame);
        vbox3.getChildren().addAll(v3Title,toolsBox, summary);
        vbox4.getChildren().addAll(v4Title,gifBox);
        vbox5.getChildren().addAll(v5Title, gameButton);



        String backgroundStyle = "-fx-background-color: #472d3c; -fx-font-family: 'Viner Hand ITC'; -fx-alignment: top;";

        BorderPane creditsGridPane = new BorderPane();

        Label creditsTitle = new Label("Dungeon Crawl: Quest for Graal - Codecool OOP, May 2020");
        creditsTitle.setPrefWidth(Double.MAX_VALUE);
        creditsTitle.setStyle("-fx-background-color: #472d3c; -fx-text-fill: white; -fx-font-size: 44px; -fx-font-family: 'Viner Hand ITC'; -fx-alignment: center; -fx-padding: 20 0");

        Pagination creditsPages = new Pagination(5);
        creditsPages.setStyle(backgroundStyle);
        creditsPages.setPageFactory((Integer pageIndex) -> {
            if (pageIndex > 6) {
                return null;
            } return createBox(pageIndex);
        });

        creditsGridPane.setTop(creditsTitle);
        creditsGridPane.setCenter(creditsPages);

        Scene creditsScene = new Scene(creditsGridPane);
        creditsStage.setScene(creditsScene);
        creditsStage.setFullScreen(true);
        creditsStage.setFullScreenExitHint("Press ESC to Exit");
        creditsStage.show();
    }
}
