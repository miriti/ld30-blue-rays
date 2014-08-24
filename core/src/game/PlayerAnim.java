package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PlayerAnim extends Actor {
	private Texture frameSheet;
	private Animation animation;
	private float currentTime;
	private TextureRegion currentFrame;
	public boolean walk = false;

	public PlayerAnim() {
		frameSheet = new Texture(Gdx.files.internal("i/player.png"));
		TextureRegion[][] frames = TextureRegion.split(frameSheet, 30, 60);

		animation = new Animation(0.10f, frames[0]);
		currentFrame = animation.getKeyFrame(0);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(currentFrame, getX() - currentFrame.getRegionWidth() / 2,
				getY());
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		if (walk) {
			currentFrame = animation.getKeyFrame(currentTime, true);
		} else {
			currentFrame = animation.getKeyFrame(0);
		}
		currentTime += delta;
	}
}
