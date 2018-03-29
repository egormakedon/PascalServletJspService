package com.epam.makedon.pascalwebservice.command;

public enum Type {
    CHANGE_LOCALE(new ChangeLocale());

    private Command command;
    Type(Command command) {
        this.command = command;
    }
    public Command getCommand() {
        return command;
    }
}