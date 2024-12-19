package fr.blockincraft.blocksbazaar;

import fr.blockincraft.blocksbazaar.command.Commands;
import fr.blockincraft.blocksbazaar.keybinding.KeyBindings;
import fr.blockincraft.blocksbazaar.listener.Listeners;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(
        modid = BlocksBazaar.MOD_ID,
        name = BlocksBazaar.MOD_NAME,
        version = BlocksBazaar.VERSION,
        clientSideOnly = true
)
public class BlocksBazaar {
    public static final String MOD_ID = "blocks_bazaar";
    public static final String MOD_NAME = "Blocks Bazaar";
    public static final String VERSION = "1.0.0-SNAPSHOT";

    @Mod.Instance(MOD_ID)
    public static BlocksBazaar INSTANCE;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Commands.registerCommands();
        KeyBindings.registerKeyBindings();
        Listeners.registerListeners();
    }
}
