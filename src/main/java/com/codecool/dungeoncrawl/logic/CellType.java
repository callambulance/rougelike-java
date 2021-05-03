package com.codecool.dungeoncrawl.logic;

import javafx.animation.PauseTransition;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    KEY("key"),
    HEART("heart"),
    STAIRS("stairs"),
    YELLOWDOOR("yellow_door"),
    YELLOWDOOROPENED("yellow_door_opened"),
    LWALL("left_wall"),
    LDCWALL("left_down_corner_wall"),
    DWALL("down_wall"),
    RDCWALL("right_down_corner_wall"),
    RWALL("right_wall"),
    RUCWALL("right_up_corner_wall"),
    UWALL("up_wall"),
    LUCWALL("left_up_corner_wall"),
    GHOST("ghost"),
    FENCE("fence"),
    BLUEKEY("blue_key"),
    BLUEDOOR("blue_door"),
    BLUEDOOROPENED("blue_door_opened"),
    BUSH("bush"),
    MONSTER("monster"),
    GRAAL("graal"),
    SWORD("sword"),
    AXE("axe"),
    HINT("hint"),
    HINT2("hint2"),
    ENTRANCE("entrance"),
    HELMET("helmet"),
    ARMOR("armor"),
    BONES("bones"),
    BOSS("boss"),
    POTION("potion"),
    BAT("bat"),
    BATMAN("batman"),
    CROWN("crown");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }

}