package com.mygdx.game.desktop;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class UFO extends BaseActor{

    public UFO(float x, float y, Stage s) {
        super(x, y, s);

        loadTexture("ufo.png");

        addAction(Actions.forever(Actions.rotateBy(50, 1)));

        setSpeed(200);
        setMaxSpeed(300);
        setDeceleration(10);

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
