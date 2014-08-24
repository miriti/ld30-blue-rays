package io.github.miriti;

import game.GameMain;
import game.Input;
import io.github.miriti.base.State;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

/**
 * Game entry point
 * 
 * @author Michael (KEFIR) Miriti <michael@miriti.ru>
 * @date Aug 23, 2014
 *
 */
public class Entry extends ApplicationAdapter {

	private static Entry instance = null;

	public static Entry getInstance() {
		if (instance == null) {
			instance = new Entry();
		}

		return instance;
	}

	private State _currentState = null;
	private ShapeRenderer debugRender;

	/**
	 * Get the current state
	 * 
	 * @return
	 */
	public State getCurrentState() {
		return _currentState;
	}

	/**
	 * Set current game state
	 * 
	 * @param state
	 * @return
	 */
	public State setCurrentState(State state) {
		if (_currentState != null) {
			_currentState.removeState();
		}
		_currentState = state;

		if (_currentState != null) {
			_currentState.setState();
		}

		return _currentState;
	}

	@Override
	public void create() {
		Input.init();
		setCurrentState(new GameMain());
		debugRender = new ShapeRenderer();
	}

	@SuppressWarnings("unused")
	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (_currentState != null) {
			_currentState.update(Gdx.graphics.getDeltaTime());
			_currentState.draw();

			if (false) { // TODO Debug render
				debugRender.setProjectionMatrix(_currentState.getStage()
						.getCamera().combined);
				debugRender.begin(ShapeType.Line);
				debugRender.setColor(1, 0, 0, 1);
				debugRender.line(0, 0, 100, 0);
				debugRender.setColor(0, 1, 0, 1);
				debugRender.line(0, 0, 0, 100);
				debugRender.end();
			}
		}
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		if (_currentState != null) {
			_currentState.resize(width, height);
		}
	}

	@Override
	public void pause() {
		super.pause();
		if (_currentState != null) {
			_currentState.pause();
		}
	}

	@Override
	public void resume() {
		super.resume();
		if (_currentState != null) {
			_currentState.resume();
		}
	}
}
