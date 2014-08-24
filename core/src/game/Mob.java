package game;

import game.elevators.PlanetElevatorEnter;
import game.hud.HUD;
import game.hud.Subtitle;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;

public class Mob extends SurfaceObject {
	public PlanetElevatorEnter elevatorEnter = null;

	protected String name = "Random Mob";

	private Vector2 animStart = null;
	private Vector2 animEnd = null;
	private float elevationTime = 3;
	private float elevationTimeCurrent = 0;
	private float initRotation;

	public Subtitle say(String str) {
		return HUD.getInstance().say(name + ": " + str);
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		if (elevatorEnter != null) {
			if (elevationTimeCurrent >= elevationTime) {
				HUD.getInstance().say("Welcome to the " + elevatorEnter.destination.planetType + " \"" + elevatorEnter.destination.name + "\"");
				elevatorEnter.destination.putObject(this,
						elevatorEnter.exit.getSurfaceX());
				elevatorEnter = null;
				elevationTimeCurrent = 0;
				animStart = null;
				animEnd = null;
			} else {
				if (animStart == null) {
					animStart = elevatorEnter.source
							.surfaceXtoWorld(getSurfaceX());
					initRotation = getRotation();
				}

				if (animEnd == null) {
					animEnd = elevatorEnter.destination
							.surfaceXtoWorld(elevatorEnter.exit.getSurfaceX());
				}

				setX(Interpolation.sine.apply(animStart.x, animEnd.x,
						elevationTimeCurrent / elevationTime));
				setY(Interpolation.sine.apply(animStart.y, animEnd.y,
						elevationTimeCurrent / elevationTime));
				setRotation(Interpolation.sine.apply(initRotation,
						initRotation + 180,
						(elevationTimeCurrent / elevationTime)));

				elevationTimeCurrent += delta;
			}
		}
	}

	@Override
	public boolean interact(Player with) {
		return false;
	}
}
