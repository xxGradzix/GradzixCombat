package me.xxgradzix.gradzixcombatsystem.items.combatPotion;

import me.xxgradzix.gradzixcombatsystem.items.CustomItem;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public interface CustomBattlePotionOrb extends CustomItem {


    @Override
    default ItemStack getDefaultItemStack(Object... optionalArgs) {


        ItemStack itemStack = new ItemStack(getMaterial(1));

        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(getName(1));

        meta.setMaxStackSize(1);

        meta.setCustomModelData(getModelData(0));

        defaultSetItemCustomId(meta);

        setLoreAndName(meta, 1);
        hideAll(meta);

        itemStack.setItemMeta(meta);

        return itemStack;

    }

    @Override
    String getName(int tier);

    @Override
    int getModelData(int tier);

    @Override
    default void addBukkitEnchantments(int tier, ItemMeta meta) {
    }


    void onDrink(Player player);
}
