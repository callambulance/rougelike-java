package com.codecool.dungeoncrawl.model;

public class InventoryModel {
    private int blue_key;
    private int key;
    private int potion;

    public InventoryModel(int blue_key, int key, int potion) {
        this.blue_key = blue_key;
        this.key = key;
        this.potion = potion;
    }

    public int getBlue_key() {
        return blue_key;
    }

    public void setBlue_key(int blue_key) {
        this.blue_key = blue_key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getPotion() {
        return potion;
    }

    public void setPotion(int potion) {
        this.potion = potion;
    }
}
