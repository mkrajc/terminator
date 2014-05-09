package org.mech.terminator.screen;

import java.util.HashMap;
import java.util.Map;

public class ScreenManager {

	private Map<String, Screen> map = new HashMap<String, Screen>();
	private Screen lastScreen;

	public Screen createScreen(String id) {
		Screen screen = map.get(id);
		if (screen == null) {
			screen = new Screen();
			map.put(id, screen);
		}
		lastScreen = screen;
		return screen;
	}

	public Screen getScreen(String id) {
		return map.get(id);
	}

	public Screen getCurrentScreen() {
		return lastScreen;
	}

	public void repaint() {
		if (lastScreen != null) {
			lastScreen.repaint();
		}
	}

}
