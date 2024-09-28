package me.xxgradzix.gradzixcombatsystem.items.weapons.instances;

import me.xxgradzix.gradzixcombatsystem.managers.EnchantManager.EnchantManager;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.CombatAttribute;
import me.xxgradzix.gradzixcombatsystem.managers.modifiersManager.ModifiersManager;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import me.xxgradzix.gradzixcombatsystem.items.weapons.*;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Set;

import static me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager.getRomanNumerals;

public class BattleSpear implements CustomWeapon, MelleWeapon, ThrowableWeapon, EnchantableWeapon, ModifiableWeapon, RangeChangeWeapon {

    public static final String CUSTOM_ID = "gradzixcombat_battle_spear";


    @Override
    public String getCustomId() {
        return CUSTOM_ID;
    }
    @Override
    public double getAttackDamage(int tier) {
        switch (tier) {
            case 1:
                return 4;
            case 2:
                return 5;
            case 3:
                return 6;
            case 4:
                return 7;
            case 5:
                return 8;
            default:
                return 0;
        }
    }

    @Override
    public double getAttackSpeed(int tier) {
        switch (tier) {
            case 1:
                return 0.8;
            case 2:
                return 0.9;
            case 3:
                return 0.9;
            case 4:
                return 1.0;
            case 5:
                return 1.1;
            default:
                return 0;
        }
    }

    @Override
    public int getRequiredAttribute(int tier, CombatAttribute attribute) {
        switch (tier){
            case 1 -> {
                if(attribute.equals(CombatAttribute.STRENGTH)) return 1;
                if(attribute.equals(CombatAttribute.ENDURANCE)) return 2;
            }
            case 2 -> {
                if(attribute.equals(CombatAttribute.STRENGTH)) return 2;
                if(attribute.equals(CombatAttribute.ENDURANCE)) return 3;

            }
            case 3 -> {
                if(attribute.equals(CombatAttribute.STRENGTH)) return 3;
                if(attribute.equals(CombatAttribute.ENDURANCE)) return 4;

            }
            case 4 -> {
                if(attribute.equals(CombatAttribute.STRENGTH)) return 4;
                if(attribute.equals(CombatAttribute.ENDURANCE)) return 5;

            }
            case 5 -> {
                if(attribute.equals(CombatAttribute.STRENGTH)) return 5;
                if(attribute.equals(CombatAttribute.ENDURANCE)) return 6;
            }
        }
        return 0;
    }


    @Override
    public String getName(int tier) {
        return ColorFixer.addColors("ᴡᴌóᴄᴢɴɪᴀ " + getRomanNumerals(tier));
    }

    @Override
    public int getModelData(int tier) {
        return 5;
    }

    @Override
    public Material getMaterial(int tier) {
        return Material.TRIDENT;
    }

    @Override
    public void addBukkitEnchantments(int tier, ItemMeta meta) {
        meta.addEnchant(Enchantment.LOYALTY, 1, true);
    }

    @Override
    public void setEnchantSlots(ItemStack itemStack, int tier) {
        if(tier >= 3) EnchantManager.setMaxSlots(itemStack, 1);
    }

    @Override
    public Set<EnchantManager.Enchant> getApplicableEnchants(int tier) {
        return Set.of(EnchantManager.Enchant.LIFE_STEAL, EnchantManager.Enchant.FREEZE, EnchantManager.Enchant.ATTACK_COMBO);
    }

    @Override
    public Set<Class> getApplicableModifications() {
        return Set.of(ModifiersManager.MelleModifier.class, ModifiersManager.UniversalModifier.class, ModifiersManager.CommonModifier.class);
    }

    @Override
    public double getRange(int tier) {
        return 1;
    }
}
