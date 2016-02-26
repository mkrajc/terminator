package org.mech.terminator;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class TerminalBufferTest {

    @Test
    public void testDeepCopy() throws Exception {
        TerminalBuffer tb = new TerminalBuffer(new TerminalSize(10,25));
        TerminalCharacter[][] buffer = tb.newBuffer();
        Assert.assertNull(buffer[0][0]);
        tb.copy(buffer);
        TerminalCharacter tc = buffer[0][0];
        Assert.assertNotSame(tc, tb.getChar(0,0));
        Assert.assertEquals(tc, tb.getChar(0,0));
        tb.put('x',0,0);
        Assert.assertNotSame(tc, tb.getChar(0,0));
        Assert.assertNotEquals(tc, tb.getChar(0,0));

    }

    @Test
    public void testCopy() throws Exception {
        TerminalBuffer tb = new TerminalBuffer(new TerminalSize(10,25));
        TerminalCharacter[][] data = tb.newBuffer();
        Assert.assertNull(data[0][0]);
        tb.copy(data);
        TerminalCharacter tc = data[0][0];
        Assert.assertNotNull(tc);
        tb.copy(data);
        Assert.assertSame(tc, data[0][0]);
    }

    @Test
    public void testCopyWithFlush() throws Exception {
        TerminalBuffer tb = new TerminalBuffer(new TerminalSize(10,25));
        TerminalCharacter[][] data = tb.newBuffer();
        Assert.assertNull(data[0][0]);
        tb.copy(data);
        TerminalCharacter tc = data[0][0];
        Assert.assertNotNull(tc);
        tb.flush();
        tb.copy(data);
        Assert.assertNotSame(tc, data[0][0]);
    }
}