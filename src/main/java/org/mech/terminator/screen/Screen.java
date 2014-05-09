package org.mech.terminator.screen;

import java.awt.event.KeyEvent;
import org.mech.terminator.ITerminal;
import org.mech.terminator.Terminal;
import org.mech.terminator.component.Component;
import org.mech.terminator.component.ComponentContainer;
import org.mech.terminator.component.Dialog;
import org.mech.terminator.component.SimplePanel;

public class Screen {

	private final ITerminal terminal = Terminal.INSTANCE;
	private final SimplePanel root;
	private boolean layoutize = false;

	private static Dialog DIALOG;

	public Screen() {
		this.root = new SimplePanel(terminal.getSize().toDimension());
		if (Component.focused == null) {
			this.root.focus();
		}
	}

	public ComponentContainer getRoot() {
		return root;
	}

	public void repaint() {
		
		if (!layoutize) {
			root.propagateTerminal(terminal);
////			root.layout();
			layoutize = true;
		}

		root.render(terminal);

		if (DIALOG != null) {
			DIALOG.render(terminal);
		}

		terminal.flush();
	}

	public void dispatchInput(final KeyEvent e) {
		if (Component.focused != null) {
			Component.focused.dispatchInput(e);
			repaint();
		}
	}

	public void relayout() {
		this.layoutize = false;
	}

	public static Dialog getDialog() {
		return DIALOG;
	}

	public static void setDialog(final Dialog dialog) {
		DIALOG = dialog;
		if (dialog != null) {
			dialog.layout();
		}
	}

}
