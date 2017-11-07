package com.aaizuss.ResourceStore;

import org.junit.rules.TemporaryFolder;

import java.io.IOException;

public class TemporaryTestDirectory {
    public static void populate(TemporaryFolder folder) {
        try {
            folder.newFile("index.html");
            folder.newFolder("styles", "styles.css");
            folder.newFile("script.js");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

