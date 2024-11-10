package me.xxgradzix.gradzixcombatsystem.items;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public interface Upgradable {


    List<ItemStack> getRequiredItems(int tier);
    int getRequiredMoney(int tier);
    boolean isLowerTierItemRequired(int tier);

}
