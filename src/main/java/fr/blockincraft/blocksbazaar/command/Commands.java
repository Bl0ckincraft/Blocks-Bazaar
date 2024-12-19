package fr.blockincraft.blocksbazaar.command;

import net.minecraftforge.client.ClientCommandHandler;

public class Commands {
    public static void registerCommands() {
        ClientCommandHandler cch = ClientCommandHandler.instance;

        cch.registerCommand(new SwitchFocus());
        cch.registerCommand(new SwitchPauseOnLostFocusCommand());
    }
}
