package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.notInventoryItems.ArmorHelmet;
import com.codecool.dungeoncrawl.logic.notInventoryItems.Axe;
import com.codecool.dungeoncrawl.logic.notInventoryItems.Heart;
import com.codecool.dungeoncrawl.logic.notInventoryItems.Sword;
import com.codecool.dungeoncrawl.logic.actors.*;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(String filename) {
            Scanner scanner;
            if (filename.length()>40){
                scanner = new Scanner(filename);
            }else{
                InputStream is = MapLoader.class.getResourceAsStream(filename);
                scanner = new Scanner(is);}

            int width = scanner.nextInt();
            int height = scanner.nextInt();
            scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            new Skeleton(cell);
                            break;
                        case 'G':
                            cell.setType(CellType.FLOOR);
                            new Ghost(cell);
                            break;
                        case 'M':
                            cell.setType(CellType.FLOOR);
                            new Monster(cell);
                            break;
                        case '!':
                            cell.setType(CellType.FLOOR);
                            new Boss(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            Inventory inventory = new Inventory();
                            map.setPlayer(new Player(cell, inventory));
                            break;
                        case '*':
                            cell.setType(CellType.FLOOR);
                            Bat bat = new Bat(cell);
                            map.addBat(bat);
                            break;
                        case 'k':
                            cell.setType(CellType.KEY);
                            break;
                        case 'h':
                            cell.setType(CellType.HEART);
                            new Heart(cell);
                            break;
                        case 'S':
                            cell.setType(CellType.STAIRS);
                            break;
                        case 'Y':
                            cell.setType(CellType.YELLOWDOOR);
                            break;
                        case 'y':
                            cell.setType(CellType.YELLOWDOOROPENED);
                            break;
                        case 'A':
                            cell.setType(CellType.LWALL);
                            break;
                        case 'Z':
                            cell.setType(CellType.LDCWALL);
                            break;
                        case 'X':
                            cell.setType(CellType.DWALL);
                            break;
                        case 'C':
                            cell.setType(CellType.RDCWALL);
                            break;
                        case 'D':
                            cell.setType(CellType.RWALL);
                            break;
                        case 'Q':
                            cell.setType(CellType.LUCWALL);
                            break;
                        case 'W':
                            cell.setType(CellType.UWALL);
                            break;
                        case 'E':
                            cell.setType(CellType.RUCWALL);
                            break;
                        case 'H':
                            cell.setType(CellType.FENCE);
                            break;
                        case 'b':
                            cell.setType(CellType.BLUEKEY);
                            break;
                        case 'B':
                            cell.setType(CellType.BLUEDOOR);
                            break;
                        case 'i':
                            cell.setType(CellType.BLUEDOOROPENED);
                            break;
                        case '%':
                            cell.setType(CellType.BUSH);
                            break;
                        case 'q':
                            cell.setType(CellType.GRAAL);
                            break;
                        case 'd':
                            cell.setType(CellType.BONES);
                            break;
                        case '$':
                            cell.setType(CellType.SWORD);
                            new Sword(cell);
                            break;
                        case 'T':
                            cell.setType(CellType.AXE);
                            new Axe(cell);
                            break;
                        case '?':
                            cell.setType(CellType.HINT);
                            break;
                        case '^':
                            cell.setType(CellType.HINT2);
                            break;
                        case 'U':
                            cell.setType(CellType.ENTRANCE);
                            break;
                        case 'a':
                            cell.setType(CellType.ARMOR);
                            new ArmorHelmet(cell);
                            break;
                        case 'V':
                            cell.setType(CellType.HELMET);
                            new ArmorHelmet(cell);
                            break;
                        case 'p':
                            cell.setType(CellType.POTION);
                            break;
                        case 'c':
                            cell.setType(CellType.CROWN);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
