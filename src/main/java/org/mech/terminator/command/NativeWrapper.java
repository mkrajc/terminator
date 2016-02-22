package org.mech.terminator.command;

import org.mech.terminator.Terminal;
import org.mech.terminator.geometry.Position;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NativeWrapper extends CommandWrapper {
    private String command;

    public NativeWrapper(String command, Terminal terminal) {
        super(terminal);
        this.command = command;
    }

    @Override
    public void flush() throws IOException {
        Process p = Runtime.getRuntime().exec(command);
        try {
            p.waitFor();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));

            String line;
            int lineIndex = getPosition().y;
            while ((line = reader.readLine()) != null) {
                for (int col = 0; col < line.length(); col++) {
                    char c = line.charAt(col);
                    getTerminal().put(c, lineIndex, col);
                }
                lineIndex++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
