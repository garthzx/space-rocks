package com.mygdx.game.desktop;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.compression.lzma.Base;

public class LevelScreen extends BaseScreen{

    private Spaceship spaceship;
    private boolean gameOver;
    private float timer;
    public void initialize()
    {
        BaseActor space = new BaseActor(0 ,0, mainStage);
        space.loadTexture("space.png");
        space.setSize(800, 600);
        BaseActor.setWorldBounds(space);

        spaceship = new Spaceship(400, 300, mainStage);

        new Rock(600, 500, mainStage);
        new Rock(600, 300, mainStage);
        new Rock(600, 100, mainStage);
        new Rock(400, 100, mainStage);
        new Rock(200, 100, mainStage);
        new Rock(200, 300, mainStage);
        new Rock(200, 500, mainStage);
        new Rock(400, 500, mainStage);

        timer = 0f;
        gameOver = false;
    }

    public void update(float dt)
    {
        for (BaseActor rockActor : BaseActor.getList(mainStage, "com.mygdx.game.desktop.Rock"))
        {
            if (rockActor.overlaps(spaceship))
            {
                if (spaceship.shieldPower <= 0)
                {
                    Explosion boom = new Explosion(0, 0, mainStage);
                    boom.centerAtActor(spaceship);
                    spaceship.remove();
                    spaceship.setPosition(-1000, -1000);

                    BaseActor messageLose = new BaseActor(0, 0, uiStage);
                    messageLose.loadTexture("message-lose.png");
                    messageLose.centerAtPosition(400, 300);
                    messageLose.setOpacity(0);
                    messageLose.addAction(Actions.fadeIn(1));
                    gameOver = true;
                }
                else
                {
                    spaceship.shieldPower -= 34;
                    Explosion boom = new Explosion(0, 0, mainStage);
                    boom.centerAtActor(rockActor);
                    rockActor.remove();
                }
            }

            for (BaseActor laserActor : BaseActor.getList(mainStage, "com.mygdx.game.desktop.Laser"))
            {
                if (laserActor.overlaps(rockActor))
                {
                    new SmallRock(rockActor.getX() + 5, rockActor.getY() + 5, mainStage);
                    new SmallRock(rockActor.getX() - 5, rockActor.getY() - 5, mainStage);

                    Explosion boom = new Explosion(0, 0, mainStage);
                    boom.centerAtActor(rockActor);
                    laserActor.remove();
                    rockActor.remove();
                }
            }
            for (BaseActor smallRock : BaseActor.getList(mainStage, "com.mygdx.game.desktop.SmallRock"))
            {
                for (BaseActor laserActor : BaseActor.getList(mainStage, "com.mygdx.game.desktop.Laser"))
                {
                    if (laserActor.overlaps(smallRock))
                    {
                        Explosion boom = new Explosion(0, 0, mainStage);
                        boom.centerAtActor(smallRock);
                        laserActor.remove();
                        smallRock.remove();
                    }
                }
                if (smallRock.overlaps(spaceship))
                {
                    if (spaceship.shieldPower <= 0)
                    {
                        Explosion boom = new Explosion(0, 0, mainStage);
                        boom.centerAtActor(spaceship);
                        spaceship.remove();
                        spaceship.setPosition(-1000, -1000);

                        BaseActor messageLose = new BaseActor(0, 0, uiStage);
                        messageLose.loadTexture("message-lose.png");
                        messageLose.centerAtPosition(400, 300);
                        messageLose.setOpacity(0);
                        messageLose.addAction(Actions.fadeIn(1));
                        gameOver = true;
                    }
                    else
                    {
                        spaceship.shieldPower -= 34;
                        Explosion boom = new Explosion(0, 0, mainStage);
                        boom.centerAtActor(smallRock);
                        smallRock.remove();
                    }
                }
            }
        }
        if (!gameOver && BaseActor.count(mainStage, "com.mygdx.game.desktop.Rock") == 0
                && BaseActor.count(mainStage, "com.mygdx.game.desktop.SmallRock") == 0)
        {
            BaseActor messageWin = new BaseActor(0, 0, uiStage);
            messageWin.loadTexture("message-win.png");
            messageWin.centerAtPosition(400, 300);
            messageWin.setOpacity(0);
            messageWin.addAction(Actions.fadeIn(1));
            gameOver = true;
        }

        if (timer >= 30)
        {
            timer = 0;
            BaseActor messageWin = new BaseActor(0, 0, uiStage);
            messageWin.loadTexture("message-win.png");
            messageWin.centerAtPosition(400, 300);
            messageWin.setOpacity(0);
            messageWin.addAction(Actions.fadeIn(1));
            gameOver = true;
        }
        else
            timer += dt;
        System.out.println(timer);
    }

    // override default InputProcessor method
    @Override
    public boolean keyDown(int keycode)
    {
        if (keycode == Input.Keys.X)
            spaceship.warp();
        if (keycode == Input.Keys.SPACE)
            spaceship.shoot();
        if (keycode == Input.Keys.F)
            spaceship.dash();
        return false;
    }
}
