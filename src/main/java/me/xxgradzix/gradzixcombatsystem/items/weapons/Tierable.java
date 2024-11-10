package me.xxgradzix.gradzixcombatsystem.items.weapons;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public interface Tierable {

    NamespacedKey weaponTierKey = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_weapon_tier");

    static int getTier(ItemStack itemStack) {
        return itemStack.getItemMeta().getPersistentDataContainer().getOrDefault(weaponTierKey, PersistentDataType.INTEGER, 0);
    }

    default void setTier(ItemStack itemStack, int tier) {
        ItemMeta meta = itemStack.getItemMeta();
        setTier(meta, tier);
        itemStack.setItemMeta(meta);
    }
    default void setTier(ItemMeta meta, int tier) {
        meta.getPersistentDataContainer().set(weaponTierKey, PersistentDataType.INTEGER, tier);
    }
}
