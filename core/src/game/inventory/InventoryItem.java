package game.inventory;

import game.Player;
import game.SurfaceObject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class InventoryItem extends SurfaceObject {
	private Texture icon;
	public String name;
	public String description;

	protected void init(Actor self, Texture icon) {
		this.icon = icon;
		this.interactable = true;
		this.takeable = true;
		self.setPosition(-self.getWidth() / 2, 0);
		addActor(self);
		setWidth(self.getWidth());
	}

	public Texture getIcon() {
		return icon;
	}

	@Override
	public boolean interact(Player with) {
		return false;
	}

	public void taken() {
	}
}
