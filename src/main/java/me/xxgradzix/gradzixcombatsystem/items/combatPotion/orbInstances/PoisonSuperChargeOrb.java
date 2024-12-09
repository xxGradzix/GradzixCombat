package me.xxgradzix.gradzixcombatsystem.items.combatPotion.orbInstances;

import me.xxgradzix.gradzixcombatsystem.items.combatPotion.CustomBattlePotionOrb;
import me.xxgradzix.gradzixcombatsystem.managers.magicEffects.MagicEffectManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Set;

public class PoisonSuperChargeOrb implements CustomBattlePotionOrb {

    public static final String CUSTOM_ID = "gradzixcombat_fallen_hero_potion_orb_super_charge_poison";

    @Override
    public String getCustomId() {
        return CUSTOM_ID;
    }

    @Override
    public String getName(int tier) {
        return "ᴋʀʏꜱᴢᴛᴀł ᴛʀᴜᴄɪᴢɴʏ";
    }

    @Override
    public int getModelData(int tier) {
        return 77010;
    }

    @Override
    public void onDrink(Player player) {
        MagicEffectManager.superChargePlayer(player, 3*60, Set.of(MagicEffectManager.MagicEffectType.POISON));
    }

    @Override
    public Material getMaterial(int tier) {
        return Material.FLINT;
    }

}
