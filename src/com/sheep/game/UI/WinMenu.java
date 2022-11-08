package com.sheep.game.UI;

import com.sheep.game.Game;
import com.sheep.game.UI.Widgets.ButtonWidget;
import com.sheep.game.UI.Widgets.TextWidget;
import com.sheep.game.UI.Widgets.VerticalLayoutGroup;
import com.sheep.game.UI.Widgets.buttonFunctions.SwitchMenuCallback;
import com.sheep.game.entity.mob.Player;

public class WinMenu extends Menu{

    public WinMenu(Game game){
        super(game);
        constructMenu();
    }

    @Override
    protected void constructMenu() {
        VerticalLayoutGroup list = new VerticalLayoutGroup(Game.WIDTH/2, Game.HEIGHT/2, this, 2, 2, game);
        AddWidget(list);


        list.AddWidget(new TextWidget(0, 0, this, "Kills: " + Player.kills, 0xffffff, game));
        list.AddWidget(new TextWidget(0, 0, this, "Deaths: " + Player.deaths, 0xffffff, game));

        AddWidget(new TextWidget(Game.WIDTH/2, Game.HEIGHT/2 - 32, this, "You Won", 0xeeeeee, game));
        AddWidget(new ButtonWidget(Game.WIDTH/2, Game.HEIGHT/2 + 32, "Return To Main Menu", this, new SwitchMenuCallback("main"), game));
    }
}
