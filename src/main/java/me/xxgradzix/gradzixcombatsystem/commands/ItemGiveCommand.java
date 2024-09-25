package me.xxgradzix.gradzixcombatsystem.commands;

import me.xxgradzix.gradzixcombatsystem.ArmorTierManager;
import me.xxgradzix.gradzixcombatsystem.items.ItemManager;
import me.xxgradzix.gradzixcombatsystem.weapons.WeaponType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class ItemGiveCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player player)) {
            commandSender.sendMessage("Only for players");
            return false;
        }

        int tier = 1;

        String itemCategory = "armor";

        String itemVariant = "medium";



        if(strings.length == 3) {
            tier = Integer.parseInt(strings[0]);
            itemCategory = strings[1];
            itemVariant = strings[2];
        } else if (strings.length == 2) {
            tier = Integer.parseInt(strings[0]);
            itemCategory = strings[1];
        }

        if(itemCategory.equalsIgnoreCase("armor")) {
            ArmorTierManager.ArmorWeight armorWeight;
            try {
                armorWeight = ArmorTierManager.ArmorWeight.valueOf(itemVariant.toUpperCase());
            } catch (IllegalArgumentException e) {
                player.sendMessage(" nie poprawna wartość: " + itemVariant);
                return true;
            }

            switch (armorWeight) {
                case LIGHT -> {
                    ItemManager.getLightArmor(player, tier);
                }
                case MEDIUM -> {
                    ItemManager.getMediumArmor(player, tier);
                }
                case HEAVY -> {
                    ItemManager.getHeavyArmor(player, tier);
                }
            }
        } else if(itemCategory.equalsIgnoreCase("weapon")) {
            WeaponType weaponType = WeaponType.valueOf(itemVariant.toUpperCase());
            ItemStack weapon = ItemManager.getWeapon(weaponType, tier);
            player.getInventory().addItem(weapon);
        }


        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if(args.length == 1) {
            return List.of("1", "2", "3", "4", "5");
        }
        if(args.length == 2) {
            return List.of("armor", "weapon");
        }
        if(args.length == 3) {
            if(args[1].equalsIgnoreCase("armor")) {
                return Arrays.stream(ArmorTierManager.ArmorWeight.values()).map(Enum::name).map(String::toLowerCase).toList();
            }
            if(args[1].equalsIgnoreCase("weapon")) {
                return Arrays.stream(WeaponType.values()).map(Enum::name).map(String::toLowerCase).toList();
            }
        }
        return List.of();
    }
}
