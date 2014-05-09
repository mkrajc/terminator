package org.mech.terminator.component;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.mech.terminator.ITerminal;
import org.mech.terminator.TerminalRectangleWrapper;
import org.mech.terminator.TerminalWrapper;
import org.mech.terminator.conf.SystemProperies;
import org.mech.terminator.geometry.Dimension;
import org.mech.terminator.geometry.Rectangle;

public abstract class Panel extends Component implements ComponentContainer {

	protected List<Component> components = new ArrayList<Component>(5);

	private final boolean debug = SystemProperies.ui_debug();

	public Panel(final Dimension size) {
		setSize(size);
		setPrefferedSize(size);
	}

	public Panel() {
		setSize(Dimension.of(0, 0));
	}

	@Override
	public void setSize(final Dimension size) {
		super.setSize(size);
	}

	public void insertComponent(final Component component, final int i) {
		component.setParent(this);
		components.add(i, component);

	}

	@Override
	public int getComponentCount() {
		return components.size();
	}

	public final void layout() {
		doLayout();

		for (final Iterator<Component> iterator = components.iterator(); iterator.hasNext();) {
			final Component cw = iterator.next();

//			cw.terminal = null;
//
//			if (cw.component instanceof Panel) {
//				final Panel l = (Panel) cw.component;
//				l.layout();
//			}
		}
	}

	public void removeComponent(final Component component) {
		if (containsComponent(component)) {
			component.setParent(null);
			components.remove(component);
//			component.onDetach();
		}

	}

	protected abstract void doLayout();

	@Override
	public void addComponent(final Component component) {
		insertComponent(component, getComponentCount());
	}

	@Override
	public Component getComponent(final int index) {
		return components.get(index);
	}

	public boolean containsComponent(final Component component) {
		for (final Component cw : components) {
			if (cw.equals(component)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void onRender(final ITerminal terminal) {

		for (final Iterator<Component> iterator = components.iterator(); iterator.hasNext();) {
			final Component cw = iterator.next();

			if (cw.terminal == null) {
				final ITerminal t = new TerminalRectangleWrapper(cw.rectangle, terminal);
				cw.terminal = this.debug ? new DebugTerminal(t) : t;
			}

//			printDebug(cw.terminal);

			cw.render(cw.terminal);
		}
	}

//	private void printDebug(final ITerminal iTerminal) {
//		final TerminalSize size = iTerminal.getSize();
//		if (debug) {
//			for (int i = 0; i < size.getLines(); i++) {
//				for (int j = 0; j < size.getColumns(); j++) {
//					iTerminal.bg(null, i, j);
//				}
//			}
//		}
//	}

	protected static class ComponentWrapper {
		private final Component component;
		private Rectangle rectangle;
		private ITerminal terminal;

		public ComponentWrapper(final Component component) {
			this.component = component;
		}

		void setRectangle(final Rectangle rectangle) {
			System.out.println("Component [" + component + "] set rec size to [" + rectangle + "]");
			this.rectangle = rectangle;
			this.component.setSize(rectangle.getSize());
		}

//		public Component getComponent() {
//			return component;
//		}

//		public ITerminal getTerminal() {
//			return terminal;
//		}

//		public void setTerminal(final ITerminal terminal) {
//			this.terminal = terminal;
//		}

//		public Rectangle getRectangle() {
//			return rectangle;
//		}

	}

	private static class DebugTerminal extends TerminalWrapper {
		private final Color color;

		public DebugTerminal(final ITerminal terminal) {
			super(terminal);
			final Random r = new Random(System.currentTimeMillis());
			color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
		}

		@Override
		public void put(final char c, final int line, final int column) {
			super.put(c, line, column);
		}

		@Override
		public void bg(final Color clr, final int line, final int column) {
			super.bg(clr == null ? color : clr, line, column);
		}

	}

}
