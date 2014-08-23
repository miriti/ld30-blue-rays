package io.github.miriti.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import io.github.miriti.Entry;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1024;
		config.height = 600;
		config.resizable = false;
		config.title = "%GAME_NAME_HERE%"; // TODO Game name in title
		new LwjglApplication(Entry.getInstance(), config);
	}
}
