package game.decor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Star extends Actor {
	private Texture starTexture;

	private float phase;

	public Star() {
		starTexture = new Texture(Gdx.files.internal("i/star.png"));
		phase = (float) (Math.random() * 500);
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		setRotation((float) (Math.sin(phase)));
		phase += 0.1f;

		setScale(0.2f + (float) (Math.cos(phase)));
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(starTexture, getX() - starTexture.getWidth() / 2, getY()
				- starTexture.getHeight() / 2, starTexture.getWidth() / 2,
				starTexture.getHeight() / 2, starTexture.getWidth(),
				starTexture.getHeight(), 1, 1, getRotation(), 0, 0,
				starTexture.getWidth(), starTexture.getHeight(), false, false);
	}
}
