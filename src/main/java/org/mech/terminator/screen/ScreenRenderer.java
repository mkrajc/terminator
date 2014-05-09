package org.mech.terminator.screen;

public abstract class ScreenRenderer {

	private ScreenManager screenManager = new ScreenManager();

	private boolean initialized = false;

	protected abstract void doInitializeScreen(Screen screen);
	protected abstract String getScreenKey();

	protected Screen screen;

	public void init() {
		screen = screenManager.createScreen(getScreenKey());
		doInitializeScreen(screen);
		initialized = true;
	}

	public void render() {
		if (!initialized) {
			init();
		}

		screen.repaint();
	}
	public Screen getScreen() {
		return screen;
	}
}
