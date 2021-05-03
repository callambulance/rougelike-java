package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.dao.PlayerDao;
import com.codecool.dungeoncrawl.dao.PlayerDaoJdbc;
import com.codecool.dungeoncrawl.model.PlayerModel;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerDaoJdbcTest {

    private PlayerDao testPlayerDaoJdbc = new PlayerDaoJdbc(new GameDatabaseManager().connect());

    PlayerDaoJdbcTest() throws SQLException {
    }
    String testName = "player";


    @Test
    void givenPlayerDaoJdbc_whenGetWithId1_shouldReturnPlayerWithHp8() {
        PlayerModel expectedPlayer = new PlayerModel("player", 6, 15, 5, 10, 25, 5);
//        when
        PlayerModel gettingPlayer = testPlayerDaoJdbc.get(testName);
//        then
        assertEquals(expectedPlayer, gettingPlayer);
    }


    @Test
    void givePlayerDaoJdbc_whenGetWithWrongName_shouldReturnNull() {
//        given
        String wrongName = "zgergnekrgjnergnewrgjn";
//        when
        PlayerModel returnedPlayer = testPlayerDaoJdbc.get(wrongName);
//        then
        assertNull(returnedPlayer);

    }

    @Test
    void givenRecord_whenUpdate_shouldUpdateRecord() {
//        given
        PlayerModel existingPlayer = testPlayerDaoJdbc.get(testName);
        int attackForChanging =  25;
//        when
        existingPlayer.setAttack(attackForChanging);
        testPlayerDaoJdbc.update(existingPlayer);
//        then
        PlayerModel updatedPlayer = testPlayerDaoJdbc.get(testName);
        assertEquals(updatedPlayer.getAttack(), existingPlayer.getAttack());
    }

    @Test
    void givenNewPlayer_whenAdding_shouldReturnOneMorePlayersFromDb() {
        PlayerModel newPlayerToInsert = new PlayerModel("test_test_player", 3, 16, 8, 10, 5, 5);
        List<PlayerModel> listOfAllPlayers = testPlayerDaoJdbc.getAll();
        int countOfPlayers = listOfAllPlayers.size();
//        when
        testPlayerDaoJdbc.add(newPlayerToInsert);
        List<PlayerModel> listOfAllPlayersAfterInserting = testPlayerDaoJdbc.getAll();
        int countOfPlayersAfterInserting = listOfAllPlayersAfterInserting.size();
        //then
        assertEquals(countOfPlayers+1, countOfPlayersAfterInserting);

    }

}
