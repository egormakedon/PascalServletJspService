package com.epam.makedon.pascalwebservice.command;

public enum Type {
    CHANGE_LOCALE(new ChangeLocale()),
    ADD(new Add()),
    ;

    private Command command;
    Type(Command command) {
        this.command = command;
    }
    public Command getCommand() {
        return command;
    }
}