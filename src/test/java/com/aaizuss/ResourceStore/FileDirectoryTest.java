package com.aaizuss.ResourceStore;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FileDirectoryTest {

    private static FileDirectory directory;

    @ClassRule
    public static TemporaryFolder myTempFolder = new TemporaryFolder();

    @BeforeClass
    public static void setUp() throws IOException {
        TemporaryTestDirectory.populate(myTempFolder);
        directory = new FileDirectory(myTempFolder.getRoot().getPath());
    }

    @Test
    public void testDirectoryConstructorSetsPathString() {
        String expected = myTempFolder.getRoot().getPath();
        assertEquals(expected, directory.getPathString());
    }

    @Test
    public void testGetResourcesReturnsListOfTopLevelResources() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("index.html");
        expected.add("script.js");
        expected.add("styles");
        assertEquals(expected, directory.getResources());
    }

    @Test
    public void givenAResourceExistsInTheTopLevelContainsResourceReturnsTrue() {
        assertTrue(directory.containsResource("/index.html"));
        assertFalse(directory.containsResource("/foo.txt"));
    }

    @Test
    public void givenAResourceExistsInAFolderContainsResourceReturnsTrue() {
        assertTrue(directory.containsResource("/styles/styles.css"));
    }
}
