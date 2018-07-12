package be.wbraeckm.commands;

import be.wbraeckm.Main;
import be.wbraeckm.commands.defaults.StopCommand;
import be.wbraeckm.commands.defaults.TrackUserCommand;
import be.wbraeckm.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class SlackBotCommandManager {

    private Main main;
    private List<SlackBotCommand> commandList;

    public SlackBotCommandManager(Main main)
    {
        this.main = main;
        commandList = new ArrayList<>();
        registerCommand(new TrackUserCommand(main));
        registerCommand(new StopCommand(main));
    }

    public void handleCommand(String command)
    {
        String[] split = command.split(" ");
        if (split.length == 0 || split[0].equalsIgnoreCase(""))
        {
            Logger.warning("Command split resulted in 0 length");
            return ;
        }
        SlackBotCommand slackBotCommand = getCommand(split[0]);
        if (slackBotCommand == null)
        {
            Logger.info("Could not find command named " + split[0]);
            return ;
        }
        String[] args = new String[split.length - 1];
        System.arraycopy(split, 1, args, 0, split.length - 1);
        if (!slackBotCommand.handleCommand(args))
            Logger.log(String.format("Usage: %s", slackBotCommand.getUsage()));
    }

    private boolean registerCommand(SlackBotCommand command)
    {
        if (getCommand(command.getName()) != null)
            return (false);
        commandList.add(command);
        return (true);
    }

    private SlackBotCommand getCommand(String name)
    {
        return commandList.stream()
                .filter(slackBotCommand -> slackBotCommand.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }
}
