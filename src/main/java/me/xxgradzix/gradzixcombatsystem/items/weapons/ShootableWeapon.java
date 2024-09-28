package me.xxgradzix.gradzixcombatsystem.items.weapons;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public interface ShootableWeapon {

    static final NamespacedKey weaponDamageKey = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_range_weapon_damage");

    static double getWeaponDamage(ItemStack bow) {
        if(bow == null) return 0;
        return bow.getItemMeta().getPersistentDataContainer().getOrDefault(weaponDamageKey, PersistentDataType.DOUBLE, 0.0);
    }

    default void setWeaponDamage(ItemMeta meta, double damage) {
        meta.getPersistentDataContainer().set(weaponDamageKey, PersistentDataType.DOUBLE, damage);

    }
    double getWeaponDamage(int tier);
}
