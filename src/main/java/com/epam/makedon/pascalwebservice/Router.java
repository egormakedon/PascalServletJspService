package com.epam.makedon.pascalwebservice;

public class Router {
    private String path;
    private RouteType route;

    public enum RouteType {
        FORWARD, REDIRECT
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public RouteType getForward() {
        route = RouteType.FORWARD;
        return route;
    }

    public RouteType getRedirect() {
        route = RouteType.REDIRECT;
        return route;
    }
}