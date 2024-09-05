package me.xxgradzix.gradzixcombatsystem.managers.EnchantManager;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class EnchantManager {

    private static final int MAX_ENCHANTS_GLOBAL_LIMITER = 2;

    private static final NamespacedKey maxEnchantsSlots = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_max_enchant_slots");
    private static final NamespacedKey freeEnchantsSlots = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_free_enchant_slots");
    private static final NamespacedKey enchantsKEy = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_enchants");

    public static boolean addEnchant(ItemStack itemStack, String tempString) {

        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();


        int freeEnchantSlots = getFreeEnchantSlots(itemStack);

        if(freeEnchantSlots <= 0) return false;



        persistentDataContainer.set(enchantsKEy, PersistentDataType.);

    }

    public static int getMaxEnchantSlots(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        return persistentDataContainer.getOrDefault(maxEnchantsSlots, PersistentDataType.INTEGER, 0);
    }

    public static int getFreeEnchantSlots(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        return persistentDataContainer.getOrDefault(freeEnchantsSlots, PersistentDataType.INTEGER, 0);
    }


    private static boolean incrementMaxSlots(ItemStack itemStack, int amount) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();

        int maxEnchantSlots = getMaxEnchantSlots(itemStack);
        int freeEnchantSlots = getFreeEnchantSlots(itemStack);

        if (maxEnchantSlots + amount > MAX_ENCHANTS_GLOBAL_LIMITER) return false;
        persistentDataContainer.set(maxEnchantsSlots, PersistentDataType.INTEGER, maxEnchantSlots + amount);
        persistentDataContainer.set(freeEnchantsSlots, PersistentDataType.INTEGER, freeEnchantSlots + amount);
        return true;
    }

}
