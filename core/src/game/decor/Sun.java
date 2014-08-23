package game.decor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Sun extends Actor {
	private Texture sunTexure;

	public Sun() {
		sunTexure = new Texture(Gdx.files.internal("i/sun.png"));
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);

		batch.draw(sunTexure, getX() - 4096, getY() - 4096, 4096 * 2, 4096 * 2);
	}
}
