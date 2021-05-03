package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Inventory;
import com.codecool.dungeoncrawl.music.MusicPlayer;
import com.codecool.dungeoncrawl.ui.AlertBox;



public class Player extends Actor {
    private Inventory inventory;
    private String name;
//    private int maxHealth;
//    private int health;
//    private int attack;
//    private int points;

    public Player(Cell cell, Inventory inventory) {
        super(cell);
        setName("player");
        setMaxHealth(10);
        setHealth(10);
        setAttack(5);
        setPoints(0);
        this.inventory = inventory;
    }

    public Player(Cell cell) {
        super(cell);
    }


    public String getTileName() {
        if (Main.batmanMode) {
            return "batman";
        } else if (Main.godMode) {
            return "godmode";
        }
        return "player";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }


    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getActor() != null) {
            Actor enemy = nextCell.getActor();
            MusicPlayer.playAttackSound();
            setHealth(getHealth() - enemy.getAttack());
            if(getHealth() == 0){
                dying();
            }
            enemy.setHealth(enemy.getHealth() - getAttack());
            if (enemy.getHealth() <= 0) {
                setPoints(getPoints() + enemy.getPoints());
                nextCell.setActor(null);
//                nextCell.setType(CellType.FLOOR);
            }
        } else if (nextCell.getType().equals(CellType.BLUEDOOR)) {
            if (inventory.lookForItem(CellType.BLUEKEY)) {
                nextCell.setType(CellType.BLUEDOOROPENED);
                MusicPlayer.playUnlockSound();
                inventory.deleteItem(CellType.BLUEKEY);
                inventory.refreshInventory();
            }
        } else if (nextCell.getType().equals(CellType.YELLOWDOOR)) {
            if (inventory.lookForItem(CellType.KEY)) {
                nextCell.setType(CellType.YELLOWDOOROPENED);
                MusicPlayer.playUnlockSound();
                inventory.deleteItem(CellType.KEY);
                inventory.refreshInventory();
                }
        }
        super.move(dx, dy);
    }

    public void godModeMove(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }

    private void dying(){
        AlertBox.display("This is the end.", "You are dead!\nBut you can try one more time.");
    }



}



