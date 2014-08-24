package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class Input {
	private static boolean[] pressedKeys = new boolean[256];

	public static void init() {
		Gdx.input.setInputProcessor(new InputProcessor() {

			@Override
			public boolean touchUp(int screenX, int screenY, int pointer,
					int button) {
				return false;
			}

			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				return false;
			}

			@Override
			public boolean touchDown(int screenX, int screenY, int pointer,
					int button) {
				return false;
			}

			@Override
			public boolean scrolled(int amount) {
				return false;
			}

			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				return false;
			}

			@Override
			public boolean keyUp(int keycode) {
				pressedKeys[keycode] = false;
				return false;
			}

			@Override
			public boolean keyTyped(char character) {
				return false;
			}

			@Override
			public boolean keyDown(int keycode) {
				pressedKeys[keycode] = true;
				return false;
			}
		});
	}

	public static boolean isKeyPressed(int code) {
		return pressedKeys[code];
	}

	public static boolean isUp() {
		return pressedKeys[Keys.UP] || pressedKeys[Keys.W];
	}

	public static boolean isDown() {
		return pressedKeys[Keys.DOWN] || pressedKeys[Keys.S];
	}

	public static boolean isLeft() {
		return pressedKeys[Keys.LEFT] || pressedKeys[Keys.A];
	}

	public static boolean isRight() {
		return pressedKeys[Keys.RIGHT] || pressedKeys[Keys.D];
	}
}
