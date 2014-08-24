package game.decor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import game.Player;
import game.SurfaceObject;

public class Tree extends SurfaceObject {
	public Tree() {
		Image treeImage = new Image(new Texture(
				Gdx.files.internal("i/tree.png")));
		treeImage.setPosition(-treeImage.getWidth() / 2, -3);
		addActor(treeImage);
	}

	@Override
	public boolean interact(Player with) {
		return false;
	}
}
