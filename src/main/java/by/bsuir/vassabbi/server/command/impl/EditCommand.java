package by.bsuir.vassabbi.server.command.impl;

import by.bsuir.vassabbi.server.command.Command;
import by.bsuir.vassabbi.server.command.exception.CommandException;
import by.bsuir.vassabbi.server.model.AuthType;
import by.bsuir.vassabbi.server.service.ServiceFactory;

public class EditCommand implements Command {
    public String complete(Object caller, String request) throws CommandException {
        String[] arguments = request.split(" ");
        if (arguments.length != 4) throw new CommandException("Invalid syntax EDIT");

        if (ServiceFactory.getInstance().getAuthService().getAuthType(caller) != AuthType.MANAGER)
            return "Should be MANAGER";

        int id;
        try {
            id = Integer.parseInt(arguments[1]);
        } catch (NumberFormatException ignored) {
            return "Invalid id";
        }

        if (!ServiceFactory.getInstance().getCaseService().containsCase(id))
            return "No such case";

        ServiceFactory.getInstance().getCaseService().editCase(id, arguments[2], arguments[3]);
        return "Success";
    }
}
