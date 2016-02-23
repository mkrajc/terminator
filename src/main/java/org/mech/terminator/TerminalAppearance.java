package org.mech.terminator;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

public class TerminalAppearance {
    public static final int SIZE = 11;
    public static final Font DEFAULT_NORMAL_FONT = createDefaultNormalFont();
    public static final Font DEFAULT_BOLD_FONT = createDefaultBoldFont();
    public static final Color DEFAULT_BORDER_COLOR = Color.GREEN;
    public static final Color DEFAULT_BG_COLOR = Color.BLACK;
    public static final Color DEFAULT_FG_COLOR = Color.GRAY;

    private static Font createDefaultNormalFont() {
        if (System.getProperty("os.name", "").toLowerCase().indexOf("win") >= 0)
            return new Font("Courier New", Font.PLAIN, SIZE);
        else
            return new Font("Monospaced", Font.PLAIN, SIZE);
    }

    private static Font createDefaultBoldFont() {
        if (System.getProperty("os.name", "").toLowerCase().indexOf("win") >= 0)
            return new Font("Courier New", Font.BOLD, SIZE);
        else
            return new Font("Monospaced", Font.BOLD, SIZE);
    }

    public static void main(String[] args) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontFamilies = ge.getAvailableFontFamilyNames();

        for (String g : fontFamilies) {
            System.out.println(g);
        }
    }
}

