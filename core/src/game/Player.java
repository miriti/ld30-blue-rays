package game;

import game.inventory.InventoryItem;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;

public class Player extends Mob {
	private float speed = 5;
	private PlayerAnim playerAnim;
	private InventoryItem[] inventory = new InventoryItem[3];
	private boolean actionLock = false;
	private static Sound sndInvMove;
	private static Sound sndPickup = null;
	private static Player instance;

	public Player() {
		instance = this;
		name = "You";
		playerAnim = new PlayerAnim();
		playerAnim.setPosition(-playerAnim.getWidth() / 2, -3);
		inventory[0] = null;
		inventory[1] = null;
		inventory[2] = null;
		addActor(playerAnim);
	}

	public static Player getInstance() {
		return instance;
	}

	public InventoryItem[] getInventory() {
		return inventory;
	}

	/**
	 * Getting a list of interactable objects near the player
	 * 
	 * @return
	 */
	protected ArrayList<SurfaceObject> getInteractableObjects() {
		ArrayList<SurfaceObject> result = new ArrayList<SurfaceObject>();
		if (planet != null) {
			for (int i = 0; i < planet.getSurfaceObjects().size(); i++) {
				SurfaceObject obj = planet.getSurfaceObjects().get(i);
				if (obj.interactable) {
					if ((getSurfaceX() > obj.getSurfaceX() - obj.getWidth() / 2)
							&& (getSurfaceX() < obj.getSurfaceX()
									+ obj.getWidth() / 2)) {
						result.add(obj);
					}
				}
			}
		}
		return result;
	}

	/**
	 * Take the item
	 * 
	 * @param item
	 */
	public void take(InventoryItem item) {
		if (item != null) {
			say(item.name + ". Can be useful.");
			item.setPlanet(null, true);
			if (sndPickup == null) {
				sndPickup = Gdx.audio.newSound(Gdx.files
						.internal("s/pickup.wav"));
			}
			sndPickup.play();
		} else {
			if ((inventory[0] != null) || (inventory[1] != null)
					|| (inventory[2] != null)) {
				if (sndInvMove == null) {
					sndInvMove = Gdx.audio.newSound(Gdx.files
							.internal("s/inv_move.wav"));
				}
				sndInvMove.play();
			}
		}
		if (inventory[2] != null) {
			boolean used = false;
			ArrayList<SurfaceObject> objs = getInteractableObjects();
			for (SurfaceObject o : objs) {
				if (o.use(inventory[2])) {
					used = true;
					break;
				}
			}
			if (!used) {
				planet.putObject(inventory[2], getSurfaceX(), true);
			}
		}
		inventory[2] = inventory[1];
		inventory[1] = inventory[0];

		inventory[0] = item;
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		playerAnim.walk = false;

		if (planet != null) {
			if (Input.isLeft()) {
				setScaleX(-1);
				setSurfaceX(getSurfaceX() - speed);
				playerAnim.walk = true;
			}

			if (Input.isRight()) {
				setScaleX(1);
				setSurfaceX(getSurfaceX() + speed);
				playerAnim.walk = true;
			}

			if (Input.isKeyPressed(Keys.SPACE)) {
				if (!actionLock) {
					boolean interacted = false;
					actionLock = true;
					ArrayList<SurfaceObject> objs = getInteractableObjects();

					if (objs.size() > 0) {
						for (int i = 0; i < objs.size(); i++) {
							SurfaceObject obj = objs.get(i);
							if ((obj.takeable)
									&& (obj instanceof InventoryItem)) {
								take((InventoryItem) obj);
								interacted = true;
								break;
							} else {
								if (obj.interactable) {
									interacted = obj.interact(this);
									break;
								}
							}
						}
					}

					if (!interacted) {
						take(null);
					}
				}
			} else {
				actionLock = false;
			}
		}
	}
}
