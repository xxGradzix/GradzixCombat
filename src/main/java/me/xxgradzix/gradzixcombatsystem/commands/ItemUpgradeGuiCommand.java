package me.xxgradzix.gradzixcombatsystem.commands;

import me.xxgradzix.gradzixcombatsystem.items.ItemManager;
import me.xxgradzix.gradzixcombatsystem.managers.EnchantManager.EnchantManager;
import me.xxgradzix.gradzixcombatsystem.managers.modifiersManager.ModifiersManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class ItemUpgradeGuiCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player player)){
            return false;
        }

        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();

        if(strings.length == 1) {
            if(strings[0].equalsIgnoreCase("0")) {
                ItemManager.upgradeWeapon(itemInMainHand);
                return true;
            }
            if(strings[0].equalsIgnoreCase("1")) {
                EnchantManager.addEnchant(itemInMainHand, EnchantManager.Enchant.FREEZE);
                return true;
            }
            if(strings[0].equalsIgnoreCase("11")) {
                EnchantManager.upgradeEnchant(itemInMainHand, EnchantManager.Enchant.FREEZE);
                return true;
            }
            if(strings[0].equalsIgnoreCase("2")) {
                EnchantManager.addEnchant(itemInMainHand, EnchantManager.Enchant.LIFE_STEAL);
                return true;
            }
            if(strings[0].equalsIgnoreCase("21")) {
                EnchantManager.upgradeEnchant(itemInMainHand, EnchantManager.Enchant.LIFE_STEAL);
                return true;
            }
            if(strings[0].equalsIgnoreCase("3")) {
                EnchantManager.addEnchant(itemInMainHand, EnchantManager.Enchant.ATTACK_COMBO);
                return true;
            }
            if(strings[0].equalsIgnoreCase("31")) {
                EnchantManager.upgradeEnchant(itemInMainHand, EnchantManager.Enchant.ATTACK_COMBO);
                return true;
            }
            if(strings[0].equalsIgnoreCase("99")) {
                player.getInventory().addItem(ItemManager.restartPotion);
                player.getInventory().addItem(ItemManager.stoneOfAggression);
                return true;
            }
            if(strings[0].equalsIgnoreCase("100")) {
                HashMap<EnchantManager.Enchant, Integer> enchants = EnchantManager.getEnchants(itemInMainHand);
                for (EnchantManager.Enchant enchant : enchants.keySet()) {
                    player.sendMessage(enchant.toString() + " " + enchants.get(enchant));
                }
                return true;
            }
            if(strings[0].equalsIgnoreCase("77")) {


                ModifiersManager.applyRandomModifier(itemInMainHand);

                return true;
            }
            if(strings[0].equalsIgnoreCase("771")) {


                ModifiersManager.setModifier(itemInMainHand, ModifiersManager.MelleModifier.LEGENDARY);

                return true;
            }

            if(strings[0].equalsIgnoreCase("772")) {


                ModifiersManager.setModifier(itemInMainHand, ModifiersManager.MelleModifier.TERRIBLE);

                return true;
            }





        }
//        EnchantManager.addEnchant(player, itemInMainHand, EnchantManager.Enchant.LIFE_STEAL);


        return false;
    }


}
