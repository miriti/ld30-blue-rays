package game.hud;

import io.github.miriti.Consts;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class HUD {
	private Stage stage;

	private static HUD instance;

	public static HUD getInstance() {
		return instance;
	}

	public HUD() {
		instance = this;
		stage = new Stage(new ExtendViewport(Consts.SCENE_WIDTH,
				Consts.SCENE_WIDTH));
	}

	public Subtitle say(String str) {
		Subtitle subtl = new Subtitle(str);
		subtl.setPosition(100, 100);
		stage.addActor(subtl);
		return subtl;
	}

	public void render() {
		stage.draw();
	}

	public void act(float delta) {
		stage.act(delta);
	}
}
