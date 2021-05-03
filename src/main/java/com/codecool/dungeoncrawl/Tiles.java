package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.Main.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("key", new Tile(16, 23));
        tileMap.put("heart", new Tile(23, 22));
        tileMap.put("stairs", new Tile(2,6));
        tileMap.put("yellow_door", new Tile(23,11));
        tileMap.put("yellow_door_opened", new Tile(23,10));
        tileMap.put("left_wall", new Tile(18,1));
        tileMap.put("left_down_corner_wall", new Tile(18,2));
        tileMap.put("down_wall", new Tile(19,2));
        tileMap.put("right_down_corner_wall", new Tile(20,2));
        tileMap.put("left_up_corner_wall", new Tile(18,0));
        tileMap.put("up_wall", new Tile(19,0));
        tileMap.put("right_up_corner_wall", new Tile(20,0));
        tileMap.put("right_wall", new Tile(20,1));
        tileMap.put("ghost", new Tile(27,6));
        tileMap.put("batman", new Tile(27,2));
        tileMap.put("godmode", new Tile(24,7));
        tileMap.put("fence", new Tile(5,3));
        tileMap.put("blue_key", new Tile(17,23));
        tileMap.put("blue_door", new Tile(0,9));
        tileMap.put("blue_door_opened", new Tile(2, 9));
        tileMap.put("bush", new Tile(6,2));
        tileMap.put("monster", new Tile(28,6));
        tileMap.put("graal", new Tile(5,15));
        tileMap.put("bones", new Tile(0,15));
        tileMap.put("axe", new Tile(10,30));
        tileMap.put("sword", new Tile(4,30));
        tileMap.put("hint", new Tile(22,25));
        tileMap.put("hint2", new Tile(22,25));
        tileMap.put("entrance", new Tile(4,13));
        tileMap.put("armor", new Tile(0,23));
        tileMap.put("helmet", new Tile(0,22));
        tileMap.put("boss", new Tile(30,6));
        tileMap.put("potion", new Tile(23, 23));
        tileMap.put("bat", new Tile(26,8));
        tileMap.put("crown", new Tile(13,30));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }

    public static Image getImage(int x, int y){
        PixelReader reader = tileset.getPixelReader();
        return new WritableImage(reader, x * (TILE_WIDTH + 2), y * (TILE_WIDTH + 2), TILE_WIDTH, TILE_WIDTH);
    }
}
