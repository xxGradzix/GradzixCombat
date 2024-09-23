package me.xxgradzix.gradzixcombatsystem.weapons.instances;

import me.xxgradzix.gradzixcombatsystem.managers.EnchantManager.EnchantManager;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.CombatAttribute;
import me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager;
import me.xxgradzix.gradzixcombatsystem.managers.modifiersManager.ModifiersManager;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import me.xxgradzix.gradzixcombatsystem.weapons.CustomWeapon;
import me.xxgradzix.gradzixcombatsystem.weapons.EnchantableWeapon;
import me.xxgradzix.gradzixcombatsystem.weapons.ModifiableWeapon;
import me.xxgradzix.gradzixcombatsystem.weapons.ShootableWeapon;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Set;

import static me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager.getRomanNumerals;

public class BattleCrossBow implements CustomWeapon, ShootableWeapon, EnchantableWeapon, ModifiableWeapon {

    public static final String CUSTOM_ID = "gradzixcombat_battle_crossbow";


    @Override
    public String getCustomId() {
        return CUSTOM_ID;
    }

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
        return ColorFixer.addColors("ᴄɪężᴋᴀ ᴋᴜꜱᴢᴀ " + getRomanNumerals(tier));
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
    public void addBukkitEnchantments(int tier, ItemMeta meta) {
        switch (tier) {
            case 1 -> {
                meta.addEnchant(Enchantment.POWER, 1, true);
            }
            case 2 -> {
                meta.addEnchant(Enchantment.POWER, 2, true);
            }
            case 3 -> {
                meta.addEnchant(Enchantment.POWER, 3, true);
            }
            case 4 -> {
                meta.addEnchant(Enchantment.POWER, 4, true);
            }
            case 5 -> {
                meta.addEnchant(Enchantment.POWER, 5, true);
            }
        }
    }
    @Override
    public void setEnchantSlots(ItemStack itemStack, int tier) {
        if(tier >= 3) EnchantManager.setMaxSlots(itemStack, 1);

    }

    @Override
    public Set<EnchantManager.Enchant> getApplicableEnchants(int tier) {
        return Set.of(EnchantManager.Enchant.LIFE_STEAL, EnchantManager.Enchant.BLOOD_LOSS);
    }

    @Override
    public Set<Class> getApplicableModifications() {
        return Set.of(ModifiersManager.RangeModifier.class, ModifiersManager.UniversalModifier.class);
    }
}
