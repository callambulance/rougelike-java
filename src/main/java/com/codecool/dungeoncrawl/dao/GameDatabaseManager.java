package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.MapMaker;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.InventoryModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class GameDatabaseManager {
    private PlayerDao playerDao;
    private GameStateDao gameStateDao;
    private InventoryDao inventoryDao;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        gameStateDao = new GameStateDaoJdbc(dataSource);
        inventoryDao = new InventoryDaoJdbc(dataSource);
    }

    public void saveGame(Player player) {
        PlayerModel playerModel = new PlayerModel(player);
        InventoryModel inventory = saveInventory(player);
        GameState gameState = new GameState(MapMaker.listRoString(), new Date(System.currentTimeMillis()), playerModel, inventory);
        System.out.println(playerModel.getPlayerName());
        if (playerDao.CheckIfPlayerExists(playerModel.getPlayerName())) {

            playerDao.update(playerModel);
            inventoryDao.update(inventory);
            gameStateDao.update(gameState);
        } else {
            playerDao.add(playerModel);
            inventoryDao.add(inventory, playerModel.getPlayerName());
            gameStateDao.add(gameState);
        }
    }

    public InventoryModel saveInventory(Player player) {
        HashMap inventory = player.getInventory().returnInventory();
        int blue_key = 0;
        int yellow_key = 0;
        int potion = 0;

        if (inventory.containsKey(CellType.BLUEKEY)) {
             blue_key = (int) inventory.get(CellType.BLUEKEY);
        }
        if (inventory.containsKey(CellType.KEY)) {
             yellow_key = (int) inventory.get(CellType.KEY);
        }
        if (inventory.containsKey(CellType.POTION)) {
            potion = (int) inventory.get(CellType.POTION);
        }

        InventoryModel inventoryModel = new InventoryModel(blue_key, yellow_key, potion);
        return inventoryModel;
    }


    public GameState loadGame(String hero_name){

        return gameStateDao.get(hero_name);
    }

    public List<GameState> loadAllGames(){
        return gameStateDao.getAll();
    }


    public DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = " ";
        String user = " ";
        String password = " ";

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
}
