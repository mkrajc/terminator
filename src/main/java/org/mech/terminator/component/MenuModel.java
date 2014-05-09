package org.mech.terminator.component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MenuModel {
	public interface MenuActionHandler {
		void onAction();
	}

	private List<Choice> choices = new ArrayList<MenuModel.Choice>();
	private int index = 0;

	public void add(String text, MenuActionHandler action) {
		choices.add(new Choice(text, action));
	}

	public void add(String text, MenuActionHandler action, Character character) {
		choices.add(new Choice(text, action, character));
	}

	public int getSelectedChoiceIndex() {
		return index;
	}

	public void next() {
		index = Math.min(choices.size() - 1, index + 1);
	}

	public void previous() {
		index = Math.max(0, index - 1);
	}

	public Choice getCurrentChoice() {
		return choices.get(index);
	}

	public Choice getChoice(int index) {
		return choices.get(index);
	}

	public int getChoiceCount() {
		return choices.size();
	}

	public void invokeMenuAction() {
		choices.get(index).getAction().onAction();
	}

	public void invokeMenuAction(char shortcut) {
		Iterator<Choice> iterator = choices.iterator();

		while (iterator.hasNext()) {
			MenuModel.Choice choice = (MenuModel.Choice) iterator.next();
			if (choice.getShortcutChar() != null && choice.getShortcutChar().charValue() == shortcut) {
				choice.getAction().onAction();
			}
		}
	}

	public final static class Choice {
		private String text;
		private Character shortcutChar;
		private MenuActionHandler action;

		public Choice(String text, MenuActionHandler action, Character shortcut) {
			this.text = text;
			this.action = action;
			this.shortcutChar = shortcut;
		}

		public Choice(String text, MenuActionHandler action) {
			this(text, action, null);
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public Character getShortcutChar() {
			return shortcutChar;
		}

		public void setShortcutChar(Character shortcutChar) {
			this.shortcutChar = shortcutChar;
		}

		public MenuActionHandler getAction() {
			return action;
		}

		public void setAction(MenuActionHandler action) {
			this.action = action;
		}
	}
}
