package org.mech.terminator.command;

import org.mech.terminator.Terminal;
import org.mech.terminator.geometry.Position;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

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
            final BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream(), Charset
                    .forName("ISO-8859-2")));

            String line;
            while ((line = reader.readLine()) != null) {
                for (int col = 0; col < line.length(); col++) {
                    char c = line.charAt(col);

                    System.out.println((int) c + " " + c);

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
