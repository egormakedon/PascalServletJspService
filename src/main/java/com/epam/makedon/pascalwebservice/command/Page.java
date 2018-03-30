package com.epam.makedon.pascalwebservice.command;

public enum Page {
    INDEX("index.jsp"),
    ADD("addPage.jsp"),
    ARTICLE("articlePage.jsp"),
    ;

    private String path;

    Page(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
