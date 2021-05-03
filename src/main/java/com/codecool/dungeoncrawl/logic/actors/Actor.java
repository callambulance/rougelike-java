package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

import java.util.ArrayList;
import java.util.List;

public abstract class Actor implements Drawable {
    protected Cell cell;

    private int maxHealth;
    private int health;
    private int attack;
    private int points;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    ArrayList<CellType> notWalkableObjects = new ArrayList<>(List.of(CellType.WALL,
            CellType.BUSH, CellType.BONES, CellType.LWALL, CellType.LDCWALL, CellType.DWALL,
            CellType.RDCWALL, CellType.RWALL, CellType.FENCE, CellType.ENTRANCE, CellType.BLUEDOOR,
            CellType.YELLOWDOOR, CellType.RUCWALL, CellType.UWALL, CellType.LUCWALL));


    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (!notWalkableObjects.contains(nextCell.getType()) && nextCell.getActor() == null) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    public int getHealth() { return health; }

    public int getMaxHealth() { return maxHealth; }

    public void setMaxHealth(int maxHealth) { this.maxHealth = maxHealth; }

    public void setHealth(int health) {
        if (health>maxHealth){
            this.health = maxHealth;
        }else if(health<=0){
            this.health = 0;
        } else {
            this.health = health;
        }
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public int getAttack() { return attack; }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getPoints() { return points; }

    public void setPoints(int points) { this.points = points; }
}
