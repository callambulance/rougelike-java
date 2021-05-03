package com.codecool.dungeoncrawl.logic.notInventoryItems;

import com.codecool.dungeoncrawl.logic.Cell;

public class Sword extends NotInventoryItem{
    public Sword(Cell cell) {
        super(cell);
        setUpgrade(15);
        setPickUpMessage("You've got a Sword (Your Attack is now 15)");
    }
}
