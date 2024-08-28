package me.xxgradzix.gradzixcombatsystem.commands;

import me.xxgradzix.gradzixcombatsystem.managers.AttributeManager;
import me.xxgradzix.gradzixcombatsystem.managers.CombatAttribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
        }



        return false;
    }
}
