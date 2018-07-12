package be.wbraeckm.listener;

import be.wbraeckm.Main;
import be.wbraeckm.logger.Logger;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import com.ullink.slack.simpleslackapi.listeners.SlackMessagePostedListener;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MessagePosterListener implements SlackMessagePostedListener {

    private Main main;

    public void onEvent(SlackMessagePosted slackMessagePosted, SlackSession slackSession)
    {
        Logger.log(String.format("#%s %s: %s", slackMessagePosted.getChannel().getName(),
                slackMessagePosted.getSender().getUserName(), slackMessagePosted.getMessageContent()));
        if (!main.getTrackedChannels().contains(slackMessagePosted.getChannel()) ||
                !main.getTargetedUsers().contains(slackMessagePosted.getSender().getId()))
            return;
        main.getSession().addReactionToMessage(slackMessagePosted.getChannel(),
                slackMessagePosted.getTimeStamp(), "thor");
        main.getSession().addReactionToMessage(slackMessagePosted.getChannel(),
                slackMessagePosted.getTimeStamp(), "vs");
        main.getSession().addReactionToMessage(slackMessagePosted.getChannel(),
                slackMessagePosted.getTimeStamp(), "my_little_pony");
    }
}
