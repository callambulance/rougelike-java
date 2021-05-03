package com.codecool.dungeoncrawl.logic.notInventoryItems;

import com.codecool.dungeoncrawl.logic.Cell;

public class ArmorHelmet extends NotInventoryItem {
    public ArmorHelmet(Cell cell) {
        super(cell);
        setUpgrade(10);
        setPickUpMessage("You've got a new armor (Your MaxHealth risen by 10)");
    }
}
