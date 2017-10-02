package com.aaizuss;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ArgParserTest {

    private static int DEFAULT_PORT = 5000;
    private static String DEFAULT_DIRECTORY = System.getProperty("user.dir") + "/public/";

    private String customPath = System.getProperty("user.dir") + "/test-directory/";

    private String[] args = new String[] {"-p", "9090", "-d", customPath};
    private String[] portArg = new String[] {"-p", "5050"};
    private String[] invalidPortArg = new String[] {"-p", "nope"};

    @Test
    public void testGetPortWithInvalidPortArgReturnsDefaultPort() throws Exception {
        assertEquals(5000, ArgParser.getPort(invalidPortArg, DEFAULT_PORT));
    }

    @Test
    public void testOnlyPortArg() throws Exception {
        assertEquals(5050, ArgParser.getPort(portArg, DEFAULT_PORT));
        assertEquals(DEFAULT_DIRECTORY, ArgParser.getDirectory(portArg, DEFAULT_DIRECTORY));
    }

    @Test
    public void testGetDirectoryWithNoDirectoryArgReturnsDefaultDirectory() throws Exception {
        assertEquals(DEFAULT_DIRECTORY, ArgParser.getDirectory(portArg, DEFAULT_DIRECTORY));
    }

    @Test
    public void testGetPortFromArgs() throws Exception {
        int port = ArgParser.getPort(args, DEFAULT_PORT);
        assertEquals(9090, port);
    }

    @Test
    public void testGetDirectoryFromArgs() throws Exception {
        assertEquals(customPath, ArgParser.getDirectory(args, DEFAULT_DIRECTORY));
    }


    @Test
    public void testPrintArguments() throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        ArgParser.printConfig(DEFAULT_PORT, DEFAULT_DIRECTORY);
        String expected = "Server listening on port: 5000\nDirectory Path: " + DEFAULT_DIRECTORY + "\n";
        assertEquals(expected, output.toString());
    }
}
