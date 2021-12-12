package by.bsuir.vassabbi.server.command;

import by.bsuir.vassabbi.server.command.exception.CommandException;

public interface Command {
    String complete(Object caller, String request) throws CommandException;
}
