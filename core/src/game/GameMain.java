package game;

import java.util.ArrayList;

import game.decor.StarSky;
import game.decor.Sun;
import game.hud.HUD;
import game.planets.Cradle;
import game.planets.Heli;
import game.planets.Noon;
import game.planets.Planet;
import io.github.miriti.base.State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameMain extends State {

	private Player player;
	private HUD hud;
	private Music music;

	public GameMain() {
		super();

		music = Gdx.audio.newMusic(Gdx.files.internal("m/loop.ogg"));
		music.setLooping(true);
		music.setVolume(0.6f);
		music.play();

		hud = new HUD();

		player = new Player();

		ArrayList<Planet> planets = new ArrayList<Planet>();
		ArrayList<PlanetElevator> elevators = new ArrayList<PlanetElevator>();

		// Create Cradle
		Cradle cradle = new Cradle();
		cradle.setPosition(6000, 0);
		cradle.putObject(player, cradle.getSurfaceLength() * 0.5f);
		planets.add(cradle);

		// Create Noon
		Noon noon = new Noon();
		noon.setPosition(6400, 2000);
		planets.add(noon);

		// Elevator
		PlanetElevator noonElevator = new PlanetElevator();
		noonElevator.connectPlanets(cradle, noon);
		elevators.add(noonElevator);

		// Heli
		Heli heli = new Heli();
		heli.setPosition(-5000, -10000);
		planets.add(heli);

		// Cradle Heli elevator
		PlanetElevator heliElivator = new PlanetElevator();
		heliElivator.connectPlanets(cradle, heli);
		heliElivator.setOperates(false, "missing battery");
		elevators.add(heliElivator);

		float minx = Float.MAX_VALUE;
		float miny = Float.MAX_VALUE;
		float maxx = Float.MIN_VALUE;
		float maxy = Float.MIN_VALUE;

		for (Planet p : planets) {
			if (p.getX() < minx) {
				minx = p.getX();
			}

			if (p.getX() > maxx) {
				maxx = p.getX();
			}

			if (p.getY() < miny) {
				miny = p.getY();
			}

			if (p.getY() > maxy) {
				maxy = p.getY();
			}
		}

		addActor(new StarSky(minx, miny, maxx, maxy));
		addActor(new Sun());

		for (PlanetElevator e : elevators) {
			addActor(e);
		}

		for (Planet p : planets) {
			addActor(p);
		}

		addActor(player);
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
