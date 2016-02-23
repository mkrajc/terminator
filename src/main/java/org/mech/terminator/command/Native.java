package org.mech.terminator.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.mech.terminator.Terminal;

public class Native extends Command {

    public Native(Terminal terminal) {
        super(terminal);
    }

    public void execute(final String command) throws IOException {
        Process p = Runtime.getRuntime().exec(command);
        try {
            p.waitFor();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream(), Charset
                    .forName("ISO-8859-2")));

            String line;
            while ((line = reader.readLine()) != null) {
                for (int col = 0; col < line.length(); col++) {
                    char c = line.charAt(col);
                    getTerminal().put(c, getPosition().y, getPosition().x);
                    moveNextColumn();
                }
                moveNextLine();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
