package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Actor {

    public Skeleton(Cell cell) {
        super(cell);
        setMaxHealth(10);
        setHealth(10);
        setAttack(1);
        setPoints(5);
    }

    @Override
    public String getTileName() { return "skeleton"; }

}
