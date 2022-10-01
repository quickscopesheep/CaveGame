package com.sheep.game.Items.lootTable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LootTable {
    Drop[] drops;
    Random random = new Random();

    public LootTable(String path){
        List<Drop> dropList = new ArrayList<>();

        JSONParser parser = new JSONParser();
        try {
            JSONArray array = (JSONArray) parser.parse(new FileReader(path));
            for (Object obj : array) {
                JSONObject jsonObject = (JSONObject) obj;

                String itemClassName = (String) (((JSONObject) obj).get("item_name"));
                int dropWeight = Integer.parseInt ((String) (((JSONObject) obj).get("weight")));

                dropList.add(new Drop(itemClassName, dropWeight));
            }
        }catch (Exception e){
            throw new RuntimeException();
        }

        drops = new Drop[dropList.size()];

        for (int i = 0; i < drops.length; i++) {
            drops[i] = dropList.get(i);
        }

        random = new Random();
    }

    public Drop getRandomDrop(){
        int roll = random.nextInt(100);

        for (int i = 0; i < drops.length; i++){
            roll -= drops[i].weight;

            if(roll <= 0){
                return drops[i];
            }
        }

        return drops[0];
    }

    public Drop[] getDrops() {
        return drops;
    }
}
