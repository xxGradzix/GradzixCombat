package me.xxgradzix.gradzixcombatsystem.armors.instances;

import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Set;

public interface UpgradableArmor {

    public List<ItemStack> getRequiredItems(int tier);
    public int getRequiredMoney(int tier);
    public boolean isLowerTierItemRequired(int tier);

}
