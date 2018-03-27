package com.epam.makedon.pascalwebservice;

public enum Page {
    ;

    private String path;

    Page(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
