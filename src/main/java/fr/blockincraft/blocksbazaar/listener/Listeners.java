package fr.blockincraft.blocksbazaar.listener;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventBus;

public class Listeners {
    // public static final MacroListeners MACRO_LISTENERS = new MacroListeners();

    public static void registerListeners() {
        EventBus bus = MinecraftForge.EVENT_BUS;

        // bus.register(MACRO_LISTENERS);
    }
}
