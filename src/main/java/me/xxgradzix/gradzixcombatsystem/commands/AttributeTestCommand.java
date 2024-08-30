package me.xxgradzix.gradzixcombatsystem.commands;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.StorageGui;
import me.xxgradzix.gradzixcombatsystem.ArmorTierManager;
import me.xxgradzix.gradzixcombatsystem.items.ItemManager;
import me.xxgradzix.gradzixcombatsystem.managers.AttributeManager;
import me.xxgradzix.gradzixcombatsystem.managers.CombatAttribute;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AttributeTestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player player)) {
            commandSender.sendMessage("Only for players"); //todo add to messages
            return false;
        }

        String arg = strings[0];

        if(arg.equalsIgnoreCase("1")) {
            player.sendMessage("Sila: " + AttributeManager.getAttributeLevel(player, CombatAttribute.STRENGTH));

        } else if(arg.equalsIgnoreCase("2")) {

            AttributeManager.incrementAttributeLevel(player, CombatAttribute.STRENGTH);
        } else if(arg.equalsIgnoreCase("31")) {

            StorageGui storageGui = Gui.storage()
                    .title(Component.text("Test"))
                    .rows(6)
                    .create();

            for(ItemStack itemStack : ItemManager.getAllItems(ArmorTierManager.ArmorWeight.LIGHT)) {
                storageGui.addItem(itemStack);
            }

            storageGui.open(player);
        } else if(arg.equalsIgnoreCase("32")) {

            StorageGui storageGui = Gui.storage()
                    .title(Component.text("Test"))
                    .rows(6)
                    .create();

            for(ItemStack itemStack : ItemManager.getAllItems(ArmorTierManager.ArmorWeight.MEDIUM)) {
                storageGui.addItem(itemStack);
            }

            storageGui.open(player);
        } else if(arg.equalsIgnoreCase("33")) {

            StorageGui storageGui = Gui.storage()
                    .title(Component.text("Test"))
                    .rows(6)
                    .create();

            for(ItemStack itemStack : ItemManager.getAllItems(ArmorTierManager.ArmorWeight.HEAVY)) {
                storageGui.addItem(itemStack);
            }

            storageGui.open(player);
        }



        return false;
    }
}
