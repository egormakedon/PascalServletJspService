package com.epam.makedon.pascalwebservice.command;

import java.util.Optional;

public class Factory {
    public static Optional<Command> defineCommand(String commandName) {
        Type type = Type.valueOf(commandName.toUpperCase());
        return Optional.of(type.getCommand());
    }
}