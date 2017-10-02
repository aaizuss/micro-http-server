package com.aaizuss;

import java.util.HashMap;

public class ArgParser {
    private static final String PORT_FLAG = "-p";
    private static final String DIR_FLAG = "-d";

    public static int getPort(String[] args, int defaultPort) {
        HashMap<String,String> argsMap = makeArgsMap(args);
        return validatePort(argsMap, defaultPort);
    }

    public static String getDirectory(String[] args, String defaultDirectory) {
        HashMap<String,String> argsMap = makeArgsMap(args);
        return validateDirectory(argsMap, defaultDirectory);
    }

    public static void printConfig(int port, String directory) {
        System.out.println("Server listening on port: " + port + "\nDirectory Path: " + directory);
    }

    private static boolean isPortFlag(String arg) {
        return arg.equals(PORT_FLAG);
    }

    private static boolean isDirFlag(String arg) {
        return arg.equals(DIR_FLAG);
    }

    private static boolean isValidFlag(String flag) {
        return (isPortFlag(flag) || isDirFlag(flag));
    }

    private static int portArgToInt(int validPort, String portArg) {
        try {
            return Integer.parseInt(portArg);
        } catch (NumberFormatException e) {
            System.out.println("Can't parse port argument `" + portArg + "`. Using default port.");
            return validPort;
        }
    }

    private static String validateDirectory(HashMap<String,String> argsMap, String defaultDirectory) {
        String validDirectory = defaultDirectory;
        String inputDirectory = argsMap.get(DIR_FLAG);

        if (inputDirectory != null) {
            validDirectory = inputDirectory;
        }
        return validDirectory;
    }

    private static int validatePort(HashMap<String,String> argsMap, int defaultPort) {
        int validPort = defaultPort;
        String inputPort = argsMap.get(PORT_FLAG);
        if (inputPort != null) {
            validPort = portArgToInt(validPort, inputPort);
        }
        return validPort;
    }

    private static HashMap<String,String> makeArgsMap(String[] args) {
        HashMap<String,String> argsMap = new HashMap<>();

        for (int i = 0; i < args.length; i++) {
            if (isValidFlag(args[i])) {
                argsMap.put(args[i], args[i + 1]);
            }
        }
        return argsMap;
    }
}
