package org.mech.terminator.component;

import java.awt.Color;
import java.awt.event.KeyEvent;
import org.mech.terminator.geometry.Dimension;
import org.mech.terminator.input.InputListener;

public class StackPanel extends VerticalPanel {

	public static Color SELECTED = Color.BLUE;
	public static Color UNSELECTED = Color.YELLOW;

	private StackItem current;

	public StackPanel() {
		addInputListener(new InputListener() {

			@Override
			public void handleInput(final KeyEvent event) {

			}
		});
	}

	@Override
	public void focus() {
		super.focus();
		if (current == null && getComponentCount() > 0) {
			current = (StackItem) getComponent(0);
			current.select();
		}
	}

	public void addComponent(final String label, final Component component) {
		final StackItem stackItem = new StackItem(label, component);
//		stackItem.setPrefferedSize(Dimension.of(0, 1));
		addComponent(stackItem);
	}

	static class StackLabel extends Label {

		public StackLabel(final String label) {
			super(label);
		}

		public void select() {
			setBackgroundColor(SELECTED);
		}

		public void unselect() {
			setBackgroundColor(UNSELECTED);
		}
	}

	static class StackItem extends VerticalPanel {

		private final Component component;

		public StackItem(final String label, final Component component) {
			this.component = component;

			final StackLabel compLabel = new StackLabel(label);
			compLabel.setPrefferedSize(Dimension.of(0, 1));
			compLabel.setTextColor(Color.BLUE);
			compLabel.setBackgroundColor(Color.GRAY);

			super.addComponent(compLabel);
		}

		public void select() {
			setBackgroundColor(SELECTED);
			addComponent(component);
		}

		public void unselect() {
			setBackgroundColor(UNSELECTED);
			removeComponent(component);
		}
	}
}
