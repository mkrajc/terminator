package org.mech.terminator.component;

import java.awt.Color;
import org.mech.terminator.ITerminal;
import org.mech.terminator.TerminalAppearance;
import org.mech.terminator.TerminalRectangleWrapper;
import org.mech.terminator.geometry.Dimension;
import org.mech.terminator.geometry.Position;
import org.mech.terminator.geometry.Rectangle;

public class Border extends Component {

	public static final int TOP = 1;
	public static final int BOTTOM = 1 << 2;
	public static final int LEFT = 1 << 4;
	public static final int RIGHT = 1 << 8;

	private Component component;
	private Dimension componentSize;
	private Color color = TerminalAppearance.DEFAULT_BORDER_COLOR;

	private int borders = TOP | LEFT | RIGHT | BOTTOM;

	public Border() {}

	public Border(final Component component) {
		this.component = component;
	}

	public Component getComponent() {
		return component;
	}

	public void setComponent(final Component component) {
		this.component = component;
	}

	private void updateComponentSize() {
		int sizex = 0, sizey = 0;

		if (isTop()) {
			sizey--;
		}
		if (isBottom()) {
			sizey--;
		}
		if (isLeft()) {
			sizex--;
		}
		if (isRight()) {
			sizex--;
		}

		componentSize = getSize().addXY(sizex, sizey);
	}

	public void setBorders(final int borders) {
		this.borders = borders;
	}

	@Override
	public void onRender(final ITerminal terminal) {
		terminal.bg(Color.GREEN);

		final TerminalRectangleWrapper wrapper = new TerminalRectangleWrapper(getRectangle(), terminal);
		wrapper.bg(TerminalAppearance.DEFAULT_BG_COLOR);

//		component.render(wrapper);
	}

	private Rectangle getRectangle() {
		int x = 0, y = 0;

		if (isTop()) {
			y++;
		}

		if (isLeft()) {
			x++;
		}

		final Rectangle rectangle = new Rectangle(Position.at(x, y), getComponentSize());
		return rectangle;
	}

	public boolean isTop() {
		return (borders & TOP) != 0;
	}

	public boolean isBottom() {
		return (borders & BOTTOM) != 0;
	}

	public boolean isRight() {
		return (borders & RIGHT) != 0;
	}

	public boolean isLeft() {
		return (borders & LEFT) != 0;
	}

	@Override
	public void setSize(final Dimension size) {
		super.setSize(size);
		updateComponentSize();
		component.setSize(getComponentSize());
	}

	private Dimension getComponentSize() {
		return componentSize;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(final Color color) {
		this.color = color;
	}

}
