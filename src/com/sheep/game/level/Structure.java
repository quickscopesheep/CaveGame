package com.sheep.game.level;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Structure {
    int width, height;
    int[] tiles;

    public Structure(int width, int height, int[] tiles) {
        this.width = width;
        this.height = height;
        this.tiles = tiles;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[] getTiles() {
        return tiles;
    }

    public static List<Structure> loadStructures(String path){
        List<Structure> structures = new ArrayList<>();

        JSONParser parser = new JSONParser();
        try {
            JSONArray array = (JSONArray) parser.parse(new FileReader(path));
            for(Object obj : array){
                JSONObject jsonObject = (JSONObject) obj;

                int width = Integer.parseInt((String)jsonObject.get("width"));
                int height = Integer.parseInt((String)jsonObject.get("height"));

                String map = (String)jsonObject.get("tiles");

                int[] tiles = new int[width*height];
                for(int i = 0; i < tiles.length; i++){
                    if(map.charAt(i) == '#'){
                        tiles[i] = 1;
                    }else if (map.charAt(i) == '.'){
                        tiles[i] = 0;
                    }
                }

                structures.add(new Structure(width, height, tiles));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return structures;
    }
}
