package game;

import game.inventory.InventoryItem;
import game.planets.Planet;

import com.badlogic.gdx.scenes.scene2d.Group;

public abstract class SurfaceObject extends Group {
	protected float surfaceX = 0;
	public Planet planet = null;
	public boolean interactable = false;
	public boolean takeable = false;

	public abstract boolean interact(Player with);

	/**
	 * Use the item somehow
	 * 
	 * @param item
	 * @return
	 */
	public boolean use(InventoryItem item) {
		return false;
	}

	public float getSurfaceX() {
		return surfaceX;
	}

	public void setSurfaceX(float value) {
		surfaceX = value;

		if (planet != null) {
			while (surfaceX < 0) {
				surfaceX = planet.getSurfaceLength() + surfaceX;
			}

			while (surfaceX > planet.getSurfaceLength()) {
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
		setPlanet(pl, false);
	}

	public void setPlanet(Planet pl, boolean deattach) {
		if (planet != null) {
			if (deattach) {
				planet.removeActor(this);
			}
			planet.removeObject(this);
		}
		planet = pl;
	}
}
