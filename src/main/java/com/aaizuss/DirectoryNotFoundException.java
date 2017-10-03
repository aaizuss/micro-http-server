package com.aaizuss;

public class DirectoryNotFoundException extends Exception {

    public DirectoryNotFoundException(String directoryPath) {
        super("Cannot find directory " + directoryPath + "!");
    }
}
