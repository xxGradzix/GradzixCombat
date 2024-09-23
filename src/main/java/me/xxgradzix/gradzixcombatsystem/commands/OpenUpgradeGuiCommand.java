package me.xxgradzix.gradzixcombatsystem.commands;

import me.xxgradzix.gradzixcombatsystem.ArmorTierManager;
import me.xxgradzix.gradzixcombatsystem.upgradeGuis.UpgradeGuiManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class OpenUpgradeGuiCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(args.length != 2) {
            sender.sendMessage("Usage: /openUpgradeGui <playerName> <priceModifier>");
            return false;
        }

        String playerName = args[0];
        String priceModifier = args[1];

        Player player;
        double priceModifierDouble;

        try {
            player = sender.getServer().getPlayer(playerName);
        } catch (NullPointerException e) {
            sender.sendMessage("Player not found");
            return false;
        }

        try {
            priceModifierDouble = Double.parseDouble(priceModifier);
        } catch (NumberFormatException e) {
            sender.sendMessage("Invalid price modifier");
            return false;
        }

        UpgradeGuiManager.openArmorsGui(player, ArmorTierManager.ArmorWeight.MEDIUM, priceModifierDouble, 1);

//        player.getInventory().addItem(ItemManager.testItem(true));
//        player.getInventory().addItem(ItemManager.testItem(false));

        return false;
    }

}
