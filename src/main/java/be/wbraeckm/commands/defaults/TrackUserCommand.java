package be.wbraeckm.commands.defaults;

import be.wbraeckm.Main;
import be.wbraeckm.commands.SlackBotCommand;
import be.wbraeckm.logger.Logger;
import com.ullink.slack.simpleslackapi.SlackUser;

public class TrackUserCommand extends SlackBotCommand {

    public TrackUserCommand(Main main)
    {
        super(main, "track", "track userid", "Used to track a new user");
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
        getMain().getTargetedUsers().add(slackUser.getId());
        Logger.log(String.format("Now tracking %s", slackUser.getUserName()));
        return true;
    }
}
