package org.mech.terminator.conf;

public class SystemProperies {
	public static boolean get(String string) {
		return Boolean.valueOf(System.getProperty(string));
	}

	public static boolean ui_debug() {
		return get(Const.UI_DEBUG);
	}
}
