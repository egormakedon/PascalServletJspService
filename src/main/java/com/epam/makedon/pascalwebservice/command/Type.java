package com.epam.makedon.pascalwebservice.command;

public enum Type {
    ;

    private Command command;
    Type(Command command) {
        this.command = command;
    }
    public Command getCommand() {
        return command;
    }
}