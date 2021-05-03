package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDaoJdbc implements PlayerDao {
    private DataSource dataSource;

    public PlayerDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(PlayerModel player) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO player (player_name, hp, max_hp, attack, x, y, score) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, player.getPlayerName());
            statement.setInt(2, player.getHp());
            statement.setInt(3, player.getMax_hp());
            statement.setInt(4, player.getAttack());
            statement.setInt(5, player.getX());
            statement.setInt(6, player.getY());
            statement.setInt(7, player.getScore());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            player.setId(resultSet.getInt(1));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(PlayerModel player) {
        String sql = "UPDATE player SET hp = ?, max_hp = ?, attack = ?, x = ?, y = ? WHERE player_name = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, player.getHp());
            ps.setInt(2, player.getMax_hp());
            ps.setInt(3, player.getAttack());
            ps.setInt(4, player.getX());
            ps.setInt(5, player.getY());
            ps.setString(6, player.getPlayerName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PlayerModel get(String hero_name) {
        String sql = "SELECT * FROM player WHERE player_name = ?";
        PlayerModel player = null;
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, hero_name);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    player = new PlayerModel(rs.getString("player_name"),rs.getInt("x"),
                            rs.getInt("y"), rs.getInt("hp"),
                            rs.getInt("max_hp"), rs.getInt("attack"), rs.getInt("score"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return player;
    }

    @Override
    public List<PlayerModel> getAll() {
        String sql = "SELECT * FROM player";
        List<PlayerModel> allPlayers = new ArrayList<PlayerModel>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    allPlayers.add(new PlayerModel(rs.getString("player_name"),rs.getInt("x"),
                            rs.getInt("y"), rs.getInt("hp"),
                            rs.getInt("max_hp"), rs.getInt("attack"), rs.getInt("score")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allPlayers;
    }

    @Override
    public Boolean CheckIfPlayerExists(String playerName) {
        String sql = "SELECT * FROM player WHERE player_name = ?";
        PlayerModel player = null;
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, playerName);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                        return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
