package game.decor;

import com.badlogic.gdx.scenes.scene2d.Group;

public class StarSky extends Group {
	protected void initSky(float minX, float minY, float maxX, float maxY,
			int starCount) {
		for (int i = 0; i < starCount; i++) {
			Star newStar = new Star();
			newStar.setPosition((float) (minX + Math.random() * (maxX - minX)),
					(float) (minY + Math.random() * (maxY - minY)));
			addActor(newStar);
		}
	}

	public StarSky(float minX, float minY, float maxX, float maxY) {
		initSky(minX, minY, maxX, maxY, 200);
	}
}
