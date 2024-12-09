package me.xxgradzix.gradzixcombatsystem.commands;

import me.xxgradzix.gradzixcombatsystem.guis.potion.MainPotionGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class OpenWitchGuiCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(args.length != 1) {
            sender.sendMessage("Usage: /openWitchGui <playerName>");
            return false;
        }
        String playerName = args[0];


        Player player;

        try {
            player = sender.getServer().getPlayer(playerName);
        } catch (NullPointerException e) {
            sender.sendMessage("Player not found");
            return false;
        }

//        new WeaponUpgradeGuiManager(player);

        new MainPotionGui(player);

//        player.getInventory().addItem(ItemManager.testItem(true));
//        player.getInventory().addItem(ItemManager.testItem(false));

        return false;
    }

}
