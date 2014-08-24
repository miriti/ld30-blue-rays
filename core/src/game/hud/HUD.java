package game.hud;

import java.util.ArrayList;

import io.github.miriti.Consts;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class HUD {
	private Stage stage;
	private Inventory inventory;
	private static HUD instance;
	private ArrayList<Subtitle> subtitles = new ArrayList<Subtitle>();

	public static HUD getInstance() {
		return instance;
	}

	public HUD() {
		instance = this;
		stage = new Stage(new ExtendViewport(Consts.SCENE_WIDTH,
				Consts.SCENE_WIDTH));
		inventory = new Inventory();
		inventory.setPosition((Consts.SCENE_WIDTH + inventory.getWidth()) / 2,
				20);
		stage.addActor(inventory);
	}

	/**
	 * Add a subtitle
	 * 
	 * @param str
	 * @return
	 */
	public Subtitle say(String str) {
		Subtitle subtl = new Subtitle(str);
		subtl.setPosition(50, 400);
		stage.addActor(subtl);

		for (int i = 0; i < subtitles.size(); i++) {
			subtitles.get(i).setY(subtitles.get(i).getY() + 50);
		}
		subtitles.add(subtl);

		return subtl;
	}

	public void render() {
		stage.draw();
	}

	public void act(float delta) {
		stage.act(delta);

		for (int i = subtitles.size() - 1; i >= 0; i--) {
			Subtitle s = subtitles.get(i);
			if (s.done) {
				subtitles.remove(i);
			}
		}
	}
}
