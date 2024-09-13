package me.xxgradzix.gradzixcombatsystem.weapons;

import me.xxgradzix.gradzixcombatsystem.managers.EnchantManager.EnchantManager;
import me.xxgradzix.gradzixcombatsystem.managers.modifiersManager.ModifiersManager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Set;

public interface ModifiableWeapon {

    default ArrayList<String> getQualityLore(ItemMeta meta) {
        return ModifiersManager.getModifiersLore(meta);
    }

    default String getQualityName(ItemMeta meta) {
        return ModifiersManager.getModifierName(meta);
    }

    Set<Class> getApplicableModifications();

}
