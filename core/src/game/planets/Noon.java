package game.planets;

import game.inventory.Axe;
import game.inventory.Battery;

public class Noon extends Planet {
	public Noon() {
		initPlanet(200, "planet2", false);
		putObject(new Battery(), -200, true);
		putObject(new Axe(), -400, true);
		name = "Noon";
		planetType = "satellite";
	}
}
