package org.mech.terminator.command;

import org.mech.terminator.Terminal;
import org.mech.terminator.geometry.Position;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by alberto on 23/11/14.
 */
public class NativeWrapper implements AbstractCommandWrapper {
    private String command;
    private Terminal terminal;
    private String result;
    private Position pos;

    public NativeWrapper(String command, Terminal terminal) {
        this.command = command;
        this.terminal = terminal;
    }

    @Override
    public void setPosition(Position pos) {
        this.pos = pos;
    }

    public Position getPosition() {
        if (pos == null) {
            pos = new Position(0,0);
        }
        return pos;
    }

    @Override
    public void flush() throws IOException {
        Process p = Runtime.getRuntime().exec(command);
        try {
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            int lineIndex = getPosition().y;
            while ((line = reader.readLine()) != null) {
                for (int col = 0; col < line.length(); col++) {
                    char c = line.charAt(col);
                    terminal.put(c, lineIndex, col);
                }
                lineIndex++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
