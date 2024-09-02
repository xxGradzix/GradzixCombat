package me.xxgradzix.gradzixcombatsystem.weapons.instances;

import me.xxgradzix.gradzixcombatsystem.managers.ColorFixer;
import me.xxgradzix.gradzixcombatsystem.managers.CombatAttribute;
import me.xxgradzix.gradzixcombatsystem.managers.MessageManager;
import me.xxgradzix.gradzixcombatsystem.weapons.CustomWeapon;
import me.xxgradzix.gradzixcombatsystem.weapons.ShootableWeapon;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static me.xxgradzix.gradzixcombatsystem.managers.MessageManager.getRomanNumerals;

public class BattleCrossBow implements CustomWeapon, ShootableWeapon {

    @Override
    public int getRequiredAttribute(int tier, CombatAttribute attribute) {
        switch (tier){
            case 1 -> {
                if(attribute.equals(CombatAttribute.STRENGTH)) return 3;
                if(attribute.equals(CombatAttribute.ENDURANCE)) return 1;
            }
            case 2 -> {
                if(attribute.equals(CombatAttribute.STRENGTH)) return 4;
                if(attribute.equals(CombatAttribute.ENDURANCE)) return 2;

            }
            case 3 -> {
                if(attribute.equals(CombatAttribute.STRENGTH)) return 6;
                if(attribute.equals(CombatAttribute.ENDURANCE)) return 3;

            }
            case 4 -> {
                if(attribute.equals(CombatAttribute.STRENGTH)) return 8;
                if(attribute.equals(CombatAttribute.ENDURANCE)) return 4;
            }
            case 5 -> {
                if(attribute.equals(CombatAttribute.STRENGTH)) return 10;
                if(attribute.equals(CombatAttribute.ENDURANCE)) return 5;
            }
        }
        return 0;
    }

    @Override
    public String getName(int tier) {
        return ColorFixer.addColors("#3e4040ᴄɪężᴋᴀ ᴋᴜꜱᴢᴀ " + getRomanNumerals(tier));
    }

    @Override
    public int getModelData(int tier) {
        return 5;
    }

    @Override
    public Material getMaterial(int tier) {
        return Material.CROSSBOW;
    }

    @Override
    public void addEnchantments(int tier, ItemMeta meta) {
        switch (tier) {
            case 1 -> {
                meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
            }
            case 2 -> {
                meta.addEnchant(Enchantment.ARROW_DAMAGE, 2, true);
            }
            case 3 -> {
                meta.addEnchant(Enchantment.ARROW_DAMAGE, 3, true);
            }
            case 4 -> {
                meta.addEnchant(Enchantment.ARROW_DAMAGE, 4, true);
            }
            case 5 -> {
                meta.addEnchant(Enchantment.ARROW_DAMAGE, 5, true);
            }
        }
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
