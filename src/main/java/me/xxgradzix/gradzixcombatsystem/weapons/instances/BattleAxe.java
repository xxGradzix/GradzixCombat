package me.xxgradzix.gradzixcombatsystem.weapons.instances;

import me.xxgradzix.gradzixcombatsystem.managers.CombatAttribute;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import me.xxgradzix.gradzixcombatsystem.weapons.CustomWeapon;
import me.xxgradzix.gradzixcombatsystem.weapons.MelleWeapon;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static me.xxgradzix.gradzixcombatsystem.managers.MessageManager.getRomanNumerals;

public class BattleAxe implements CustomWeapon, MelleWeapon {

    @Override
    public double getAttackDamage(int tier) {
        switch (tier) {
            case 1:
                return 7;
            case 2:
                return 8;
            case 3:
                return 9;
            case 4:
                return 10;
            case 5:
                return 11;
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
                if(attribute == CombatAttribute.STRENGTH) return 3;
            }
            case 2 -> {
                if(attribute == CombatAttribute.STRENGTH) return 4;
            }
            case 3 -> {
                if(attribute == CombatAttribute.STRENGTH) return 6;
            }
            case 4 -> {
                if(attribute == CombatAttribute.STRENGTH) return 8;
            }
            case 5 -> {
                if(attribute == CombatAttribute.STRENGTH) return 10;
            }
        }
        return 9999;
    }

    @Override
    public boolean canUse(int tier, Player player) {
        return false;
    }

    @Override
    public int getModelData(int tier) {
        return 5;
    }

    @Override
    public ItemStack getItemStack(int tier) {
        ItemStack itemStack = new ItemStack(Material.IRON_AXE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ColorFixer.addColors("#3e4040ᴛᴏᴘóʀ ʙᴏᴊᴏᴡʏ" + " " + getRomanNumerals(tier)));

        setAttributes(itemStack, tier);


        return null;
    }
}
