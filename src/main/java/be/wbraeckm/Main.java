package be.wbraeckm;

import be.wbraeckm.commands.SlackBotCommandManager;
import be.wbraeckm.listener.MessagePosterListener;
import be.wbraeckm.logger.Logger;
import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Getter
public class Main {

    private static Main main;

    private SlackSession session;
    private SlackBotCommandManager commandManager;
    private @Setter boolean running;
    private List<String> targetedUsers;
    private List<SlackChannel> trackedChannels;

    public static void main(String[] args) throws IOException
    {
        if (args.length == 0)
        {
            System.out.println("Please provide the token as launch argument");
            return ;
        }
        main = new Main(args[0]);
    }

    private Main(String token)
    {
        session = SlackSessionFactory
                .createWebSocketSlackSession(token);
        try {
            session.connect();
        } catch (Exception exception) {
            Logger.error("Could not start slacksession");
            return;
        }
        running = true;
        commandManager = new SlackBotCommandManager(this);
        readCommand(System.in);
        targetedUsers = new ArrayList<>();
        targetedUsers.add("UBGM4FT6C");targetedUsers.add("UAETDFTFW");targetedUsers.add("UBGM71U64");
        trackedChannels = new ArrayList<>();
        trackedChannels.add(session.findChannelByName("random"));
        session.addMessagePostedListener(new MessagePosterListener(this));
    }

    private void readCommand(InputStream inputStream)
    {
        new Thread(() -> {
            Scanner scanner = new Scanner(inputStream);
            String command;

            while (running && scanner.hasNextLine())
            {
                command = scanner.nextLine();
                commandManager.handleCommand(command);
            }
        }).start();
    }
}
