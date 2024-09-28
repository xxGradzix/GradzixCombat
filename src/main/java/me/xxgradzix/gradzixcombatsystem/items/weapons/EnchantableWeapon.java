package me.xxgradzix.gradzixcombatsystem.items.weapons;

import me.xxgradzix.gradzixcombatsystem.managers.EnchantManager.EnchantManager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Set;

public interface EnchantableWeapon {

    void setEnchantSlots(ItemStack itemStack, int tier);

    default ArrayList<String> getEnchantLore(ItemMeta meta){
        return EnchantManager.getEnchantLore(meta);
    }

    Set<EnchantManager.Enchant> getApplicableEnchants(int tier);

}
