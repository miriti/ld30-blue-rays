package game.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Battery extends InventoryItem {
	public Battery() {
		name = "A battery";
		init(new Image(new Texture(Gdx.files.internal("i/battery.png"))),
				new Texture(Gdx.files.internal("i/battery-icon.png")));
	}
}
