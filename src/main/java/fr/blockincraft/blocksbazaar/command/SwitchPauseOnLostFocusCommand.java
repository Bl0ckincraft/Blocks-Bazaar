package fr.blockincraft.blocksbazaar.command;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class SwitchPauseOnLostFocusCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "/switchpauseonlostfocus";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/switchpauseonlostfocus";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        Minecraft.getMinecraft().gameSettings.pauseOnLostFocus = !Minecraft.getMinecraft().gameSettings.pauseOnLostFocus;
        sender.addChatMessage(new ChatComponentText("New state of pause on lost focus: " + Minecraft.getMinecraft().gameSettings.pauseOnLostFocus));
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}
