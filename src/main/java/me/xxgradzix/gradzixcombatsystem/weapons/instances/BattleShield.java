package me.xxgradzix.gradzixcombatsystem.weapons.instances;

import me.xxgradzix.gradzixcombatsystem.managers.ColorFixer;
import me.xxgradzix.gradzixcombatsystem.managers.CombatAttribute;
import me.xxgradzix.gradzixcombatsystem.managers.MessageManager;
import me.xxgradzix.gradzixcombatsystem.weapons.CustomWeapon;
import me.xxgradzix.gradzixcombatsystem.weapons.MelleWeapon;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static me.xxgradzix.gradzixcombatsystem.managers.MessageManager.getRomanNumerals;

public class BattleShield implements CustomWeapon {

    @Override
    public int getRequiredAttribute(int tier, CombatAttribute attribute) {

        if(attribute.equals(CombatAttribute.STRENGTH)) return 5;
        if(attribute.equals(CombatAttribute.ENDURANCE)) return 5;
        return 0;
    }

    @Override
    public String getName(int tier) {
        return ColorFixer.addColors("#3e4040ᴛᴀʀᴄᴢᴀ");
    }

    @Override
    public int getModelData(int tier) {
        return 0;
    }

    @Override
    public Material getMaterial(int tier) {
        return Material.SHIELD;
    }

    @Override
    public void addEnchantments(int tier, ItemMeta meta) {

    }

    @Override
    public void setLoreAndName(ItemMeta meta, int tier) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(me.xxgradzix.gradzixcombatsystem.utils.ColorFixer.addColors("&7ᴀʙʏ ᴋᴏʀᴢʏꜱᴛᴀć ᴢ ᴛᴇɢᴏ ᴘʀᴢᴇᴅᴍɪᴏᴛᴜ ᴘᴏᴛʀᴢᴇʙᴜᴊᴇꜱᴢ:"));
        for (CombatAttribute combatAttribute : CombatAttribute.values()) {
            int requiredAttribute = getRequiredAttribute(tier, combatAttribute);
            if(requiredAttribute != 0) {
                lore.add(me.xxgradzix.gradzixcombatsystem.utils.ColorFixer.addColors(MessageManager.getAttributeFormatedName(combatAttribute, requiredAttribute)));
            }
        }
        meta.setLore(lore);
        meta.setDisplayName(getName(tier));

    }
}
