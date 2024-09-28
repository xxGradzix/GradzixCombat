package me.xxgradzix.gradzixcombatsystem.items.armors;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface UpgradableArmor {

    public List<ItemStack> getRequiredItems(int tier);
    public int getRequiredMoney(int tier);
    public boolean isLowerTierItemRequired(int tier);

}
