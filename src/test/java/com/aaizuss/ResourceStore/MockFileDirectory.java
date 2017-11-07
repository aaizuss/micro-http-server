package com.aaizuss.ResourceStore;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.TreeMap;

public class MockFileDirectory implements ResourceStore {

    private String directoryPath;
    private Hashtable<String,String> resources = new Hashtable<>();

    public static MockFileDirectory emptyDirectory(String path) {
        return new MockFileDirectory(path);
    }

    public static MockFileDirectory withResources(String path, Hashtable<String,String> resources) {
        return new MockFileDirectory(path, resources);
    }

    public static MockFileDirectory withResourceNames(String path, String[] resourceNames) {
        Hashtable<String,String> resources = new Hashtable<>();
        for (String name : resourceNames) {
            resources.put(name, "");
        }
        return new MockFileDirectory(path, resources);
    }

    public static MockFileDirectory withTextFile(String path, String fileName, String text) {
        Hashtable<String,String> resources = new Hashtable<>();
        resources.put(fileName, text);
        return new MockFileDirectory(path, resources);
    }

    public MockFileDirectory(String path) {
        this.directoryPath = path;
    }

    public MockFileDirectory(String path, Hashtable<String,String> resources) {
        this.directoryPath = path;
        this.resources = resources;
    }


    @Override
    public boolean containsResource(String uri) {
        String resourceName = getResourceNameFromUri(uri);
        for (String item : resources.keySet()) {
            if (resourceName.equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<String> getResources() {
        TreeMap<String,String> ordered = new TreeMap<>(resources);
        ArrayList<String> resourceNames = new ArrayList<>();
        for (String fileName : ordered.keySet()) {
            resourceNames.add(fileName);
        }
        return resourceNames;
    }

    @Override
    public byte[] read(String uri) {
        String fileName = getResourceNameFromUri(uri);
        return resources.get(fileName).getBytes();
    }

    private String getResourceNameFromUri(String uri) {
        return uri.substring(1, uri.length());
    }
}
