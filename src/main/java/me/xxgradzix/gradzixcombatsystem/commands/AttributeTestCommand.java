package me.xxgradzix.gradzixcombatsystem.commands;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.StorageGui;
import me.xxgradzix.gradzixcombatsystem.ArmorTierManager;
import me.xxgradzix.gradzixcombatsystem.items.ItemManager;
import me.xxgradzix.gradzixcombatsystem.managers.AttributeManager;
import me.xxgradzix.gradzixcombatsystem.managers.CombatAttribute;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.map.MapView;

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
            player.sendMessage("Zrecznosc: " + AttributeManager.getAttributeLevel(player, CombatAttribute.ENDURANCE));
            player.sendMessage("Wytrzymalosc: " + AttributeManager.getAttributeLevel(player, CombatAttribute.DEXTERITY));
            player.sendMessage("Inteligencja: " + AttributeManager.getAttributeLevel(player, CombatAttribute.INTELLIGENCE));
        } else if(arg.equalsIgnoreCase("11")) {
            AttributeManager.incrementAttributeLevel(player, CombatAttribute.STRENGTH);
        } else if(arg.equalsIgnoreCase("12")) {
            AttributeManager.incrementAttributeLevel(player, CombatAttribute.ENDURANCE);
        } else if(arg.equalsIgnoreCase("13")) {
            AttributeManager.incrementAttributeLevel(player, CombatAttribute.DEXTERITY);
        } else if(arg.equalsIgnoreCase("14")) {
            AttributeManager.incrementAttributeLevel(player, CombatAttribute.INTELLIGENCE);
        } else if(arg.equalsIgnoreCase("15")) {
            AttributeManager.resetAttributes(player);
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
        } else if(arg.equalsIgnoreCase("4")) {

            MapView mapView = Bukkit.createMap(player.getWorld());

            player.sendMap(mapView);
        }


        return false;
    }
}
