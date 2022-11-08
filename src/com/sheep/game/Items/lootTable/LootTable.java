package com.sheep.game.Items.lootTable;

import com.sheep.game.level.Level;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LootTable {
    List<Drop> drops;
    Random random;

    //public LootTable(String path){
    //    List<Drop> dropList = new ArrayList<>();
//
    //    JSONParser parser = new JSONParser();
    //    try {
    //        JSONArray array = (JSONArray) parser.parse(new FileReader(path));
    //        for (Object obj : array) {
    //            String itemClassName = (String) (((JSONObject) obj).get("item_name"));
    //            int dropWeight = Integer.parseInt ((String) (((JSONObject) obj).get("weight")));
//
    //            dropList.add(new Drop(itemClassName, dropWeight));
    //        }
    //    }catch (Exception e){
    //        throw new RuntimeException();
    //    }
//
    //    drops = new Drop[dropList.size()];
//
    //    for (int i = 0; i < drops.length; i++) {
    //        drops[i] = dropList.get(i);
    //    }
//
    //    random = new Random();
    //}

    public LootTable(){
        drops = new ArrayList<>();
        random = new Random();
    }

    public void addDrop(Class dropClass, int weight){
        drops.add(new Drop(dropClass, weight));
    }

    public Drop getRandomDrop(){
        int roll = random.nextInt(100);

        for (int i = 0; i < drops.size(); i++){
            roll -= drops.get(i).weight;

            if(roll <= 0){
                return drops.get(i);
            }
        }

        return drops.get(0);
    }
}
