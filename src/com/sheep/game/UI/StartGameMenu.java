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
    MultipleChoiceButton floorsButton;

    public StartGameMenu(){
        super();
        constructMenu();
    }

    @Override
    protected void constructMenu() {
        VerticalLayoutGroup group;
        widgets.add(group = new VerticalLayoutGroup(Game.WIDTH/2, Game.HEIGHT/2, this, 2, 2));

        group.AddWidget(new ButtonWidget(Game.WIDTH/2, Game.HEIGHT/2 - 4, "Start Game", this, new StartGameCallback()));
        group.AddWidget(difficultyButton = new MultipleChoiceButton(Game.WIDTH/2, Game.HEIGHT/2 + 6 + 4, this, new String[]{
                "Difficulty: Easy",
                "Difficulty: Normal",
                "Difficulty: Hard",
                "Difficulty: Impossible"
        }, 1));
        group.AddWidget(floorsButton = new MultipleChoiceButton(Game.WIDTH/2, Game.HEIGHT/2 + 6 + 4, this, new String[]{
                "Floors: 2",
                "Floors: 3",
                "Floors: 4",
                "Floors: 5"
        }, 1));
    }

    public GameSettings makeGameSettings(){
        int dificulty = difficultyButton.getIndex();
        int floors = floorsButton.getIndex()+2;

        return new GameSettings(dificulty, floors);
    }
}
