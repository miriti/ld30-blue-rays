package io.github.miriti.base;

import io.github.miriti.Consts;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * 
 * @author Michael (KEFIR) Miriti <michael@miriti.ru>
 * @date Aug 23, 2014
 *
 */
public abstract class State {
	protected Stage stage;
	protected Viewport viewport;

	public State() {
		initDefaultStage();
	}

	protected void initDefaultStage() {
		viewport = new ExtendViewport(Consts.SCENE_WIDTH, Consts.SCENE_HEIGHT);
		stage = new Stage();
	}

	public void addActor(Actor actor) {
		stage.addActor(actor);
	}

	public Stage getStage() {
		return stage;
	}

	public void draw() {
		stage.draw();
	}

	public void update(float delta) {
		stage.act(delta);
	}

	public void resize(int newWidth, int newHeight) {
		if (viewport != null) {
			viewport.update(newWidth, newHeight);
		}
	}

	public void pause() {
	};

	public void resume() {
	};

	public void removeState() {
	};

	public void setState() {
	};

}
