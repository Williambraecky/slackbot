package be.wbraeckm.commands;

import be.wbraeckm.Main;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class SlackBotCommand {

    private Main main;
    private String name;
    private String usage;
    private String description;

    public abstract boolean handleCommand(String[] args);
}
