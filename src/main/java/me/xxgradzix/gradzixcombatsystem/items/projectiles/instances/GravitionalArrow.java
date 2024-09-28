package me.xxgradzix.gradzixcombatsystem.items.projectiles.instances;

import me.xxgradzix.gradzixcombatsystem.items.projectiles.CustomArrow;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.CombatAttribute;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;

import static me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager.getRomanNumerals;

public class GravitionalArrow implements CustomArrow {
    @Override
    public double getArrowDamage(int tier) {
        switch (tier) {
            case 1:
                return 1;
            case 2:
                return 1.5;
            case 3:
                return 2.0;
            case 4:
                return 2.5;
            case 5:
                return 3;
            default:
                return 0;
        }
    }

    public static final String CUSTOM_ID = "gradzixcombat_gravitional_arrow";

    @Override
    public String getCustomId() {
        return CUSTOM_ID;
    }

    @Override
    public int getModelData(int tier) {
        return 0;
    }

    @Override
    public int getRequiredAttribute(int tier, CombatAttribute attribute) {

        return 0;
    }

    @Override
    public String getName(int tier) {
        return ColorFixer.addColors("#702b9eꜱᴛʀᴢᴀᴌᴀ ɢʀᴀᴡɪᴛᴀᴄʏᴊɴᴀ " + getRomanNumerals(tier));
    }

}
