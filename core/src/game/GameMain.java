package game;

import game.decor.StarSky;
import game.decor.Sun;
import game.elevators.HeliElevator;
import game.elevators.NocturneElevator;
import game.elevators.PlanetElevator;
import game.elevators.WeerdoElevator;
import game.hud.HUD;
import game.planets.Cradle;
import game.planets.Heli;
import game.planets.Nocturne;
import game.planets.Noon;
import game.planets.Planet;
import game.planets.Weerdo;
import io.github.miriti.base.State;

import java.util.ArrayList;

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
		noon.setPosition(4500, 0);
		planets.add(noon);

		// Cradle -> Noon
		PlanetElevator noonElevator = new PlanetElevator(cradle, noon);
		elevators.add(noonElevator);

		// Heli
		Heli heli = new Heli();
		heli.setPosition(-5000, -10000);
		planets.add(heli);

		// Cradle -> Heli
		HeliElevator heliElivator = new HeliElevator(cradle, heli);
		elevators.add(heliElivator);

		// Weerdo
		Weerdo weerdo = new Weerdo();
		weerdo.setPosition(-7000, -6000);
		planets.add(weerdo);

		// Nocturne
		Nocturne nocturne = new Nocturne();
		nocturne.setPosition(8000, -2000);
		planets.add(nocturne);

		// Heli -> Weerdo
		NocturneElevator heliNocturneElevator = new NocturneElevator(heli,
				nocturne);
		elevators.add(heliNocturneElevator);

		// Weerdo -> Nocturne
		WeerdoElevator weerdoNocturneElevator = new WeerdoElevator(weerdo,
				nocturne);
		elevators.add(weerdoNocturneElevator);

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

		hud.say("You are on your home planet \"The Cradle\"", 30);
		hud.say("Use [A] [D] or [<-] [->] to move around", 30);
		hud.say("Use [space] to interact with objects", 30);
		hud.say("Hold [Z] to zoom out", 30);
		hud.say("Explore the planetary system using space elevators", 30);
	}

	@Override
	public void update(float delta) {
		super.update(delta);

		OrthographicCamera cam = (OrthographicCamera) stage.getCamera();

		cam.position.set(player.getX(), player.getY(), 0);

		// Rotate camera to player's angle
		double a = -(player.getRotation() * (Math.PI / 180));
		cam.up.set((float) Math.sin(a), (float) Math.cos(a), 0);

		if (Input.isKeyPressed(Keys.Z)) {
			if (cam.zoom < 30) {
				cam.zoom += 0.5f;
				if (cam.zoom > 30) {
					cam.zoom = 30;
				}
			}
		} else {
			if (cam.zoom > 1) {
				cam.zoom -= 1;
				if (cam.zoom < 1) {
					cam.zoom = 1;
				}
			}
		}

		hud.act(delta);
	}

	@Override
	public void draw() {
		super.draw();
		hud.render();
	}

}
