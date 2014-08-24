package game.hud;

import game.Player;
import game.inventory.InventoryItem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Inventory extends Actor {
	private Texture background;

	public Inventory() {
		background = new Texture(Gdx.files.internal("i/inventory.png"));
		setSize(background.getWidth(), getHeight());
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);

		batch.draw(background, getX(), getY());

		for (int i = 0; i < 3; i++) {
			InventoryItem item = Player.getInstance().getInventory()[i];
			if (item != null) {
				batch.draw(item.getIcon(), getX() + 40 + 225 * i, getY() + 40);
			}
		}
	}

	@Override
	public void act(float delta) {
		super.act(delta);
	}
}
