package game.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Wire extends InventoryItem {
	public Wire() {
		name = "A piece of wire";
		init(new Image(new Texture(Gdx.files.internal("i/wire.png"))),
				new Texture(Gdx.files.internal("i/wire-icon.png")));
	}
}
