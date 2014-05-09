package org.mech.terminator.component;

import org.mech.terminator.ITerminal;
import org.mech.terminator.geometry.Dimension;

public class SimplePanel extends Panel {

	public SimplePanel(final Dimension size) {
		super(size);
	}

	public SimplePanel() {}

	@Override
	public void addComponent(final Component component) {
		if (getComponentCount() > 1) {
			throw new IllegalAccessError("DefaultLayout cannot contains more than 1 component");
		} else {
			super.addComponent(component);
		}
	}

	@Override
	protected void doLayout() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doPropagateTerminal(final ITerminal terminal) {
		if (components.size() == 1) {
			components.get(0).propagateTerminal(terminal);
		}
	}

	//	@Override
	//	public void doLayout() {
	////		if (components.size() == 1) {
	////			final ComponentWrapper cw = components.get(0);
	////			cw.setRectangle(new Rectangle(Position.at(0, 0), getSize()));
	////		}
	//	}

}
