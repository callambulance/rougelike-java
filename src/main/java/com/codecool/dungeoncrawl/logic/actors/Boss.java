package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Boss extends Actor{

    public Boss(Cell cell) {
        super(cell);
        setMaxHealth(30);
        setHealth(30);
        setAttack(4);
        setPoints(20);
    }

    @Override
    public String getTileName() {
        return "boss";
    }
}
