package me.xxgradzix.gradzixcombatsystem.commands;

import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.AttributePointsManager;
import me.xxgradzix.gradzixcombatsystem.guis.attributes.AttributesGuiManager;
import me.xxgradzix.gradzixcombatsystem.managers.magicEffects.MagicEffectManager;
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

        new AttributesGuiManager(player);

        return false;
    }
}
