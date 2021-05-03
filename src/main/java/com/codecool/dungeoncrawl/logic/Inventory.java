package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.Tiles;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.InventoryModel;
import com.codecool.dungeoncrawl.music.MusicPlayer;
import com.codecool.dungeoncrawl.ui.UI;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import javafx.scene.text.Text;

import java.util.HashMap;

public class Inventory {
    private HashMap<CellType, Integer> inventory;

    Image potion = Tiles.getImage(23,23);
    Image key =  Tiles.getImage(16,23);
    Image blueKey = Tiles.getImage(17,23);

    HashMap <CellType, Image> images = new HashMap<CellType, Image>()
    {{put(CellType.POTION, potion); put(CellType.KEY, key); put(CellType.BLUEKEY, blueKey);}};


    public Inventory() {
        inventory = new HashMap<CellType, Integer>();
    }

    public Inventory(HashMap<CellType, Integer> savedInventory) {
        inventory = savedInventory;
    }

    public HashMap<CellType, Integer> returnInventory() {
        inventory.forEach((k, v) -> System.out.println("Key: " + k + " Value: " + v));
        return inventory;
    }

    public void setInventory(HashMap<CellType, Integer> inventory) {
        this.inventory = inventory;
    }

    public void addItem(CellType item) {

        if (inventory.containsKey(item)) {
            int quantity = inventory.get(item);
            inventory.replace(item, quantity + 1);
        } else {
            inventory.put(item, 1);
            inventory.forEach((k, v) -> System.out.println(k + ": " + v));
        }
    }

    public void deleteItem(CellType item) {
        int quantity = inventory.get(item);
        inventory.replace(item, quantity - 1);
    }

    public boolean lookForItem(CellType cellType) {
        if (inventory.containsKey(cellType)) {
            return true;
        }
        return false;
    }

    public void refreshInventory() {

        UI.imageBox.getChildren().clear();

        inventory.forEach((item, count)-> {
            images.forEach((k, v) -> {
                if(item.equals(k) && count != 0) {
                    Text inventoryCount = new Text(count + "x");
                    inventoryCount.setStyle("-fx-font-size: 20px; -fx-font-family: 'Viner Hand ITC'; -fx-fill: white; -fx-alignment: bottom");
                    HBox inventoryItem = new HBox(10, inventoryCount, new ImageView(v));
                    UI.imageBox.getChildren().add(inventoryItem);
                }
            });
        });
    }

    public void elixirUsing(GameMap map){
        deleteItem(CellType.POTION);
        MusicPlayer.playDrinkingSound();
        refreshInventory();
        int maxHealth = map.getPlayer().getMaxHealth();
        map.getPlayer().setHealth(maxHealth);
    }

    public Inventory makeInventory(GameState state){
        InventoryModel inventory = state.getInventory();

        HashMap <CellType, Integer> savedInventory = new HashMap<CellType, Integer>()
        {{put(CellType.POTION, inventory.getPotion()); put(CellType.KEY, inventory.getKey());
            put(CellType.BLUEKEY, inventory.getBlue_key());}};

        return new Inventory(savedInventory);
    }
}
