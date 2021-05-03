package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.*;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.music.MusicPlayer;
import com.codecool.dungeoncrawl.ui.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;


public class Main extends Application {
    public static boolean batmanMode;
    public static boolean godMode;

    UI ui;
    BottomPanel bottomPanel;
    Label gameMessage;
    TextMsg pickUpText = new TextMsg();
    Hint hint = new Hint();
    ArrayList<CellType> inventoryItems = new ArrayList<>(List.of(CellType.BLUEKEY,
            CellType.GRAAL, CellType.KEY, CellType.POTION));

    GameMap map = MapLoader.loadMap("/map.txt");

    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);

    private int heightOffset = 0;
    private int widthOffset = 0;

    GameDatabaseManager dbManager;

    GraphicsContext context = canvas.getGraphicsContext2D();

    Stage window;
    ArrayList<String> developers = new ArrayList<>(List.of("Mateusz", "Dawid", "Valeri"));
    public boolean musicPlaying = false;


    public static void main(String[] args) { launch(args);    }

    @Override
    public void start(Stage mainStage) {
        setupDbManager();

        mainStage.setTitle("Dungeon Crawl");

        StartUi startUi = new StartUi();
        startUi.SetUpMenu(startUi);

        BorderPane startPane = new BorderPane();
        startPane.setCenter(startUi);

        Scene scene = new Scene(startPane, 600, 600);

        startUi.load.setOnAction(e -> {
            List<GameState> allGames;
            allGames = dbManager.loadAllGames();
            AllGamesWindow.showAllSavedGames(allGames);
        });

        startUi.credits.setOnAction(e -> CreditsWindow.showCredits());

        mainStage.setScene(scene);
        mainStage.show();
    }

    public void startGame(Stage primaryStage, Boolean welcomeDisplayed) {
        setupDbManager();
        window = primaryStage;
        ui = new UI();
        ui.setUpUI(ui);
        bottomPanel = new BottomPanel();
        bottomPanel.setPrefHeight(60);

        batmanMode = UI.heroName.getText().equals("Batman");
        godMode = developers.contains(UI.heroName.getText());

        if (canvas.getWidth() > 800) {
            canvas.setWidth(800);
        }
        canvas.setHeight(640);

        UI.heroName.setOnMouseClicked(e -> nameChange());

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        bottomPanel.setBottomPanel(bottomPanel);
        borderPane.setBottom(bottomPanel);

        gameMessage = bottomPanel.getGameMessage();

        Scene scene = new Scene(borderPane);

        primaryStage.setScene(scene);
        scene.setOnKeyPressed(this::onKeyPressed);
        scene.setOnKeyReleased(this::onKeyReleased);

        primaryStage.setTitle("Dungeon Crawl: Quest for Graal");
        primaryStage.show();
        refresh(KeyCode.SPACE);
        map.getPlayer().getInventory().refreshInventory();
        UI.heroName.setText(map.getPlayer().getName());

        bottomPanel.saveGameButton.setOnAction(e -> {
            System.out.println("Game Saved");
            try {
                MapMaker.makeMap(map);
            } catch (IOException exc) {
                exc.printStackTrace();
            }
            Player player = map.getPlayer();
            dbManager.saveGame(player);
        });



        if (!welcomeDisplayed) {
            AlertBox.display(hint.getWelcomeTitle(), hint.getWelcomeHint());
            map.getPlayer().setName(UI.heroName.getText());
        }

        if (!musicPlaying) {
            MusicPlayer.playMusic();
            musicPlaying = true;
        }
    }

    private void setupDbManager() {
        dbManager = new GameDatabaseManager();
        try {
            dbManager.setup();
        } catch (SQLException ex) {
            System.out.println("Cannot connect to database.");
        }
    }

    private void exit() {
        try {
            stop();
        } catch (Exception e) {
            System.exit(1);
        }
        System.exit(0);
    }

    private void onKeyReleased(KeyEvent keyEvent) {
        KeyCombination exitCombinationMac = new KeyCodeCombination(KeyCode.W, KeyCombination.SHORTCUT_DOWN);
        KeyCombination exitCombinationWin = new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN);
        KeyCombination saveGame = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
        if (exitCombinationMac.match(keyEvent)
                || exitCombinationWin.match(keyEvent)
                || keyEvent.getCode() == KeyCode.ESCAPE) {
            exit();
        }else if(saveGame.match(keyEvent)){
            System.out.println("works");
            try {
                MapMaker.makeMap(map);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Player player = map.getPlayer();
            dbManager.saveGame(player);
        }
    }

    private void nameChange() {
        TextInputDialog dialog = new TextInputDialog(UI.heroName.getText());
        dialog.setTitle("Change Hero's name");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter Your name here:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            UI.heroName.setText(dialog.getResult());
            map.getPlayer().setName(dialog.getResult());
            godMode = false;
            batmanMode = false;
            if (developers.contains(UI.heroName.getText())) {
                godMode = true;
            } else if (UI.heroName.getText().equals("Batman")) {
                batmanMode = true;
                godMode = false;
            }
        }
    }

    private void onHintMove() {
        if (map.getCell(map.getPlayer().getX(), map.getPlayer().getY()).getType().equals(CellType.HINT)) {
            AlertBox.display(hint.getTitle1(), hint.getHint1());
        } else if (map.getCell(map.getPlayer().getX(), map.getPlayer().getY()).getType().equals(CellType.HINT2)) {
            AlertBox.display(hint.getTitle2(), hint.getHint2());
        }
    }

    private void checkForGraal() {
        if (map.getCell(map.getPlayer().getX(), map.getPlayer().getY()).getType().equals(CellType.GRAAL)) {
            MusicPlayer.stopMusic();
            MusicPlayer.playEndGameSound();
            AlertBox.display(hint.getEndGameTitle(), hint.getEndMessage());
        }
    }

    private void nextLevelMove() {
        if (map.getCell(map.getPlayer().getX(), map.getPlayer().getY()).getType().equals(CellType.CROWN)) {
            loadNextMap("/map3.txt");
        } else if (map.getCell(map.getPlayer().getX(), map.getPlayer().getY()).getType().equals(CellType.STAIRS)) {
            loadNextMap("/map2.txt");
        }

    }

    public static int randomInt(int low, int high) {
        Random random = new Random();
        return random.nextInt((high - low)+1) + low;
    }

    private void batRandomMove() {
        map.getBatList().forEach(bat -> {
            int a = randomInt(-1,1);
            int b = randomInt(-1,1);
            bat.move(a,b);
        });
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case W:
            case UP:
                makeMove(0, -1, KeyCode.UP);
                break;
            case S:
            case DOWN:
                makeMove(0, 1, KeyCode.DOWN);
                break;
            case A:
            case LEFT:
                makeMove(-1, 0, KeyCode.LEFT);
                break;
            case D:
            case RIGHT:
                makeMove(1, 0, KeyCode.RIGHT);
                break;
            case P:
                pickUpItem();
                break;
            case H:
                if (map.getPlayer().getInventory().lookForItem(CellType.POTION)) {
                    map.getPlayer().getInventory().elixirUsing(map);
                    pickUpText.setPickUpTextBottom(pickUpText.UsePotion(), gameMessage);
                }

                break;
            case N:
                nameChange();
                break;
            case M:
                MusicPlayer.stopMusic();
                break;
        }
    }

    private void makeMove(int x, int y, KeyCode up) {
        if (godMode) {
            map.getPlayer().godModeMove(x, y);
        } else {
            map.getPlayer().move(x, y);
        }
        if (!batmanMode) {
            batRandomMove();
        }
        onHintMove();
        nextLevelMove();
        refresh(up);
        checkForGraal();
    }

    private void refresh(KeyCode keyCode) {
        context.setFill(Color.BLACK);
        context.setStroke(Color.WHITESMOKE);

        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int playerXposition = map.getPlayer().getX();
        int playerYposition = map.getPlayer().getY();
        double playerWidthMin = (canvas.getWidth()/32) - 4 ;
        double playerWidthMax = map.getWidth()-4;
        double playerHeightMin = (canvas.getHeight()/32) -4;
        double playerHeightMax = map.getHeight()-4;


        if (map.getWidth() > 25) {
            if (KeyCode.RIGHT.equals(keyCode)) {
                if (playerXposition <= playerWidthMax && playerXposition > playerWidthMin && widthOffset >= 0) {
                    widthOffset++;
                }
            }
            if (KeyCode.DOWN.equals(keyCode)) {
                if (playerYposition <= playerHeightMax && playerYposition > playerHeightMin && heightOffset >= 0) {
                    heightOffset++;
                }
            }
            if (KeyCode.LEFT.equals(keyCode)) {
                if (playerXposition < playerWidthMax && playerXposition >= playerWidthMin && widthOffset > 0) {
                    widthOffset--;
                }
            }
            if (KeyCode.UP.equals(keyCode)) {
                if (playerYposition >= playerHeightMin && playerYposition < playerHeightMax && heightOffset > 0) {
                    heightOffset--;
                }
            }
        }

        for (int x = 0; x < map.getWidth()-widthOffset; x++) {
            for (int y = 0; y < map.getHeight()-heightOffset; y++) {

                Cell cell = map.getCell(x+widthOffset, y+heightOffset);

                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                    context.strokeText(String.format("%s/%s", cell.getActor().getHealth(), cell.getActor().getMaxHealth()),
                            (cell.getActor().getX() - widthOffset) * Tiles.TILE_WIDTH,
                            (cell.getActor().getY() - heightOffset) * Tiles.TILE_WIDTH);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        UI.healthLabel.setText("" + map.getPlayer().getHealth());
        UI.strengthLabel.setText("" + map.getPlayer().getAttack());
        UI.pointsLabel.setText("" + map.getPlayer().getPoints());
    }


    private void pickUpItem() {
            if (inventoryItems.contains(map.getPlayer().getCell().getType())) {
                CellType item = map.getPlayer().getCell().getType();
                if (item.equals(CellType.KEY) || item.equals(CellType.BLUEKEY)) {
                    MusicPlayer.playKeySound();
                } else if (item.equals(CellType.POTION)) {
                    MusicPlayer.playGlassSound();
                }
                pickingObject(item);
                pickUpText.setPickUpTextBottom(pickUpText.PickUpSome(), gameMessage);
            }else if(map.getPlayer().getCell().getType().equals(CellType.HEART)){
                int health = map.getPlayer().getHealth();
                MusicPlayer.playEatingSound();
                map.getPlayer().setHealth(health+map.getPlayer().getCell().getItem().getUpgrade());
                pickUpText.setPickUpTextBottom(map.getPlayer().getCell().getItem().getPickUpMessage(), gameMessage);
            }else if(map.getPlayer().getCell().getType().equals(CellType.SWORD)||
                    map.getPlayer().getCell().getType().equals(CellType.AXE)){
                MusicPlayer.playUpgradeSound();
                map.getPlayer().setAttack(map.getPlayer().getCell().getItem().getUpgrade());
                pickUpText.setPickUpTextBottom(map.getPlayer().getCell().getItem().getPickUpMessage(), gameMessage);
            } else if (map.getPlayer().getCell().getType().equals(CellType.HELMET) ||
                    map.getPlayer().getCell().getType().equals(CellType.ARMOR)) {
                MusicPlayer.playUpgradeSound();
                int maxHealth = map.getPlayer().getMaxHealth();
                map.getPlayer().setMaxHealth(maxHealth + map.getPlayer().getCell().getItem().getUpgrade());
                pickUpText.setPickUpTextBottom(map.getPlayer().getCell().getItem().getPickUpMessage(), gameMessage);
            } else pickUpText.setPickUpTextBottom(pickUpText.Nothing(), gameMessage);
            map.getPlayer().getCell().setType(CellType.FLOOR);

    }

    private void pickingObject(CellType item){
        map.getPlayer().getInventory().addItem(item);
        map.getPlayer().getInventory().refreshInventory();
        map.getPlayer().getCell().setType(CellType.FLOOR);
    }

    public void loadNextMap(String filename) {
            String savedName = UI.heroName.getText();
            int savedHealth = map.getPlayer().getHealth();
            int savedMaxHealth = map.getPlayer().getMaxHealth();
            int savedAttack = map.getPlayer().getAttack();
            int savedPoints = map.getPlayer().getPoints();
            Inventory savedInventory = map.getPlayer().getInventory();
            map = MapLoader.loadMap(filename);
            canvas = new Canvas(
                    map.getWidth() * Tiles.TILE_WIDTH,
                    map.getHeight() * Tiles.TILE_WIDTH);
            context = canvas.getGraphicsContext2D();
            try {
                startGame(window, true);
                map.getPlayer().setInventory(savedInventory);
                map.getPlayer().getInventory().refreshInventory();
                UI.heroName.setText(savedName);
                map.getPlayer().setMaxHealth(savedMaxHealth);
                map.getPlayer().setHealth(savedHealth);
                map.getPlayer().setAttack(savedAttack);
                map.getPlayer().setPoints(savedPoints);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

    public void loadSavedGame(String name){
        setupDbManager();
        GameState state = dbManager.loadGame(name);

        map = MapLoader.loadMap(state.getCurrentMap());

        map.getPlayer().setName(state.getPlayer().getPlayerName());
        map.getPlayer().setMaxHealth(state.getPlayer().getMax_hp());
        map.getPlayer().setHealth(state.getPlayer().getHp());
        map.getPlayer().setAttack(state.getPlayer().getAttack());
        map.getPlayer().setInventory(map.getPlayer().getInventory().makeInventory(state));
        map.getPlayer().setPoints(state.getPlayer().getScore());

        canvas = new Canvas(
                map.getWidth() * Tiles.TILE_WIDTH,
                map.getHeight() * Tiles.TILE_WIDTH);
        context = canvas.getGraphicsContext2D();
        try {
            Stage stage = new Stage();
            startGame(stage, true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}

