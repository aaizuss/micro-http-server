package com.aaizuss.ResourceStore;

public class FileDirectoryNotFoundException extends Exception {
    public FileDirectoryNotFoundException(String directoryPath) {
        super("Cannot find directory " + directoryPath + "!");
    }
}
