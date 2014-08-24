package game.planets;

import game.BatteryFactory;
import game.inventory.Wire;

public class Heli extends Planet {
	public Heli() {
		name = "Heli";
		initPlanet(1200, "planet", true);
		putObject(new BatteryFactory(), 500, true);
		putObject(new Wire(), 1500, true);
	}
}
