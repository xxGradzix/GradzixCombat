package me.xxgradzix.gradzixcombatsystem.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class NbtItemUtil {

    public static boolean isCustomItem(ItemStack itemStack) {
        return isCustomItem(itemStack.getItemMeta());
    }

    public static boolean isCustomItem(ItemMeta meta) {
        return CustomItem.getCustomId(meta).isPresent();
    }
    public static int calcItemAmount(Player player, ItemStack customItem) throws ItemIsNotCustomItem {

        int totalAmount = 0;
        for (ItemStack itemStack : player.getInventory()) {
            if (itemStack != null && (isSameNBT(customItem, itemStack) || itemStack.isSimilar(customItem))) {
                totalAmount += itemStack.getAmount();
            }
        }
        return totalAmount;
    }

    public static void removeItemsWithCustomItemNBT(Player player, ItemStack targetItem, int amountToRemove)  {


        for (ItemStack itemStack : player.getInventory()) {
            if (amountToRemove <= 0) {
                break;
            }

            if (itemStack != null && (isSameNBT(targetItem, itemStack) || itemStack.isSimilar(targetItem)) && itemStack.getType() != Material.AIR) {
                int currentAmount = itemStack.getAmount();

                if (currentAmount <= amountToRemove) {
                    amountToRemove -= currentAmount;
                    itemStack.setAmount(0);
                } else {
                    itemStack.setAmount(currentAmount - amountToRemove);
                    amountToRemove = 0;
                }
            }
        }

//        for (ItemStack itemStack : player.getInventory()) {
//            if (amountToRemove <= 0) {
//                break;
//            }
//
//            if (itemStack != null && itemStack.isSimilar(targetItem) && itemStack.getType() != Material.AIR) {
//                int currentAmount = itemStack.getAmount();
//
//                if (currentAmount <= amountToRemove) {
//                    amountToRemove -= currentAmount;
//                    itemStack.setAmount(0);
//                } else {
//                    itemStack.setAmount(currentAmount - amountToRemove);
//                    amountToRemove = 0;
//                }
//            }
//        }
    }

    public static boolean isSameNBT(ItemStack item, ItemStack stack) {
        if (item.isSimilar(stack)) return true;
        Optional<String> customId = CustomItem.getCustomId(item.getItemMeta());
        Optional<String> customId1 = CustomItem.getCustomId(stack.getItemMeta());
        if (customId.isEmpty() || customId1.isEmpty()) return false;
        return customId.get().equals(customId1.get());
    }
}
