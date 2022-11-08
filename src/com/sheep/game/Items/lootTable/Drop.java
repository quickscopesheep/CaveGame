package com.sheep.game.Items.lootTable;

public class Drop {
    public Class itemClass;
    public int weight;

    public Drop(Class itemClassName, int weight) {
        this.itemClass = itemClassName;
        this.weight = weight;
    }
}
