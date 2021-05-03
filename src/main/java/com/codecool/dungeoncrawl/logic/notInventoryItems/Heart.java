package com.codecool.dungeoncrawl.logic.notInventoryItems;

import com.codecool.dungeoncrawl.logic.Cell;

public class Heart extends NotInventoryItem {

    public Heart(Cell cell) {
        super(cell);
        setUpgrade(2);
        setPickUpMessage("I'm healing!");
    }

}
