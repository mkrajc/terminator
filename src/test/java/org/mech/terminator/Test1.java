package org.mech.terminator;

import java.awt.Color;
import org.mech.terminator.component.Label;
import org.mech.terminator.geometry.Dimension;
import org.mech.terminator.swing.TerminalFrame;
import org.mech.terminator.swing.TerminalPanel;

public class Test1 {
	public static void main(final String[] args) {
		Terminal.INSTANCE = new Terminal(new TerminalSize(25, 80));

		final TerminalFrame frame = new TerminalFrame();
		final TerminalPanel panel = new TerminalPanel();

		frame.setTerminalPanel(panel);
		
		final Label t = new Label("test");
		t.setBackgroundColor(Color.yellow);
//		final Label t2 = new Label("test2");
//		t2.setBackgroundColor(Color.red);
		
//		final VerticalPanel panel2 = new VerticalPanel();
//		panel2.setBackgroundColor(Color.CYAN);
//		panel2.addComponent(t);
//		panel2.addComponent(t2);
		
//		panel.getDefaultScreen().getRoot().addComponent(panel2);
		
		t.setPrefferedSize(Dimension.of(10, 10));
		panel.getDefaultScreen().getRoot().addComponent(t);
		
		
//
//		final StackPanel vpanel = new StackPanel() {
//			@Override
//			public void onRender(final ITerminal terminal) {
//				terminal.bg(Color.RED);
//				super.onRender(terminal);
//			}
//		};
//
//		final Label test = new Label("Hello World!");
//		test.setPrefferedSize(Dimension.of(0, 1));
//
//		final Label test2 = new Label("Hello World 2 !");
//		test2.setPrefferedSize(Dimension.of(0, 1));
//
//		final Label test3 = new Label("Hello World 3 !");
//		test3.setPrefferedSize(Dimension.of(0, 1));
//		test3.setTextColor(Color.CYAN);
//
//		vpanel.addComponent("1x", test);
//		vpanel.addComponent("2x", test2);
//		vpanel.addComponent("3x", test3);
//
//		panel.getDefaultScreen().getRoot().addComponent(vpanel);
//		vpanel.focus();

		frame.showWindow();

		//		String property = System.getProperty("java.io.tmpdir");
		//
		//		File dir = new File(property);
		//
		//		String[] list = dir.list();
		//		int i = 0;
		//		for (String f : list) {
		//			i++;
		//			Label label = new Label(f);
		//			vpanel.addComponent(label);
		//			if (i == 25) {
		//				break;
		//			}
		//		}

		//		vpanel.addInputListener(new InputListener() {
		//
		//			private int s = 0;
		//
		//			@Override
		//			public void handleInput(KeyEvent e) {
		//				
		//				if (Inputs.isUpArrowKey(e)) {
		//					s = Math.max(0, s - 1);
		//				} else if (Inputs.isDownArrowKey(e)) {
		//					s = Math.min(vpanel.getComponentCount() - 1, s + 1);
		//				}
		//
		//				for (int i = 0; i < vpanel.getComponentCount(); i++) {
		//					Label component = (Label) vpanel.getComponent(i);
		//					component.setTextColor(Color.GRAY);
		//
		//					if (i == s) {
		//						component.setTextColor(Color.RED);
		//					}
		//				}
		//			}
		//		});

	}
}
