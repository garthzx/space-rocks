package com.mygdx.game.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class MenuScreen extends BaseScreen{

    public void initialize()
    {
        BaseActor space = new BaseActor(0, 0, mainStage);
        space.loadTexture("space.png");
        space.setSize(800, 600);
        BaseActor.setWorldBounds(space);

        BaseActor title = new BaseActor(0, 0, mainStage);
        title.loadTexture("start.png");
        title.setScale(title.getScaleX() / 1.5f, title.getScaleY() / 1.5f );
        title.centerAtPosition(400, 300);
        title.moveBy(0, 100);
    }

    public void update(float dt)
    {
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER))
            SpaceGame.setActiveScreen(new LevelScreen());
    }
}
