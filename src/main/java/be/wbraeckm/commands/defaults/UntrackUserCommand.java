package be.wbraeckm.commands.defaults;

import be.wbraeckm.Main;
import be.wbraeckm.commands.SlackBotCommand;
import be.wbraeckm.logger.Logger;
import com.ullink.slack.simpleslackapi.SlackUser;

public class UntrackUserCommand extends SlackBotCommand {

    public UntrackUserCommand(Main main)
    {
        super(main, "untrack", "untrack userid", "Used to untrack an user");
    }

    @Override
    public boolean handleCommand(String[] args) {
        if (args.length == 0)
            return false;
        SlackUser slackUser = getMain().getSession().findUserById(args[0]);
        if (slackUser == null)
        {
            Logger.log(String.format("Could not find user with id %s", args[0]));
            return true;
        }
        if (!getMain().getTargetedUsers().contains(slackUser.getId()))
            Logger.log(String.format("User %s was not tracked", slackUser.getUserName()));
        getMain().getTargetedUsers().remove(slackUser.getId());
        Logger.log(String.format("Not tracking %s anymore", slackUser.getUserName()));
        return true;
    }
}
