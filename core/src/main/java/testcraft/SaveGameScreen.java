package testcraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.core.screen.transition.NullTransition;

import static testcraft.TestCraftGame.multiplexer;

public class SaveGameScreen extends   MenuScreen {

    public static int ID = 4;

    @Override
    public void initialise(GameContainer gc) {
        super.initialise(gc);

        for (int i=0;i<15;i++)
            stage.addActor(new SaveLoadButton(150+200*((i%5)), HEIGHT-(i/5+1)*175,125,175,skin,true,i));

        addButton( 50, new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                back=true;
            }
        }, "RETURN");
    }


    @Override
    public void goBack (ScreenManager screenManager, GameContainer gameContainer)
    {
        back=false;
        multiplexer.removeProcessor(stage);
        screenManager.enterGameScreen(InGameMenuScreen.ID, new NullTransition(), new NullTransition());
    }

    @Override
    public void update(GameContainer gc,  ScreenManager screenManager, float delta) {

        if(!multiplexer.getProcessors().contains(stage,false))
            multiplexer.addProcessor(stage);

        if(back || Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            goBack(screenManager, gc);

    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        super.render(gc,g);
    }

    @Override
    public int getId(){
        return ID;
    }


}
