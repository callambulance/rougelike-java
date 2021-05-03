package com.codecool.dungeoncrawl.logic.notInventoryItems;

import com.codecool.dungeoncrawl.logic.Cell;

public class Axe extends NotInventoryItem {

    public Axe(Cell cell) {
        super(cell);
        setUpgrade(10);
        setPickUpMessage("You've got an Axe (Your Attack is now 10)");

    }
}
