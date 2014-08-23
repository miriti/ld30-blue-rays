package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Player extends Mob {
	private float speed = 5;
	private Image playerImage;

	public Player() {
		name = "You";
		playerImage = new Image(new Texture(Gdx.files.internal("i/player.png")));
		playerImage.setPosition(-playerImage.getWidth() / 2, -3);
		addActor(playerImage);
		
		say("Where am I?!");
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		if (planet != null) {
			if (Input.isLeft()) {
				setScaleX(1);
				setSurfaceX(getSurfaceX() - speed);
			}

			if (Input.isRight()) {
				setScaleX(-1);
				setSurfaceX(getSurfaceX() + speed);
			}

			if (Input.isUp()) {
				for (int i = 0; i < planet.getSurfaceObjects().size(); i++) {
					SurfaceObject obj = planet.getSurfaceObjects().get(i);
					if (obj instanceof PlanetElevatorEnter) {
						elevatorEnter = (PlanetElevatorEnter) obj;

						if ((getSurfaceX() > elevatorEnter.getSurfaceX()
								- elevatorEnter.doorWidth / 2)
								&& (getSurfaceX() < elevatorEnter.getSurfaceX()
										+ elevatorEnter.doorWidth / 2)) {
							planet.removeObject(this);
							planet = null;
							break;
						} else {
							elevatorEnter = null;
						}
					}
				}
			}
		}
	}
}
