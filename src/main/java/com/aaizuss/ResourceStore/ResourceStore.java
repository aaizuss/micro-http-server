package com.aaizuss.ResourceStore;

import java.util.ArrayList;

public interface ResourceStore {

    boolean containsResource(String identifier);
    ArrayList<String> getResources();

    byte[] read(String uri);
}
