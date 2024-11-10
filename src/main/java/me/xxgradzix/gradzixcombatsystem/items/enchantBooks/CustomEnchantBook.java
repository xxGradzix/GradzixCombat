package me.xxgradzix.gradzixcombatsystem.items.enchantBooks;

import me.xxgradzix.gradzixcombatsystem.items.CustomItem;
import me.xxgradzix.gradzixcombatsystem.items.weapons.Tierable;
import me.xxgradzix.gradzixcombatsystem.managers.EnchantManager.EnchantManager;
import me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public interface CustomEnchantBook extends CustomItem, Tierable {

    @Override
    default String getName(int tier) {
        return ColorFixer.addColors("&7ᴋꜱɪęɢᴀ ") + getAssignedEnchant().enchantSuffix + " " + MessageManager.getRomanNumeralsForEnchant(tier);
    }

    EnchantManager.Enchant getAssignedEnchant();

    List<String> getEnchantDescription(int tier);

    @Override
    default ItemStack getDefaultItemStack(Object... optionalArgs) {

        int tier = 1;

        if(optionalArgs.length == 1) {
            if(optionalArgs[0] instanceof Integer) {
                tier = (int) optionalArgs[0];
            }
        }

        ItemStack itemStack = new ItemStack(getMaterial(1));

        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setMaxStackSize(1);
        itemMeta.setEnchantmentGlintOverride(false);

        itemMeta.setCustomModelData(getModelData(tier));

        setLoreAndName(itemMeta, tier);


        setTier(itemMeta, tier);

        itemStack.setItemMeta(itemMeta);

        return itemStack;

    }

    @Override
    default void addBukkitEnchantments(int tier, ItemMeta meta) {

    }

    @Override
    default void setLoreAndName(ItemMeta meta, int tier) {
        ArrayList<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.addAll(getEnchantDescription(tier));
        lore.add(" ");

        lore.add(ColorFixer.addColors("&7ᴀʙʏ ᴋᴏʀᴢʏꜱᴛᴀć ᴢ ʙʀᴏɴɪ ᴢ ᴛʏᴍ ᴢᴀᴋʟęᴄɪᴇᴍ"));
        lore.add(ColorFixer.addColors("&7ᴘᴏᴛʀᴢᴇʙᴀ ɪɴᴛᴇʟɪɢᴇɴᴄᴊɪ ɴᴀ ᴘᴏᴢɪᴏᴍɪᴇ ") + getAssignedEnchant().getIntLevelByTier(tier));

        meta.setLore(lore);

        meta.setDisplayName(getName(tier));

    }

}
