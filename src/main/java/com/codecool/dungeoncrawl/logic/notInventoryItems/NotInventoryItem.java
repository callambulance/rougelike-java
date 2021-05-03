package com.codecool.dungeoncrawl.logic.notInventoryItems;
import com.codecool.dungeoncrawl.logic.Cell;

public abstract class NotInventoryItem {
    protected Cell cell;
    int upgrade;
    String pickUpMessage;

    public NotInventoryItem(Cell cell) {
        this.cell = cell;
        this.cell.setItem(this);
    }

    public int getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(int upgrade) {
        this.upgrade = upgrade;
    }

    public String getPickUpMessage() {
        return pickUpMessage;
    }

    public void setPickUpMessage(String pickUpMessage) {
        this.pickUpMessage = pickUpMessage;
    }
}
