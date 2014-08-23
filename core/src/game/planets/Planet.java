package game.planets;

import game.SurfaceObject;
import game.decor.Tree;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Planet extends Group {
	protected String name;

	private ArrayList<SurfaceObject> surfaceObjects = new ArrayList<SurfaceObject>();
	private float radius;
	private float surfaceLength;

	public ArrayList<SurfaceObject> getSurfaceObjects() {
		return surfaceObjects;
	}

	protected void initPlanet(float radius) {
		Image atmosphereImage = new Image(new Texture(
				Gdx.files.internal("i/atmosphere.png")));
		atmosphereImage.setSize((radius * 1.5f) * 2f, (radius * 1.5f) * 2f);
		atmosphereImage.setPosition(-(radius * 1.5f), -(radius * 1.5f));
		addActor(atmosphereImage);

		Image planetImage = new Image(new Texture(
				Gdx.files.internal("i/planet.png")));
		planetImage.setSize(radius * 2, radius * 2);
		planetImage.setPosition(-radius, -radius);
		addActor(planetImage);

		this.radius = radius;
		this.surfaceLength = (float) (2 * Math.PI * radius);

		int treeNum = (int) (2 + Math.floor(Math.random() * 6));

		for (int i = 0; i < treeNum; i++) {
			Tree tree = new Tree();
			putObject(tree, (float) (Math.random() * surfaceLength), true);
		}
	}

	public float getRadius() {
		return radius;
	}

	public float getSurfaceLength() {
		return surfaceLength;
	}

	/**
	 * Calculates world coordinates of the given ground (surface) position
	 * 
	 * @return
	 */
	public Vector2 surfaceXtoWorld(float sX) {
		double a = (sX / surfaceLength) * (2 * Math.PI);

		return new Vector2((float) (getX() + Math.sin(a) * radius),
				(float) (getY() + Math.cos(a) * radius));
	}

	/**
	 * Calculate position on the surface for distant point
	 * 
	 * @param cx
	 * @param cy
	 * @return
	 */
	public float calculateSurfacePosition(float cx, float cy) {
		double a = Math.PI / 2 + Math.atan2(getY() - cy, getX() - cx);
		return getSurfaceLength() * (float) (-a / (2 * Math.PI));
	}

	public Planet(float radius) {
		initPlanet(radius);
	}

	public Planet() {
		initPlanet(300);
	}

	/**
	 * Put object on the planet
	 * 
	 * @param obj
	 * @param pos
	 */
	public void putObject(SurfaceObject obj, float pos) {
		putObject(obj, pos, false);
	}

	/**
	 * 
	 * @param obj
	 * @param pos
	 * @param add
	 */
	public void putObject(SurfaceObject obj, float pos, boolean add) {
		surfaceObjects.add(obj);
		obj.setPlanet(this);
		if (add) {
			addActor(obj);
		}
		obj.setSurfaceX(pos);
	}

	/**
	 * Remove object from the planet
	 * 
	 * @param obj
	 */
	public void removeObject(SurfaceObject obj) {
		surfaceObjects.remove(obj);
	}
}
