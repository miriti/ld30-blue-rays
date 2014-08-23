package game;

import game.planets.Planet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class PlanetElevatorEnter extends SurfaceObject {
	private Image image;
	public Planet source;
	public Planet destination;
	public PlanetElevator elevator;
	public PlanetElevatorEnter exit;
	public float doorWidth;

	public PlanetElevatorEnter(Planet source, Planet dest, PlanetElevator elev) {
		this.source = source;
		this.destination = dest;
		this.elevator = elev;

		image = new Image(new Texture(Gdx.files.internal("i/elevator.png")));
		image.setPosition(-image.getWidth() / 2, 0);
		addActor(image);

		doorWidth = image.getWidth();
	}
}
