package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.actors.Player;

import java.util.Objects;

public class PlayerModel extends BaseModel {
    private String playerName;
    private int hp;
    private int max_hp;
    private int attack;
    private int x;
    private int y;
    private int score;

    public PlayerModel(String playerName, int x, int y, int hp, int max_hp, int attack, int score) {
        this.playerName = playerName;
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.max_hp = max_hp;
        this.attack = attack;
        this.score = score;
    }

    public PlayerModel(Player player) {
        this.playerName = player.getName();
        this.x = player.getX();
        this.y = player.getY();
        this.hp = player.getHealth();
        this.max_hp = player.getMaxHealth();
        this.attack = player.getAttack();
        this.score = player.getPoints();

    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getMax_hp() { return max_hp; }

    public void setMax_hp(int max_hp) { this.max_hp = max_hp; }

    public int getAttack() { return attack; }

    public void setAttack(int attack) { this.attack = attack; }

    public int getScore() { return score; }

    public void setScore(int score) { this.score = score; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerModel that = (PlayerModel) o;
        return hp == that.hp && max_hp == that.max_hp && attack == that.attack && x == that.x && y == that.y
                && score == that.score && playerName.equals(that.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName, hp, max_hp, attack, x, y, score);
    }
}
