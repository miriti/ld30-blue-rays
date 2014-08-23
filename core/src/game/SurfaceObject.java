package game;

import game.planets.Planet;

import com.badlogic.gdx.scenes.scene2d.Group;

public class SurfaceObject extends Group {
	protected float surfaceX = 0;
	protected Planet planet = null;

	public float getSurfaceX() {
		return surfaceX;
	}

	public void setSurfaceX(float value) {
		surfaceX = value;

		if (planet != null) {
			if (surfaceX < 0) {
				surfaceX = planet.getSurfaceLength() + surfaceX;
			}

			if (surfaceX > planet.getSurfaceLength()) {
				surfaceX = surfaceX - planet.getSurfaceLength();
			}

			float f = surfaceX / planet.getSurfaceLength();
			float a = (float) (f * (2 * Math.PI));
			
			float pX = (getParent() == planet ? 0 : planet.getX())
					+ (float) (Math.sin(a) * planet.getRadius());
			float pY = (getParent() == planet ? 0 : planet.getY())
					+ (float) (Math.cos(a) * planet.getRadius());

			setRotation(-(float) (a * (180 / Math.PI)));
			setPosition(pX, pY);
		}
	}

	public void setPlanet(Planet pl) {
		if (planet != null) {
			planet.removeObject(this);
		}
		planet = pl;
	}
}
