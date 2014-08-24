package game.planets;

import game.inventory.Battery;

public class Noon extends Planet {
	public Noon() {
		initPlanet(200, "planet2", false);
		putObject(new Battery(), -300, true);
		name = "Noon";
		planetType = "satellite";
	}
}
