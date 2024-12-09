package me.xxgradzix.gradzixcombatsystem.commands;

import me.xxgradzix.gradzixcombatsystem.guis.upgradeGuis.MainEnchanterGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EnchantGuiCommand implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        String playerName = args[0];
        Player player;

        try {
            player = sender.getServer().getPlayer(playerName);
        } catch (NullPointerException e) {
            if(sender instanceof Player) {
                player = (Player) sender;
            } else {
                sender.sendMessage("Player not found");
                return false;
            }
        }


        new MainEnchanterGui(player);


        return false;
    }
}
