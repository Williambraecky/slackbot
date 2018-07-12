package be.wbraeckm.commands.defaults;

import be.wbraeckm.Main;
import be.wbraeckm.commands.SlackBotCommand;
import be.wbraeckm.logger.Logger;

import java.io.IOException;

public class StopCommand extends SlackBotCommand {

    public StopCommand(Main main)
    {
        super(main, "stop", "stop", "Used to stop the bot");
    }

    @Override
    public boolean handleCommand(String[] args) {
        getMain().setRunning(false);
        Logger.info("Shutting down bot");
        try {
            getMain().getSession().disconnect();
        } catch (IOException e) {
            Logger.error("Unexpected error occured while disconnecting from slack");
            e.printStackTrace();
        }
        Logger.info("Goodbye :-)");
        return true;
    }
}
