package com.mygdx.game.desktop;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class SmallRock extends BaseActor{
    Rock rock;

    public SmallRock(float x, float y, Stage s) {
        super(x, y, s);
        rock = new Rock(0,0, s);
        loadTexture("rock.png");
        setSize(getActorWidth(rock) / 2, getActorHeight(rock) / 2);

        float random = MathUtils.random(30);

        addAction(Actions.forever(Actions.rotateBy(30 + random, 1)));

        setSpeed(75 + random);
        setMaxSpeed(90 + random);
        setDeceleration(0);

        setMotionAngle(MathUtils.random(360));
    }

    @Override
    public void act(float dt)
    {
        super.act(dt);
        applyPhysics(dt);
        wrapAroundWorld();
    }
}
