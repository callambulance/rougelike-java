package com.codecool.dungeoncrawl.logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class MapMaker {
    public static ArrayList listOfCharacters;

    public static void makeMap(GameMap map) throws IOException {
        listOfCharacters = new ArrayList();
        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                if (map.getCell(j, i).getActor() != null) {
                    switch (map.getCell(j, i).getActor().getTileName()) {
                        case "skeleton":
                            listOfCharacters.add('s');
                            break;
                        case "bat":
                            listOfCharacters.add('*');
                            break;
                        case "ghost":
                            listOfCharacters.add('G');
                            break;
                        case "monster":
                            listOfCharacters.add('M');
                            break;
                        case "boss":
                            listOfCharacters.add('!');
                            break;
                        case "player":
                            listOfCharacters.add('@');
                            break;
                        case "godmode":
                            listOfCharacters.add('@');
                            break;
                        case "batman":
                            listOfCharacters.add('@');
                            break;
                    }
                } else {
                    switch (map.getCell(j, i).getType()) {
                        case WALL:
                            listOfCharacters.add('#');
                            break;
                        case EMPTY:
                            listOfCharacters.add(' ');
                            break;
                        case FLOOR:
                            listOfCharacters.add('.');
                            break;
                        case POTION:
                            listOfCharacters.add('p');
                            break;
                        case AXE:
                            listOfCharacters.add('T');
                            break;
                        case BUSH:
                            listOfCharacters.add('%');
                            break;
                        case HINT:
                            listOfCharacters.add('?');
                            break;
                        case HEART:
                            listOfCharacters.add('h');
                            break;
                        case KEY:
                            listOfCharacters.add('k');
                            break;
                        case STAIRS:
                            listOfCharacters.add('S');
                            break;
                        case YELLOWDOOR:
                            listOfCharacters.add('Y');
                            break;
                        case YELLOWDOOROPENED:
                            listOfCharacters.add('y');
                        case LWALL:
                            listOfCharacters.add('A');
                            break;
                        case LDCWALL:
                            listOfCharacters.add('Z');
                            break;
                        case DWALL:
                            listOfCharacters.add('X');
                            break;
                        case RDCWALL:
                            listOfCharacters.add('C');
                            break;
                        case RWALL:
                            listOfCharacters.add('D');
                            break;
                        case LUCWALL:
                            listOfCharacters.add('Q');
                            break;
                        case UWALL:
                            listOfCharacters.add('W');
                            break;
                        case RUCWALL:
                            listOfCharacters.add('E');
                            break;
                        case FENCE:
                            listOfCharacters.add('H');
                            break;
                        case BLUEKEY:
                            listOfCharacters.add('b');
                            break;
                        case BLUEDOOR:
                            listOfCharacters.add('B');
                            break;
                        case BLUEDOOROPENED:
                            listOfCharacters.add('i');
                            break;
                        case GRAAL:
                            listOfCharacters.add('q');
                            break;
                        case BONES:
                            listOfCharacters.add('d');
                            break;
                        case SWORD:
                            listOfCharacters.add('$');
                            break;
                        case HINT2:
                            listOfCharacters.add('^');
                            break;
                        case ENTRANCE:
                            listOfCharacters.add('U');
                            break;
                        case ARMOR:
                            listOfCharacters.add('a');
                            break;
                        case HELMET:
                            listOfCharacters.add('V');
                            break;
                        case CROWN:
                            listOfCharacters.add('c');
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + "'");
                    }
                }
            }
        }
        separateLines(map);
    }

    public static void separateLines(GameMap map) throws IOException {

        int row = 1;
        while (row < map.getHeight()) {
            listOfCharacters.add((map.getWidth() * row) + (row - 1), "\n");
            row++;
        }


        listOfCharacters.add(0, map.getHeight() + "\n");
        listOfCharacters.add(0, map.getWidth() + " ");


        File file = new File("src/main/resources/testMap.txt");
        String listString = (String) listOfCharacters.stream().map(Object::toString).collect(Collectors.joining(""));
        FileWriter myWriter = new FileWriter("src/main/resources/testMap.txt");
        myWriter.write(listString);
        myWriter.close();
    }

    public static String listRoString(){
        String listString = (String) listOfCharacters.stream().map(Object::toString).collect(Collectors.joining(""));
        return listString;
    }
}
