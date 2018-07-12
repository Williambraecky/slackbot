package be.wbraeckm;

import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.SlackUser;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import com.ullink.slack.simpleslackapi.listeners.SlackMessagePostedListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static Main main;

    private SlackSession session;
    private SlackChannel channelRandom;

    public static void main(String[] args) throws IOException {
        main = new Main();
    }

    public Main() throws IOException {
        session = SlackSessionFactory
                .createWebSocketSlackSession("xoxb-354360306016-397451107731-FNMpdS6a4RYTx68klnAvIOXK");
        session.connect();
        channelRandom = session.findChannelByName("random");
        final SlackChannel _private = session.findChannelById("GBPGHB06P");
        session.addMessagePostedListener(new SlackMessagePostedListener() {
            public void onEvent(SlackMessagePosted slackMessagePosted, SlackSession slackSession) {
                if (slackMessagePosted.getChannel() != _private ||
                        (!slackMessagePosted.getSender().getId().equals("UBGM71U64") &&
                        !slackMessagePosted.getSender().getId().equals("UAETDFTFW") &&
                        !slackMessagePosted.getSender().getId().equals("UBGM4FT6C")))
                    return ;
                session.addReactionToMessage(_private, slackMessagePosted.getTimeStamp(), "thor");
                session.addReactionToMessage(_private, slackMessagePosted.getTimeStamp(), "vs");
                session.addReactionToMessage(_private, slackMessagePosted.getTimeStamp(), "my_little_pony");
                System.out.println(String.format("%s: %s",
                        slackMessagePosted.getSender().getId(),
                        slackMessagePosted.getMessageContent()));
            }
        });
    }
}
