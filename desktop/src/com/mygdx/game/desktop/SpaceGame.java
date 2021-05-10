package com.mygdx.game.desktop;

public class SpaceGame extends BaseGame{

    public void create()
    {
        super.create();
        setActiveScreen(new MenuScreen());
    }
}
