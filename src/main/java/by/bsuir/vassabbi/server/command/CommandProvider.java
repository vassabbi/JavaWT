package by.bsuir.vassabbi.server.command;

import by.bsuir.vassabbi.server.command.exception.CommandException;
import by.bsuir.vassabbi.server.command.impl.*;

public class CommandProvider {
    private static final CommandProvider INSTANCE = new CommandProvider();

    private CommandProvider() {
    }

    public static CommandProvider getInstance() {
        return INSTANCE;
    }

    public Command getCommand(String request) throws CommandException {
        if (request == null) throw new CommandException("No command");
        String[] arguments = request.split(" ");
        if (arguments.length < 1) throw new CommandException("No command");
        return switch (arguments[0]) {
            case "AUTH" -> new AuthCommand();
            case "DISCONNECT" -> new DisconnectCommand();
            case "EDIT" -> new EditCommand();
            case "VIEW" -> new ViewCommand();
            case "CREATE" -> new CreateCommand();
            default -> throw new CommandException("No such command");
        };
    }
}
