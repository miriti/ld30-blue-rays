package game.planets;

import game.decor.Tree;
import game.inventory.Wire;

public class Cradle extends Planet {
	public Cradle() {
		name = "Cradle";
		initPlanet(600, "planet", true);
		int treeNum = (int) (2 + Math.floor(Math.random() * 6));

		for (int i = 0; i < treeNum; i++) {
			Tree tree = new Tree();
			putObject(tree, (float) (Math.random() * getSurfaceLength()), true);
		}

		putObject(new Wire(), -400, true);
	}
}
