package com.aaizuss.ResourceStore;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class FileDirectory implements ResourceStore {
    private String pathString;
    private ArrayList<String> resources = new ArrayList<>();

    public FileDirectory(String absolutePath) {
        this.pathString = formatPathString(absolutePath);
        this.resources = initResources(pathString);
    }

    public String getPathString() {
        return pathString;
    }

    @Override
    public boolean containsResource(String uri) {
        String path = getPathToResource(uri);
        File file = new File(path);
        return file.exists();
    }

    @Override
    public ArrayList<String> getResources() {
        return resources;
    }

    @Override
    public byte[] read(String uri) {
        String path = getPathToResource(uri);
        return readFromPath(path);
    }

    private byte[] readFromPath(String absolutePath) {
        File file = new File(absolutePath);

        byte[] content = new byte[0];
        if (file.isFile()) {
            try {
                content = Files.readAllBytes(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }

    private String getPathToResource(String uri) {
        File file = uriToFile(uri);
        return file.getPath();
    }

    private File uriToFile(String uri) {
        String identifier = uri;
        if (!uri.startsWith("/")) {
            identifier = "/" + uri;
        }
        return new File(pathString + identifier);
    }

    private String formatPathString(String pathString) {
        if (pathString.endsWith("/")) {
            return pathString.substring(0, pathString.length() - 1);
        } else {
            return pathString;
        }
    }

    private ArrayList<String> initResources(String pathString) {
        File file = new File(pathString);
        String[] files = file.list();
        ArrayList<String> fileList = new ArrayList<>();
        for (String entry : files) {
            fileList.add(entry);
        }
        return fileList;
    }
}
