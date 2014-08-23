package game;

import game.decor.Sun;
import game.hud.HUD;
import game.planets.Planet;
import io.github.miriti.base.State;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameMain extends State {

	private Player player;
	private Planet testPlanet;
	private Planet testPlanet2;
	private Planet testPlanet3;
	private HUD hud;

	public GameMain() {
		super();

		hud = new HUD();

		player = new Player();

		testPlanet = new Planet(500);
		testPlanet.setPosition(0, 20000);
		testPlanet.putObject(player, 0);

		testPlanet2 = new Planet(500);
		testPlanet2.setPosition(-15000, -3000);

		testPlanet3 = new Planet(2000);
		testPlanet3.setPosition(20000, -30000);

		PlanetElevator elevator = new PlanetElevator();
		elevator.connectPlanets(testPlanet, testPlanet2);

		PlanetElevator elevator2 = new PlanetElevator();
		elevator2.connectPlanets(testPlanet2, testPlanet3);

		PlanetElevator elevator3 = new PlanetElevator();
		elevator3.connectPlanets(testPlanet3, testPlanet);

		addActor(new Sun());

		addActor(testPlanet);
		addActor(testPlanet2);
		addActor(testPlanet3);

		addActor(player);

		addActor(elevator);
		addActor(elevator2);
		addActor(elevator3);

	}

	@Override
	public void update(float delta) {
		super.update(delta);

		stage.getCamera().position.set(player.getX(), player.getY(), 0);

		// Rotate camera to player's angle
		double a = -(player.getRotation() * (Math.PI / 180));
		stage.getCamera().up.set((float) Math.sin(a), (float) Math.cos(a), 0);

		if (Input.isKeyPressed(Keys.Z)) {
			((OrthographicCamera) stage.getCamera()).zoom = 30;
		} else {
			((OrthographicCamera) stage.getCamera()).zoom = 1;
		}

		hud.act(delta);
	}

	@Override
	public void draw() {
		super.draw();
		hud.render();
	}

}
