package com.mygdx.game.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Spaceship extends BaseActor{

    private final Thrusters thrusters;
    private Shield shield;
    public int shieldPower;
    private int maxLasers;
    private int usedLasers;

    public Spaceship(float x, float y, Stage s)
    {
        super(x, y, s);

        loadTexture("spaceship.png");
        setBoundaryPolygon(8);

        setAcceleration(200);
        setMaxSpeed(150);
        setDeceleration(10);

        thrusters = new Thrusters(0, 0, s);
        addActor(thrusters);
        thrusters.setPosition(-thrusters.getWidth(), getHeight()/2 - thrusters.getHeight()/2);

        shield = new Shield(0, 0, s);
        addActor(shield);
        shield.centerAtPosition(getWidth()/2, getHeight()/2);
        shieldPower = 150;

        maxLasers = 10;
        usedLasers = 0;
    }

    @Override
    public void act(float dt)
    {
        super.act(dt);
        float degreesPerSecond = 120; // rotation speed
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            rotateBy(degreesPerSecond * dt);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            rotateBy(-degreesPerSecond * dt);

        if (Gdx.input.isKeyPressed(Input.Keys.UP))
        {
            accelerateAtAngle(getRotation());
            thrusters.setVisible(true);
        }
        else
            thrusters.setVisible(false);

        applyPhysics(dt);

        wrapAroundWorld();

        // as shieldPower decreases, opacity decreases as well.
        shield.setOpacity(shieldPower / 100f);
        if (shieldPower <= 0)
            shield.setVisible(false);
    }

    public void warp()
    {
        // verify if spaceship is still part of the game
        if (getStage() == null)
            return;

        Warp warp1 = new Warp(0, 0, this.getStage());
        warp1.centerAtActor(this);
        // teleports spaceship at a random location based on argument range
        // 800 for x and 600 for y because that is the screen size of the game
        setPosition(MathUtils.random(800), MathUtils.random(600));
        Warp warp2 = new Warp(0, 0, this.getStage());
        warp2.centerAtActor(this);
    }

    public void shoot()
    {
        if (getStage() == null)
            return;
        if (usedLasers == maxLasers)
            return;
        Laser laser = new Laser(0, 0, this.getStage());
        laser.centerAtActor(this);
        laser.setRotation(this.getRotation());
        laser.setMotionAngle(this.getRotation());
        this.usedLasers++;
    }
}
