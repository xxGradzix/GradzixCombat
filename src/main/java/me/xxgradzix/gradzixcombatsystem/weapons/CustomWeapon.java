package me.xxgradzix.gradzixcombatsystem.weapons;

import me.xxgradzix.gradzixcombatsystem.managers.AttributeManager;
import me.xxgradzix.gradzixcombatsystem.managers.CombatAttribute;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public interface CustomWeapon {

    int getRequiredAttribute(int tier, CombatAttribute attribute);
    boolean canUse(int tier, Player player);

    int getModelData(int tier);

    ItemStack getItemStack(int tier);

    default void setAttributes(ItemStack itemStack, int tier) {
        for (CombatAttribute combatAttribute : CombatAttribute.values()) {
            int requiredAttribute = getRequiredAttribute(tier, combatAttribute);
            if(requiredAttribute != 0) {
                AttributeManager.setAttributeRequirement(itemStack, combatAttribute, requiredAttribute);
            }
        }
    }
    default void setLoreAndName(ItemStack itemStack, int tier) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ColorFixer.addColors("&7ᴀʙʏ ᴋᴏʀᴢʏꜱᴛᴀć ᴢ ᴛᴇɢᴏ ᴘʀᴢᴇᴅᴍɪᴏᴛᴜ ᴘᴏᴛʀᴢᴇʙᴜᴊᴇꜱᴢ:"));
        for (CombatAttribute combatAttribute : CombatAttribute.values()) {
            int requiredAttribute = getRequiredAttribute(tier, combatAttribute);
            if(requiredAttribute != 0) {
                lore.add("§7" + combatAttribute.getName() + ": " + requiredAttribute);
            }
        }
        itemStack.getItemMeta().setLore(lore);
        itemStack.getItemMeta().setDisplayName("§6" + this.getClass().getSimpleName());
    }

}
