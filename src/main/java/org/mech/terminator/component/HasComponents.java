package org.mech.terminator.component;

public interface HasComponents {
	void addComponent(Component component);
	int getComponentCount();
	Component getComponent(int index);
}
