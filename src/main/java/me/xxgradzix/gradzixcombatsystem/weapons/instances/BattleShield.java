package me.xxgradzix.gradzixcombatsystem.weapons.instances;

import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.CombatAttribute;
import me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import me.xxgradzix.gradzixcombatsystem.weapons.CustomWeapon;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class BattleShield implements CustomWeapon {
    public static final String CUSTOM_ID = "gradzixcombat_battle_shield";

    @Override
    public void setWeaponCustomId(ItemMeta meta) {
        meta.getPersistentDataContainer().set(weaponCustomIdKey, PersistentDataType.STRING, CUSTOM_ID);
    }
    @Override
    public int getRequiredAttribute(int tier, CombatAttribute attribute) {

        if(attribute.equals(CombatAttribute.STRENGTH)) return 5;
        if(attribute.equals(CombatAttribute.ENDURANCE)) return 5;
        return 0;
    }

    @Override
    public String getName(int tier) {
        return ColorFixer.addColors("ᴛᴀʀᴄᴢᴀ ");
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
    public void addBukkitEnchantments(int tier, ItemMeta meta) {

    }

}
