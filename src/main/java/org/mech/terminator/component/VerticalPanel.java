package org.mech.terminator.component;

import org.mech.terminator.geometry.Dimension;
import org.mech.terminator.geometry.Position;

public class VerticalPanel extends LinePanel {

	public VerticalPanel(final Dimension size) {
		super(size);
	}

	public VerticalPanel() {}

	@Override
	protected Position getPosition(final int lastPosition) {
		return Position.at(0, lastPosition);
	}

	@Override
	protected Dimension getDimension(final int size) {
		return Dimension.of(getSize().width, size);
	}

	@Override
	protected int getLimitSize() {
		return getSize().height;
	}

	@Override
	protected Integer getPreferedSize(final Component cw) {
		return cw.getPrefferedSize() == null ? null : cw.getPrefferedSize().getHeight();
	}
}
