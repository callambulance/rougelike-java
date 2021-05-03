package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;

import java.util.Random;

public class Bat extends Actor{
    private int maxHealth = 6;
    private int health = 6;
    private int attack = 1;
    private int points = 2;

    public Bat(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "bat";
    }

    @Override
    public void move(int dx, int dy) {
        if (health > 0) {
            super.move(dx, dy);
        }
    }

    @Override
    public int getMaxHealth() { return maxHealth; }

    @Override
    public int getHealth() { return health; }

    @Override
    public void setHealth(int health) { this.health = health; }

    @Override
    public int getAttack() { return attack; }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public void setPoints(int points) {
        this.points = points;
    }
}
