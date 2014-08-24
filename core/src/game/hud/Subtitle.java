package game.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Subtitle extends Actor {
	private static BitmapFont font;
	private float timePassed = 0;
	private float timeTotal;

	public boolean done = false;

	private static BitmapFont getFont() {
		if (font == null) {
			font = new BitmapFont(Gdx.files.internal("f/font.fnt"));
		}

		return font;
	}

	private String text;

	public Subtitle(String text) {
		this.text = text;
		this.timeTotal = (text.length() * 150) / 1000;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		getFont().setColor(0, 0, 0, 1);
		getFont().draw(batch, text, getX() + 5, getY() + 5);
		getFont().setColor(1, 1, 1, 1);
		getFont().draw(batch, text, getX(), getY());
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		if (timePassed > timeTotal) {
			done = true;
			remove();
		} else {
			timePassed += delta;
		}
	}
}
