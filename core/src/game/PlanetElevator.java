package game;

import game.planets.Planet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class PlanetElevator extends Group {

	private Planet planet1;
	private Planet planet2;
	public PlanetElevatorEnter elevatorEnter1;
	public PlanetElevatorEnter elevatorEnter2;

	public void connectPlanets(Planet planet1, Planet planet2) {

		this.planet1 = planet1;
		this.planet2 = planet2;

		elevatorEnter1 = new PlanetElevatorEnter(planet1, planet2, this);
		elevatorEnter2 = new PlanetElevatorEnter(planet2, planet1, this);

		planet1.putObject(elevatorEnter1, planet1.calculateSurfacePosition(
				planet2.getX(), planet2.getY()), true);
		planet2.putObject(elevatorEnter2, planet2.calculateSurfacePosition(
				planet1.getX(), planet1.getY()), true);

		elevatorEnter1.exit = elevatorEnter2;
		elevatorEnter2.exit = elevatorEnter1;

		Vector2 elevatorEnter1Pos = planet1.surfaceXtoWorld(elevatorEnter1
				.getSurfaceX());
		Vector2 elevatorEnter2Pos = planet2.surfaceXtoWorld(elevatorEnter2
				.getSurfaceX());

		setPosition(elevatorEnter1Pos.x, elevatorEnter1Pos.y);

		final Vector2 v = new Vector2(
				elevatorEnter1Pos.x - elevatorEnter2Pos.x, elevatorEnter1Pos.y
						- elevatorEnter2Pos.y);
		Group tube = new Group() {
			{
				Image tubeImage = new Image(new Texture(
						Gdx.files.internal("i/tube.png")));
				tubeImage.setSize(100, v.len());
				tubeImage.setPosition(-50, 0);
				addActor(tubeImage);
			}
		};

		tube.setRotation(90f + v.angle());
		addActor(tube);
	}

	public Planet getSource() {
		return this.planet1;
	}

	public Planet getDestination() {
		return this.planet2;
	}
}