package me.xxgradzix.gradzixcombatsystem.commands;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.items.CustomItemManager;
import me.xxgradzix.gradzixcombatsystem.items.ItemManager;
import me.xxgradzix.gradzixcombatsystem.items.armors.ArmorType;
import me.xxgradzix.gradzixcombatsystem.items.armors.ArmorWeight;
import me.xxgradzix.gradzixcombatsystem.items.battleBottles.CombatPotionType;
import me.xxgradzix.gradzixcombatsystem.items.weapons.WeaponType;
import me.xxgradzix.gradzixcombatsystem.managers.EnchantManager.EnchantManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Husk;
import org.bukkit.entity.NPC;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
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

        if(strings.length == 1) {
            if(strings[0].equalsIgnoreCase("potion")) {

                ItemStack potion = CustomItemManager.FALLEN_HERO_POTION.getCustomItem().getDefaultItemStack();
                player.getInventory().addItem(potion);

                ItemStack dashOrb = CustomItemManager.DASH_ORB.getCustomItem().getDefaultItemStack();
                player.getInventory().addItem(dashOrb);
                ItemStack heartOrb = CustomItemManager.HEART_ORB.getCustomItem().getDefaultItemStack();
                player.getInventory().addItem(heartOrb);
                ItemStack teleportOrb = CustomItemManager.TELEPORT_ORB.getCustomItem().getDefaultItemStack();
                player.getInventory().addItem(teleportOrb);
                ItemStack volcanoOrb = CustomItemManager.VOLCANO_ORB.getCustomItem().getDefaultItemStack();
                player.getInventory().addItem(volcanoOrb);


            }
        }

        if(strings.length == 3) {
            tier = Integer.parseInt(strings[0]);
            itemCategory = strings[1];
            itemVariant = strings[2];
        } else if (strings.length == 2) {
            tier = Integer.parseInt(strings[0]);
            itemCategory = strings[1];
        }

        if(itemCategory.equalsIgnoreCase("armor")) {
            ArmorWeight armorWeight;
            try {
                armorWeight = ArmorWeight.valueOf(itemVariant.toUpperCase());
            } catch (IllegalArgumentException e) {
                player.sendMessage(" nie poprawna wartość: " + itemVariant);
                return true;
            }

            switch (armorWeight) {
                case LIGHT -> {
                    for(ArmorType armorType : ArmorType.values()) {
                        ItemStack armor = CustomItemManager.LIGHT_ARMOR.getCustomItem().getDefaultItemStack(tier, armorType);
                        player.getInventory().addItem(armor);
                    }
//                    CustomItemManager.LIGHT_ARMOR.getCustomItem().defaultSetItemCustomId(tier);
//                    ItemManager.getLightArmor(player, tier);
                }
                case MEDIUM -> {
//                    ItemManager.getMediumArmor(player, tier);
                    for (ArmorType armorType : ArmorType.values()) {
                        ItemStack armor = CustomItemManager.MEDIUM_ARMOR.getCustomItem().getDefaultItemStack(tier, armorType);
                        player.getInventory().addItem(armor);
                    }
                }
                case HEAVY -> {
//                    ItemManager.getHeavyArmor(player, tier);
                    for (ArmorType armorType : ArmorType.values()) {
                        ItemStack armor = CustomItemManager.HEAVY_ARMOR.getCustomItem().getDefaultItemStack(tier, armorType);
                        player.getInventory().addItem(armor);
                    }
                }
            }
        } else if(itemCategory.equalsIgnoreCase("weapon")) {
            WeaponType weaponType = WeaponType.valueOf(itemVariant.toUpperCase());
            ItemStack weapon = CustomItemManager.valueOf(weaponType.name()).getCustomItem().getDefaultItemStack(tier);
            player.getInventory().addItem(weapon);
        } else if (itemCategory.equalsIgnoreCase("arrow")) {
            switch (itemVariant) {
                case "common" -> {
                    ItemStack commonArrow = CustomItemManager.COMMON_ARROW.getCustomItem().getDefaultItemStack(tier);
                    player.getInventory().addItem(commonArrow);
                }
                case "gravitional" -> {
                    ItemStack gravitionalArrow = CustomItemManager.GRAVITATIONAL_ARROW.getCustomItem().getDefaultItemStack(tier);
                    player.getInventory().addItem(gravitionalArrow);
                }
                case "knockback" -> {
                    ItemStack knockbackArrow = CustomItemManager.KNOCK_BACK_ARROW.getCustomItem().getDefaultItemStack(tier);
                    player.getInventory().addItem(knockbackArrow);
                }
                case "explosive" -> {
                    ItemStack explosiveArrow = CustomItemManager.EXPLOSIVE_ARROW.getCustomItem().getDefaultItemStack(tier);
                    player.getInventory().addItem(explosiveArrow);
                }
            }
        } else if (itemCategory.equalsIgnoreCase("book")) {
            EnchantManager.Enchant enchant;
            try {
                enchant = EnchantManager.Enchant.valueOf(itemVariant.toUpperCase());
            } catch (IllegalArgumentException e) {
                player.sendMessage(" nie poprawna wartość: " + itemVariant);
                return true;
            }

            ItemStack book = CustomItemManager.getEnchantBookCustomItemByEnchant(enchant).getDefaultItemStack(tier);

            player.getInventory().addItem(book);

        } else if (itemCategory.equalsIgnoreCase("aggression_stone")) {
            player.getInventory().addItem(ItemManager.stoneOfAggression);
        } else if (itemCategory.equalsIgnoreCase("restart_potion")) {
            player.getInventory().addItem(ItemManager.restartPotion);
        } else if (itemCategory.equalsIgnoreCase("bottle")) {
            CombatPotionType combatPotionType;
            try {
                combatPotionType = CombatPotionType.valueOf(itemVariant.toUpperCase());
            } catch (IllegalArgumentException e) {
                player.sendMessage(" nie poprawna wartość: " + itemVariant);
                return true;
            }

            ItemStack book = CustomItemManager.getCustomPotionBottle(combatPotionType).getDefaultItemStack(tier);

            player.getInventory().addItem(book);

        }


        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if(args.length == 1) {
            return List.of("1", "2", "3", "4", "5");
        }
        if(args.length == 2) {
            return List.of("armor", "weapon", "arrow", "book",
                    "aggression_stone", "restart_potion", "bottle");
        }
        if(args.length == 3) {
            if(args[1].equalsIgnoreCase("armor")) {
                return Arrays.stream(ArmorWeight.values()).map(Enum::name).map(String::toLowerCase).toList();
            }
            if(args[1].equalsIgnoreCase("weapon")) {
                return Arrays.stream(WeaponType.values()).map(Enum::name).map(String::toLowerCase).toList();
            }
            if(args[1].equalsIgnoreCase("arrow")) {
                return List.of("common", "gravitional", "knockback", "explosive");
            }
            if(args[1].equalsIgnoreCase("book")) {
                return Arrays.stream(EnchantManager.Enchant.values()).map(Enum::name).map(String::toLowerCase).toList();
            }
            if(args[1].equalsIgnoreCase("bottle")) {
                return Arrays.stream(CombatPotionType.values()).map(Enum::name).map(String::toLowerCase).toList();
            }
        }

        return List.of();
    }
}
