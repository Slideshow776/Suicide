package no.sandramoen.suicide.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import no.sandramoen.suicide.SuicideGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Suicide?";
		int scale = 4; // resolution of lg g4 is 1440 x 2560 (in portrait mode)
		config.width = (int)1440 / scale;
		config.height = (int)2560 / scale;
		config.resizable = false;

		new LwjglApplication(new SuicideGame(), config);
		/*new LwjglApplication(new Launcher(), config);*/
	}
}
