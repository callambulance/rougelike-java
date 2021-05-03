package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Ghost extends Actor{

    public Ghost(Cell cell) {
        super(cell);
        setMaxHealth(12);
        setHealth(12);
        setAttack(1);
        setPoints(3);
    }

    @Override
    public String getTileName() {
        return "ghost";
    }

}
