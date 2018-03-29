package com.epam.makedon.pascalwebservice.servlet;

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

    public void setForward() {
        route = RouteType.FORWARD;
    }

    public void setRedirect() {
        route = RouteType.REDIRECT;
    }

    public RouteType getRoute() {
        return route;
    }
}