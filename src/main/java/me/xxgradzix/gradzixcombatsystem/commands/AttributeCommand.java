package me.xxgradzix.gradzixcombatsystem.commands;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcombatsystem.items.ItemManager;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.AbilitiesPointsManager;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.AttributePointsManager;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.CombatAttribute;
import me.xxgradzix.gradzixcombatsystem.managers.magicEffects.MagicEffectManager;
import me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager;
import me.xxgradzix.gradzixcombatsystem.managers.messages.MessageType;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
                        AttributePointsManager.resetAllAttributes();
                        return true;
                    }
                    Player player = Bukkit.getPlayer(arg2);
                    if(player == null) {
                        commandSender.sendMessage("Gracz " + arg2 + " nie jest na serwerze");
                        return true;
                    }
                    AttributePointsManager.resetAttributes(player, 0);
                    return true;
                }
                if(arg1.equalsIgnoreCase("add")) {
                    Player player = Bukkit.getPlayer(arg2);
                    if (player == null) {
                        commandSender.sendMessage("Gracz " + arg2 + " nie jest na serwerze");
                        return true;
                    }
                    AttributePointsManager.givePointsToPlayer(player, 1, true);
                    return true;
                }
                if(arg1.equalsIgnoreCase("test88")) {
                    Player player = (Player) commandSender;
                    MagicEffectManager.setSoulsStolen(player.getInventory().getItemInMainHand(), 88);
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
            GuiItem attributeGuiItem = new GuiItem(ItemManager.getAttributeItem(player, attribute, AttributePointsManager.getAttributeLevel(player, attribute)));

            attributeGuiItem.setAction((e) -> {

                if(e.isRightClick()) {
                    AbilitiesPointsManager.openAbilitiesGui(player, attribute);
                    return;
                }

                boolean isSuccessful = AttributePointsManager.incrementAttributeLevel(player, attribute);

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
