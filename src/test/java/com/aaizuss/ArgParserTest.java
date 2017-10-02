package com.aaizuss;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ArgParserTest {

    private ArgParser argParser;

    private String customPath = System.getProperty("user.dir") + "/fun-stuff/";
    private String defaultDirectory = System.getProperty("user.dir") + "/public/";
    private String[] args = new String[] {"-p", "9090", "-d", customPath};
    private String[] portArg = new String[] {"-p", "5050"};
    private String[] invalidPortArg = new String[] {"-p", "nope"};

    @Before
    public void setUp() throws Exception {
        argParser = new ArgParser();
    }

    @Test
    public void testDefaultPort() throws Exception {
        assertEquals(5000, argParser.getPort());
    }

    @Test
    public void testInvalidPort() throws Exception {
        argParser.parseArguments(invalidPortArg);
        assertEquals(5000, argParser.getPort());
    }

    @Test
    public void testOnlyPortArg() throws Exception {
        argParser.parseArguments(portArg);
        assertEquals(5050, argParser.getPort());
        assertEquals(defaultDirectory, argParser.getDirectory());
    }

    @Test
    public void testDefaultDirectory() throws Exception {
        assertEquals(defaultDirectory, argParser.getDirectory());
    }

    @Test
    public void testPortFromArgs() throws Exception {
        argParser.parseArguments(args);
        assertEquals(9090, argParser.getPort());
    }

    @Test
    public void testDirectoryFromArgs() throws Exception {
        argParser.parseArguments(args);
        assertEquals(customPath, argParser.getDirectory());
    }

    @Test
    public void testDirectoryAndPortFromArgs() throws Exception {
        argParser.parseArguments(args);
        assertEquals(9090, argParser.getPort());
        assertEquals(customPath, argParser.getDirectory());
    }

    @Test
    public void testPrintArguments() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        argParser.printConfig();
        String expected = "Server listening on port: 5000\nDirectory Path: " + defaultDirectory + "\n";
        assertEquals(expected, output.toString());
    }

    @Test
    public void testPrintCustomArguments() throws Exception {
        argParser.parseArguments(args);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        argParser.printConfig();
        String expected = "Server listening on port: 9090\nDirectory Path: " + customPath + "\n";
        assertEquals(expected, output.toString());
    }
}
