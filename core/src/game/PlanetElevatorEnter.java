package game;

import game.hud.HUD;
import game.inventory.Battery;
import game.inventory.InventoryItem;
import game.planets.Planet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class PlanetElevatorEnter extends SurfaceObject {
	private Image image;
	public Planet source;
	public Planet destination;
	public PlanetElevator elevator;
	public PlanetElevatorEnter exit;
	private static Sound snd = null;

	public PlanetElevatorEnter(Planet source, Planet dest, PlanetElevator elev) {
		this.source = source;
		this.destination = dest;
		this.elevator = elev;
		this.takeable = false;
		this.interactable = true;

		image = new Image(new Texture(Gdx.files.internal("i/elevator.png")));
		image.setPosition(-image.getWidth() / 2, 0);
		addActor(image);

		setSize(image.getWidth(), image.getHeight());
	}

	@Override
	public boolean use(InventoryItem item) {
		if (item instanceof Battery) {
			elevator.setOperates(true);
			HUD.getInstance().say("The elevator is back on");
			return true;
		}

		return super.use(item);
	}

	@Override
	public boolean interact(Player with) {
		if (elevator.isOperates()) {
			with.elevatorEnter = this;
			with.planet.removeObject(with);
			with.planet = null;
			if (snd == null) {
				snd = Gdx.audio.newSound(Gdx.files.internal("s/elevator.wav"));
			}
			snd.play();
			return true;
		} else {
			HUD.getInstance().say(
					"The elevator is out of order due to "
							+ elevator.outOfOrderMessage);
			return false;
		}
	}
}
