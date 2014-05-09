package org.mech.terminator.component;

import org.mech.terminator.TerminalRectangleWrapper;
import org.mech.terminator.geometry.Dimension;
import org.mech.terminator.geometry.Position;
import org.mech.terminator.geometry.Rectangle;
import org.mech.terminator.screen.Screen;

public class Dialog extends SimplePanel {

	public void show() {
		Screen.setDialog(this);
	}

	public void hide() {
		Screen.setDialog(null);
	}

	public void setVisible(final boolean showOrHide) {
		if (showOrHide) {
			show();
		} else {
			hide();
		}
	}

	@Override
	public void render() {
		final Dimension size = getSize();
		final Dimension termDim = terminal.getSize().toDimension();

		final int x = (termDim.width - size.width) / 2;
		final int y = (termDim.height - size.height) / 2;

		final TerminalRectangleWrapper newTerminal = new TerminalRectangleWrapper(new Rectangle(Position.at(x, y), getSize()), terminal);
		onRender(newTerminal);

	}

}
