package me.xxgradzix.gradzixcombatsystem.weapons.instances;

import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.CombatAttribute;
import me.xxgradzix.gradzixcombatsystem.managers.EnchantManager.EnchantManager;
import me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager;
import me.xxgradzix.gradzixcombatsystem.managers.modifiersManager.ModifiersManager;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import me.xxgradzix.gradzixcombatsystem.weapons.CustomWeapon;
import me.xxgradzix.gradzixcombatsystem.weapons.EnchantableWeapon;
import me.xxgradzix.gradzixcombatsystem.weapons.MelleWeapon;
import me.xxgradzix.gradzixcombatsystem.weapons.ModifiableWeapon;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Set;

import static me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager.getRomanNumerals;

public class BattleSword implements CustomWeapon, MelleWeapon, EnchantableWeapon, ModifiableWeapon {

    public static final String CUSTOM_ID = "gradzixcombat_battle_sword";

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
    public String getCustomId() {
        return CUSTOM_ID;
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
//        return ColorFixer.addColors("#3e4040ᴅᴌᴜɢɪ ᴍɪᴇᴄᴢ " + getRomanNumerals(tier));
        return ColorFixer.addColors("ᴅᴌᴜɢɪ ᴍɪᴇᴄᴢ " + getRomanNumerals(tier));
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
    public void addBukkitEnchantments(int tier, ItemMeta meta) {

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

}
