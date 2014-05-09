package org.mech.terminator.component;

import org.mech.terminator.geometry.Dimension;
import org.mech.terminator.geometry.Position;

public class HorizontalPanel extends LinePanel {

	public HorizontalPanel(final Dimension size) {
		super(size);
	}

	public HorizontalPanel() {}

	@Override
	protected Position getPosition(final int lastPosition) {
		return Position.at(lastPosition, 0);
	}

	@Override
	protected Dimension getDimension(final int size) {
		return Dimension.of(size, getSize().height);
	}

	@Override
	protected int getLimitSize() {
		return getSize().width;
	}

	@Override
	protected Integer getPreferedSize(final Component cw) {
		return cw.getPrefferedSize() == null ? null : cw.getPrefferedSize().getWidth();
	}

}
