package me.xxgradzix.gradzixcombatsystem.weapons.instances;

import me.xxgradzix.gradzixcombatsystem.managers.ColorFixer;
import me.xxgradzix.gradzixcombatsystem.managers.CombatAttribute;
import me.xxgradzix.gradzixcombatsystem.managers.MessageManager;
import me.xxgradzix.gradzixcombatsystem.weapons.CustomWeapon;
import me.xxgradzix.gradzixcombatsystem.weapons.MelleWeapon;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static me.xxgradzix.gradzixcombatsystem.managers.MessageManager.getRomanNumerals;

public class BattleSword implements CustomWeapon, MelleWeapon {

    @Override
    public double getAttackDamage(int tier) {
        switch (tier) {
            case 1:
                return 5;
            case 2:
                return 6;
            case 3:
                return 7;
            case 4:
                return 8;
            case 5:
                return 9;
            default:
                return 0;
        }
    }

    @Override
    public double getAttackSpeed(int tier) {
        switch (tier) {
            case 1:
                return 1.5;
            case 2:
                return 1.6;
            case 3:
                return 1.7;
            case 4:
                return 1.7;
            case 5:
                return 1.8;
            default:
                return 0;
        }
    }

    @Override
    public int getRequiredAttribute(int tier, CombatAttribute attribute) {
        switch (tier){
            case 1 -> {
                if(attribute.equals(CombatAttribute.DEXTERITY)) return 3;
            }
            case 2 -> {
                if(attribute.equals(CombatAttribute.DEXTERITY)) return 4;
            }
            case 3 -> {
                if(attribute.equals(CombatAttribute.DEXTERITY)) return 6;
            }
            case 4 -> {
                if(attribute.equals(CombatAttribute.DEXTERITY)) return 8;
            }
            case 5 -> {
                if(attribute.equals(CombatAttribute.DEXTERITY)) return 10;
            }
        }
        return 0;
    }

    @Override
    public String getName(int tier) {
        return ColorFixer.addColors("#3e4040ᴅᴌᴜɢɪ ᴍɪᴇᴄᴢ " + getRomanNumerals(tier));
    }

    @Override
    public int getModelData(int tier) {
        return 5;
    }

    @Override
    public Material getMaterial(int tier) {
        return Material.IRON_SWORD;
    }

    @Override
    public void addEnchantments(int tier, ItemMeta meta) {

    }

    @Override
    public void setLoreAndName(ItemMeta meta, int tier) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(MessageManager.weaponDamageWithWords(getAttackDamage(tier)));
        lore.add(MessageManager.weaponSpeedWithWords(getAttackSpeed(tier)));
        lore.add(" ");
        lore.add(me.xxgradzix.gradzixcombatsystem.utils.ColorFixer.addColors("&7ᴀʙʏ ᴋᴏʀᴢʏꜱᴛᴀć ᴢ ᴛᴇɢᴏ ᴘʀᴢᴇᴅᴍɪᴏᴛᴜ ᴘᴏᴛʀᴢᴇʙᴜᴊᴇꜱᴢ:"));
        for (CombatAttribute combatAttribute : CombatAttribute.values()) {
            int requiredAttribute = getRequiredAttribute(tier, combatAttribute);
            if(requiredAttribute > 0) {
                lore.add(me.xxgradzix.gradzixcombatsystem.utils.ColorFixer.addColors(MessageManager.getAttributeFormatedName(combatAttribute, requiredAttribute)));
            }
        }
        meta.setLore(lore);
        meta.setDisplayName(getName(tier));

    }
}
