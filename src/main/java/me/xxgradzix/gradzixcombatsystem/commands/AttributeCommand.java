package me.xxgradzix.gradzixcombatsystem.commands;

import com.google.common.collect.Multimap;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcombatsystem.items.ItemManager;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.AttributeManager;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.CombatAttribute;
import me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager;
import me.xxgradzix.gradzixcombatsystem.managers.messages.MessageType;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class AttributeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender.isOp()) {
            if(strings.length == 2) {
                String arg1 = strings[0];
                String arg2 = strings[1];

                if(arg1.equalsIgnoreCase("reset")) {
                    boolean all = arg2.equalsIgnoreCase("all");
                    if(all) {
                        AttributeManager.resetAllAttributes();
                        return true;
                    }
                    Player player = Bukkit.getPlayer(arg2);
                    if(player == null) {
                        commandSender.sendMessage("Gracz " + arg2 + " nie jest na serwerze");
                        return true;
                    }
                    AttributeManager.resetAttributes(player, 0);
                    return true;
                }
                if(arg1.equalsIgnoreCase("add")) {
                    Player player = Bukkit.getPlayer(arg2);
                    if (player == null) {
                        commandSender.sendMessage("Gracz " + arg2 + " nie jest na serwerze");
                        return true;
                    }
                    AttributeManager.givePointsToPlayer(player, 1, true);
                    return true;
                }

            }
        }
        if(!(commandSender instanceof Player player)) {
            commandSender.sendMessage("Only for players");
            return false;
        }

        openAttributeGui(player);

        return false;
    }

    private void openAttributeGui(Player player) {
        Gui gui = Gui.gui()
                .title(Component.text("Atrybuty"))
                .rows(3)
                .disableAllInteractions()
                .create();

        int slot = 10;

        for (CombatAttribute attribute : CombatAttribute.values()) {
            GuiItem attributeGuiItem = new GuiItem(ItemManager.getAttributeItem(player, attribute, AttributeManager.getAttributeLevel(player, attribute)));

            attributeGuiItem.setAction((e) -> {

                boolean isSuccessful = AttributeManager.incrementAttributeLevel(player, attribute);

                if(isSuccessful) {
                    openAttributeGui(player);
                } else {
                    MessageManager.sendMessageFormated(player, MessageManager.NOT_ENOUGH_FREE_POINTS, MessageType.CHAT);
                }

            });
            gui.setItem(slot, attributeGuiItem);
            slot+=2;

        }

        gui.open(player);
    }
}
