package org.mech.terminator.component;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import org.mech.terminator.ITerminal;
import org.mech.terminator.TerminalRectangleWrapper;
import org.mech.terminator.geometry.Dimension;
import org.mech.terminator.geometry.Rectangle;
import org.mech.terminator.input.InputListener;

public abstract class Component {

	public static Component focused;

	private Color backgroundColor;

	private Dimension prefferedSize;
	private Dimension propagatedSize;
	private Dimension size = Dimension.of(0, 0);
	private Component parent;

	protected ITerminal terminal;
	protected Rectangle rectangle;

	private List<InputListener> inputListeners;

	public void propagateTerminal(final ITerminal terminal) {
		this.propagatedSize = terminal.getSize().toDimension();
		this.rectangle = terminal.getSize().toRectangle();

		if (prefferedSize != null) {
			setSize(propagatedSize.intersectWith(prefferedSize));
		} else {
			setSize(propagatedSize);
		}

		// TODO alignment of rectangle
		this.terminal = new TerminalRectangleWrapper(this.size.toRectangle(), terminal);

		System.out.println(this + " propagate terminal : " + rectangle + ", term=" + terminal);
		doPropagateTerminal(this.terminal);
	}
	
	public void doPropagateTerminal(final ITerminal terminal) {}

	public Dimension getPrefferedSize() {
		return prefferedSize;
	}

	public Dimension getSize() {
		return size;
	}

	public Component getParent() {
		return parent;
	}

	public void setPrefferedSize(final Dimension prefferedSize) {
		//		System.out.println(getClass() + "setting size" + prefferedSize);
		this.prefferedSize = prefferedSize;
	}

	public void setSize(final Dimension size) {
		this.size = size;
	}

	public void setParent(final Component parent) {
		this.parent = parent;
	}

	public void addInputListener(final InputListener l) {
		if (inputListeners == null) {
			inputListeners = new ArrayList<InputListener>(2);
		}

		inputListeners.add(l);
	}

	public void dispatchInput(final KeyEvent e) {
		if (inputListeners != null) {
			for (final InputListener l : inputListeners) {
				l.handleInput(e);
			}
		} else {
			System.out.println(getClass().getSimpleName() + " dispatching input but no listeners.");
		}
	}

	public void focus() {
		System.out.println(this + " focused");
		if (focused != null) {
			focused.unfocus();
		}
		focused = this;
	}

	public void unfocus() {
		System.out.println(this + " unfocused");
	}

	public void render() {
		if (getBackgroundColor() != null) {
			terminal.bg(getBackgroundColor());
		}
		onRender(terminal);
	}

	public abstract void onRender(ITerminal terminal);

	public boolean isOrphan() {
		return parent == null;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(final Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	protected void onDetach() {

	}

	protected void onAttach() {

	}

}
