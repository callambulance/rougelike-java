package com.codecool.dungeoncrawl.logic;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JasonDataGame {

    public static String jsonDataGameSaver() {

        JSONObject savedData = new JSONObject();

        JSONArray arrayPlayerData = new JSONArray();
        JSONObject jsonPlayerData = new JSONObject();

        arrayPlayerData.add(jsonPlayerData);

        JSONArray arrayPlayerInventory = new JSONArray();
        JSONObject jsonPlayerInventory = new JSONObject();
        jsonPlayerInventory.put("Potion", 2);
        jsonPlayerInventory.put("Keys", new String[]{"yellow", "blue"});

        arrayPlayerInventory.add(jsonPlayerInventory);

        JSONObject jsonGameMap = new JSONObject();
        jsonGameMap.put("Current Map", "10 10" +
                "##########" +
                "#....@...#" +
                "#........#" +
                "#........#" +
                "#........#" +
                "#........#" +
                "#........#" +
                "#........#" +
                "#........#" +
                "##########");

        savedData.put("Map", jsonGameMap);
        savedData.put("Inventory", arrayPlayerInventory);
        savedData.put("Player data", arrayPlayerData);

        //Write JSON file
        try (FileWriter file = new FileWriter("sample_data\\dungeon.json")) {

            file.write(savedData.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "File saved";
    }
}
