package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.InventoryModel;

import java.util.List;

public interface InventoryDao {
    void add(InventoryModel inventory, String hero_name);
    void update(InventoryModel inventory);
    InventoryModel get(String hero_name);
    List<InventoryModel> getAll();
}
