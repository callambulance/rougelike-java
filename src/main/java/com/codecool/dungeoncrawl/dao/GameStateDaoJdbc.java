package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.MapMaker;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.InventoryModel;
import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameStateDaoJdbc implements GameStateDao {
    private DataSource dataSource;


    public GameStateDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(GameState state) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO game_state (current_map, saved_at, hero_name) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, state.getCurrentMap());
            statement.setDate(2, state.getSavedAt());
            statement.setString(3, state.getPlayer().getPlayerName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(GameState state) {
        String sql = "UPDATE game_state SET current_map = ?, saved_at = ? WHERE hero_name = ?";
        String playerName = state.getPlayer().getPlayerName();
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, state.getCurrentMap());
            ps.setDate(2, state.getSavedAt());
            ps.setString(3, playerName);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public GameState get(String hero_name) {
        PlayerModel player = new PlayerDaoJdbc(dataSource).get(hero_name);
        InventoryModel inventory = new InventoryDaoJdbc(dataSource).get(hero_name);
        String sql = "SELECT * FROM game_state WHERE hero_name = ?";
        GameState state = null;
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, hero_name);
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    state = new GameState(rs.getString("current_map"), rs.getDate("saved_at"), player, inventory);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return state;
    }

    @Override
    public List<GameState> getAll() {
        String sql = "SELECT * FROM game_state";
        List<GameState> allGameStates = new ArrayList<GameState>();
        List<InventoryModel> allInventories = new InventoryDaoJdbc(dataSource).getAll();
        List<PlayerModel> allPlayers = new PlayerDaoJdbc(dataSource).getAll();
        int count = 0;

            try (Connection con = dataSource.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                            allGameStates.add(new GameState(rs.getString("current_map"),
                                    rs.getDate("saved_at"), allPlayers.get(count),
                                    allInventories.get(count)));
                        count++;
                    }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGameStates;
    }
}
