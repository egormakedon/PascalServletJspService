package com.epam.makedon.pascalwebservice.command;

import com.epam.makedon.pascalwebservice.Router;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    Router execute(HttpServletRequest req);
}