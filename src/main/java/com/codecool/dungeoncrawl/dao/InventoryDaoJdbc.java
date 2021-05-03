package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.InventoryModel;
import com.codecool.dungeoncrawl.model.PlayerModel;
import com.codecool.dungeoncrawl.ui.UI;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDaoJdbc implements InventoryDao{
    private DataSource dataSource;

    public InventoryDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(InventoryModel inventory, String hero_name) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO inventory (hero_name, blue_key, yellow_key, potion) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, hero_name);
            statement.setInt (2, inventory.getBlue_key());
            statement.setInt(3, inventory.getKey());
            statement.setInt(4, inventory.getPotion());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(InventoryModel inventory) {
        String sql = "UPDATE inventory SET blue_key = ?, yellow_key = ?, potion = ? WHERE hero_name = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, inventory.getBlue_key());
            ps.setInt(2, inventory.getKey());
            ps.setInt(3, inventory.getPotion());
            ps.setString(4, UI.heroName.getText());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public InventoryModel get(String hero_name) {
        String sql = "SELECT * FROM inventory WHERE hero_name = ?";
        InventoryModel inventory = null;
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, hero_name);
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    inventory = new InventoryModel(rs.getInt("blue_key"), rs.getInt("yellow_key"),
                            rs.getInt("potion"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventory;
    }

    @Override
    public List<InventoryModel> getAll() {
        String sql = "SELECT * FROM inventory";
        List<InventoryModel> allInventory = new ArrayList<InventoryModel>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    allInventory.add(new InventoryModel(rs.getInt("blue_key"),rs.getInt("yellow_key"),
                            rs.getInt("potion")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allInventory;
    }
}
