package me.xxgradzix.gradzixcombatsystem.items;

import me.xxgradzix.gradzixcombatsystem.items.weapons.CustomWeapon;
import me.xxgradzix.gradzixcombatsystem.items.weapons.EnchantableWeapon;
import me.xxgradzix.gradzixcombatsystem.items.weapons.Tierable;
import me.xxgradzix.gradzixcombatsystem.managers.EnchantManager.EnchantManager;
import me.xxgradzix.gradzixcombatsystem.managers.modifiersManager.ModifiersManager;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class CustomItemNbtItemUtil {

    public static boolean isCustomItem(ItemStack itemStack) {
        return isCustomItem(itemStack.getItemMeta());
    }

    public static boolean isCustomItem(ItemMeta meta) {
        return CustomItem.getCustomId(meta).isPresent();
    }

    public static int calcItemAmount(Player player, ItemStack customItem) {

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
    }

    public static boolean isSameNBT(ItemStack item, ItemStack stack) {

        if (item.isSimilar(stack)) return true;

        Optional<String> customId = CustomItem.getCustomId(item.getItemMeta());
        Optional<String> customId1 = CustomItem.getCustomId(stack.getItemMeta());
        if (customId.isEmpty() || customId1.isEmpty()) return false;
        if(!customId.get().equals(customId1.get())) return false;

        if(!EnchantManager.getEnchants(item).equals(EnchantManager.getEnchants(stack))) return false;
        if(!ModifiersManager.getModifierName(item.getItemMeta()).equals(ModifiersManager.getModifierName(stack.getItemMeta()))) return false;

        CustomItem weaponType1 = CustomItemManager.getCustomItem(item);
        CustomItem weaponType2 = CustomItemManager.getCustomItem(stack);

        if(weaponType1 != null && weaponType2 != null) {
            if(weaponType1 instanceof Tierable && weaponType2 instanceof Tierable) {
                int tier = Tierable.getTier(item);
                int tier1 = Tierable.getTier(stack);
                if(tier != tier1) return false;
            }
        }

        return true;
    }

    public static List<ItemStack> getItemWithCustomId(Player player, NamespacedKey key, String customId) {

        List<ItemStack> items = new ArrayList<>();

        for (ItemStack itemStack : player.getInventory()) {
            if (itemStack == null || !isCustomItem(itemStack)) continue;

            ItemMeta meta = itemStack.getItemMeta();
            if (meta == null) continue;

            if (customId.equals(meta.getPersistentDataContainer().get(key, PersistentDataType.STRING))) {
                items.add(itemStack);
            }
        }
        return items;

    }
}
