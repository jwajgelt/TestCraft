package testcraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.core.screen.transition.FadeInTransition;
import org.mini2Dx.core.screen.transition.FadeOutTransition;
import org.mini2Dx.core.screen.transition.NullTransition;

import static testcraft.TestCraftGame.multiplexer;

public class LoadGameScreen extends   MenuScreen {

    public static int ID = 5;
    boolean toMainMenu=false;

    @Override
    public void initialise(GameContainer gc) {
        super.initialise(gc);

        for (int i=0;i<15;i++)
            stage.addActor(new SaveLoadButton(150+200*((i%5)), HEIGHT-(i/5+1)*175,125,175,skin,false,i));

        addButton( 50, new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                toMainMenu=true;
            }
        }, "RETURN");
    }

    @Override
    public void goBack (ScreenManager screenManager, GameContainer gc)
    {
        screenManager.getGameScreen(InGameScreen.ID).initialise(gc);
        back=false;
        multiplexer.removeProcessor(stage);
        screenManager.enterGameScreen(InGameScreen.ID, new NullTransition(), new NullTransition());
    }
    public void gotoMainMenu (ScreenManager screenManager)
    {
        toMainMenu=false;
        multiplexer.removeProcessor(stage);
        screenManager.enterGameScreen(MainMenuScreen.ID, new NullTransition(), new NullTransition());
    }

    @Override
    public void update(GameContainer gc,  ScreenManager screenManager, float delta) {

        for (Actor t : stage.getActors())
            if(t instanceof  SaveLoadButton)
                    ((SaveLoadButton) t).update();

        if(!multiplexer.getProcessors().contains(stage,false))
            multiplexer.addProcessor(stage);

        if(toMainMenu || Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            gotoMainMenu(screenManager);
        if(back)
            goBack(screenManager,gc);

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
