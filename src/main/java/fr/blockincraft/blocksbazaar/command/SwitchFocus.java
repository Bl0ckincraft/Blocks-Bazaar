package fr.blockincraft.blocksbazaar.command;

import fr.blockincraft.blocks.listener.MacroListeners;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MouseHelper;

public class SwitchFocus extends CommandBase {
    private static CustomMouseHelper cmh = null;

    @Override
    public String getCommandName() {
        return "/switchfocus";
    }

    @Override
    public String getCommandUsage(ICommandSender iCommandSender) {
        return "/switchfocus";
    }

    @Override
    public void processCommand(ICommandSender iCommandSender, String[] strings) throws CommandException {
        for (Entity entity : Minecraft.getMinecraft().theWorld.getLoadedEntityList()) {
            NBTTagCompound nbt = new NBTTagCompound();
            entity.writeToNBT(nbt);

            if (nbt.hasKey("Equipment")) {
                NBTTagList equipment = nbt.getTagList("Equipment", 10);
                if (equipment.tagCount() > 4) {
                    NBTTagCompound itemNbt = equipment.getCompoundTagAt(4);
                    if (itemNbt.hasKey("id") && itemNbt.getString("id").equals("minecraft:skull")) {
                        if (itemNbt.hasKey("tag")) {
                            NBTTagCompound tagNbt = itemNbt.getCompoundTag("tag");
                            if (tagNbt.hasKey("SkullOwner")) {
                                NBTTagCompound skullOwner = tagNbt.getCompoundTag("SkullOwner");
                                if (skullOwner.hasKey("Properties")) {
                                    NBTTagCompound properties = skullOwner.getCompoundTag("Properties");
                                    if (properties.hasKey("textures")) {
                                        NBTTagList textures = properties.getTagList("textures", 10);
                                        if (textures.tagCount() > 0) {
                                            NBTTagCompound texture = textures.getCompoundTagAt(0);
                                            if (texture.hasKey("Value")) {
                                                String textureData = texture.getString("Value");
                                                System.out.println("--------------------------------------------------------------");
                                                System.out.println("Texture data: " + textureData);
                                                System.out.println("Distance: " + entity.getDistanceToEntity(Minecraft.getMinecraft().thePlayer));
                                                MacroListeners.Pests pest = MacroListeners.Pests.getPestByTexture(textureData);
                                                if (pest != null) {
                                                    System.out.println("Pest: " + pest.getName());
                                                }
                                                System.out.println("--------------------------------------------------------------");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (cmh == null) {
            cmh = new CustomMouseHelper(Minecraft.getMinecraft().mouseHelper);
            Minecraft.getMinecraft().mouseHelper = cmh;
        }

        if (cmh.isFocussed()) {
            Minecraft.getMinecraft().inGameHasFocus = true;
            Minecraft.getMinecraft().setIngameNotInFocus();
            Minecraft.getMinecraft().mouseHelper.grabMouseCursor();
            Minecraft.getMinecraft().mouseHelper.ungrabMouseCursor();
            cmh.setFocussed(false);
        } else {
            cmh.setFocussed(true);
            Minecraft.getMinecraft().inGameHasFocus = false;
            Minecraft.getMinecraft().setIngameFocus();
            Minecraft.getMinecraft().mouseHelper.ungrabMouseCursor();
            Minecraft.getMinecraft().mouseHelper.grabMouseCursor();
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    public static class CustomMouseHelper extends MouseHelper {
        private final MouseHelper base;
        private boolean focussed = true;

        public CustomMouseHelper(MouseHelper helper) {
            this.base = helper;
            this.deltaX = base.deltaX;
            this.deltaY = base.deltaY;
        }

        public boolean isFocussed() {
            return focussed;
        }

        public void setFocussed(boolean focussed) {
            this.focussed = focussed;
        }

        public void grabMouseCursor() {
            if (focussed) {
                base.grabMouseCursor();
                this.deltaX = base.deltaX;
                this.deltaY = base.deltaY;
            }
        }

        public void ungrabMouseCursor() {
            if (focussed) {
                base.ungrabMouseCursor();
                this.deltaX = base.deltaX;
                this.deltaY = base.deltaY;
            }
        }

        public void mouseXYChange() {
            if (focussed) {
                base.mouseXYChange();
                this.deltaX = base.deltaX;
                this.deltaY = base.deltaY;
            }
        }
    }
}
