package com.sheep.game.UI;

import com.sheep.game.Game;
import com.sheep.game.GameSettings;
import com.sheep.game.UI.Widgets.ButtonWidget;
import com.sheep.game.UI.Widgets.MultipleChoiceButton;
import com.sheep.game.UI.Widgets.VerticalLayoutGroup;
import com.sheep.game.UI.Widgets.buttonFunctions.StartGameCallback;
import com.sheep.game.UI.Widgets.buttonFunctions.SwitchMenuCallback;

public class StartGameMenu extends Menu{
    public static StartGameMenu menu = new StartGameMenu();

    MultipleChoiceButton difficultyButton;
    MultipleChoiceButton mapSizeButton;

    public StartGameMenu(){
        super();
        constructMenu();
    }

    @Override
    protected void constructMenu() {
        VerticalLayoutGroup group;
        widgets.add(group = new VerticalLayoutGroup(Game.WIDTH/2, Game.HEIGHT/2, this, 2));

        group.AddWidget(new ButtonWidget(Game.WIDTH/2, Game.HEIGHT/2 - 4, "Start Game", this, new StartGameCallback()));
        group.AddWidget(difficultyButton = new MultipleChoiceButton(Game.WIDTH/2, Game.HEIGHT/2 + 6 + 4, this, new String[]{
                "Difficulty: Easy",
                "Difficulty: Normal",
                "Difficulty: Hard"
        }));
        group.AddWidget(mapSizeButton = new MultipleChoiceButton(Game.WIDTH/2, Game.HEIGHT/2 + 16 + 4, this, new String[]{
                "Map Size: Small",
                "Map Size: Medium",
                "Map Size: Large"
        }));
    }

    public GameSettings makeGameSettings(){
        int dificulty = difficultyButton.getIndex();
        int mapSize = 0;

        System.out.println(difficultyButton.getIndex() + " , " + mapSizeButton.getIndex());

        switch (mapSizeButton.getIndex()) {
            case 0 -> mapSize = 64;
            case 1 -> mapSize = 128;
            case 2 -> mapSize = 256;
        }

        return new GameSettings(dificulty, mapSize);
    }
}
