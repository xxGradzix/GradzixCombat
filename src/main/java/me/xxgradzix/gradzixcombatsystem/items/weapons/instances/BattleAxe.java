package me.xxgradzix.gradzixcombatsystem.items.weapons.instances;

import me.xxgradzix.gradzixcombatsystem.managers.EnchantManager.EnchantManager;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.CombatAttribute;
import me.xxgradzix.gradzixcombatsystem.managers.modifiersManager.ModifiersManager;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import me.xxgradzix.gradzixcombatsystem.items.weapons.CustomWeapon;
import me.xxgradzix.gradzixcombatsystem.items.weapons.EnchantableWeapon;
import me.xxgradzix.gradzixcombatsystem.items.weapons.MelleWeapon;
import me.xxgradzix.gradzixcombatsystem.items.weapons.ModifiableWeapon;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Set;

import static me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager.getRomanNumerals;

public class BattleAxe implements CustomWeapon, MelleWeapon, EnchantableWeapon, ModifiableWeapon {

    public static final String CUSTOM_ID = "gradzixcombat_battle_axe";

    @Override
    public String getCustomId() {
        return CUSTOM_ID;
    }


    @Override
    public double getAttackDamage(int tier) {
        switch (tier) {
            case 1:
                return 6;
            case 2:
                return 7;
            case 3:
                return 8;
            case 4:
                return 9;
            case 5:
                return 10;
            default:
                return 0;
        }
    }

    @Override
    public double getAttackSpeed(int tier) {
        switch (tier) {
            case 1:
                return 0.6;
            case 2:
                return 0.6;
            case 3:
                return 0.65;
            case 4:
                return 0.7;
            case 5:
                return 0.75;
            default:
                return 0;
        }
    }

    @Override
    public int getRequiredAttribute(int tier, CombatAttribute attribute) {
        switch (tier){
            case 1 -> {
                if(attribute.equals(CombatAttribute.STRENGTH)) return 3;
            }
            case 2 -> {
                if(attribute.equals(CombatAttribute.STRENGTH)) return 4;
            }
            case 3 -> {
                if(attribute.equals(CombatAttribute.STRENGTH)) return 6;
            }
            case 4 -> {
                if(attribute.equals(CombatAttribute.STRENGTH)) return 8;
            }
            case 5 -> {
                if(attribute.equals(CombatAttribute.STRENGTH)) return 10;
            }
        }
        return 0;
    }


    @Override
    public String getName(int tier) {
        return ColorFixer.addColors("ᴛᴏᴘóʀ ʙᴏᴊᴏᴡʏ " + getRomanNumerals(tier));
    }

    @Override
    public int getModelData(int tier) {
        return 5;
    }

    @Override
    public Material getMaterial(int tier) {
        return Material.IRON_AXE;
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
