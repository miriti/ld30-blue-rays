package game.decor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import game.Player;
import game.SurfaceObject;
import game.inventory.Axe;
import game.inventory.InventoryItem;
import game.inventory.Wood;

public class Tree extends SurfaceObject {
	public Tree() {
		this.interactable = true;
		Image treeImage = new Image(new Texture(
				Gdx.files.internal("i/tree.png")));
		treeImage.setPosition(-treeImage.getWidth() / 2, -3);
		setSize(treeImage.getWidth(), treeImage.getHeight());
		addActor(treeImage);
	}

	@Override
	public boolean use(InventoryItem item) {
		if (item instanceof Axe) {
			Wood newWood = new Wood();
			planet.putObject(newWood, getSurfaceX(), true);
			setPlanet(null, true);
			return true;
		}
		return super.use(item);
	}

	@Override
	public boolean interact(Player with) {
		return false;
	}
}
