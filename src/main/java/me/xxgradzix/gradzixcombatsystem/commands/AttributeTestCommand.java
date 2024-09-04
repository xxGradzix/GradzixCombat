package me.xxgradzix.gradzixcombatsystem.commands;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import dev.triumphteam.gui.guis.StorageGui;
import me.xxgradzix.gradzixcombatsystem.ArmorTierManager;
import me.xxgradzix.gradzixcombatsystem.items.ItemManager;
import me.xxgradzix.gradzixcombatsystem.managers.AttributeManager;
import me.xxgradzix.gradzixcombatsystem.managers.CombatAttribute;
import me.xxgradzix.gradzixcombatsystem.managers.MessageManager;
import me.xxgradzix.gradzixcombatsystem.managers.MessageType;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.map.MapView;

import java.util.HashMap;

public class AttributeTestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player player)) {
            commandSender.sendMessage("Only for players"); //todo add to messages
            return false;
        }

        String arg = strings[0];

        if(arg.equalsIgnoreCase("0")) {
            openAttributeGui(player);
        } else if (arg.equalsIgnoreCase("21")) {

            for (int i = 1; i<=5; i++) {
                player.getInventory().addItem(ItemManager.getWeapon(ItemManager.WeaponType.SWORD, i));
            }

        } else if (arg.equalsIgnoreCase("22")) {

            for (int i = 1; i<=5; i++) {
                player.getInventory().addItem(ItemManager.getWeapon(ItemManager.WeaponType.AXE, i));
            }
        }  else if (arg.equalsIgnoreCase("23")) {

            for (int i = 1; i<=5; i++) {
                player.getInventory().addItem(ItemManager.getWeapon(ItemManager.WeaponType.JAVELIN, i));
            }
        }  else if (arg.equalsIgnoreCase("24")) {

            for (int i = 1; i<=5; i++) {
                player.getInventory().addItem(ItemManager.getWeapon(ItemManager.WeaponType.SHIELD, i));
            }
        } else if (arg.equalsIgnoreCase("25")) {

            for (int i = 1; i<=5; i++) {
                player.getInventory().addItem(ItemManager.getWeapon(ItemManager.WeaponType.CROSSBOW, i));
            }
        } else if (arg.equalsIgnoreCase("26")) {

            for (int i = 1; i<=5; i++) {
                player.getInventory().addItem(ItemManager.getWeapon(ItemManager.WeaponType.BOW, i));
            }
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

            player.getInventory().addItem(ItemManager.getWeapon(ItemManager.WeaponType.JAVELIN, 1));

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
        }  else if(arg.equalsIgnoreCase("4")) {
            AttributeManager.resetAttributes(player, 26);
        }


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
