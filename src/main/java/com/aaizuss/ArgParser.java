package com.aaizuss;

import java.util.HashMap;

public class ArgParser {
    private static final int DEFAULT_PORT = 5000;
    private static final String DEFAULT_DIRECTORY = System.getProperty("user.dir") + "/public/";
    private static final String PORT_FLAG = "-p";
    private static final String DIR_FLAG = "-d";

    private int port = DEFAULT_PORT;
    private String directory = DEFAULT_DIRECTORY;

    public void parseArguments(String[] args) {
        HashMap<String, String> argsMap = makeArgsMap(args);
        setPort(argsMap);
        setDirectory(argsMap);
    }

    public int getPort() {
        return port;
    }

    public String getDirectory() {
        return directory;
    }

    private boolean isPortFlag(String arg) {
        return arg.equals(PORT_FLAG);
    }

    private boolean isDirFlag(String arg) {
        return arg.equals(DIR_FLAG);
    }

    private boolean isValidFlag(String flag) {
        return (isPortFlag(flag) || isDirFlag(flag));
    }

    private HashMap<String,String> makeArgsMap(String[] args) {
        HashMap<String,String> argsMap = new HashMap<>();

        for (int i = 0; i < args.length; i++) {
            if (isValidFlag(args[i])) {
                argsMap.put(args[i], args[i + 1]);
            }
        }
        return argsMap;
    }

    private void setPort(HashMap<String,String> argsMap) {
        int validPort = DEFAULT_PORT;
        String inputPort = argsMap.get(PORT_FLAG);

        if (inputPort != null) {
            validPort = portArgToInt(validPort, inputPort);
        }

        port = validPort;
    }

    // todo: check that directory path is valid?
    private void setDirectory(HashMap<String,String> argsMap) {
        String validDirectory = DEFAULT_DIRECTORY;
        String inputDirectory = argsMap.get(DIR_FLAG);

        if (inputDirectory != null) {
            validDirectory = inputDirectory;
        }
        directory = validDirectory;
    }

    private int portArgToInt(int validPort, String portArg) {
        try {
            return Integer.parseInt(portArg);
        } catch (NumberFormatException e) {
            System.out.println("Can't parse port argument `" + portArg + "`. Using default port.");
            return validPort;
        }
    }

    public void printConfig() {
        System.out.println("Server listening on port: " + getPort() + "\nDirectory Path: " + getDirectory());
    }
}
