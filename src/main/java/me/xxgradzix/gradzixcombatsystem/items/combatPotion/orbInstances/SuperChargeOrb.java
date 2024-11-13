package me.xxgradzix.gradzixcombatsystem.items.combatPotion.orbInstances;

import me.xxgradzix.gradzixcombatsystem.items.combatPotion.CustomBattlePotionOrb;
import me.xxgradzix.gradzixcombatsystem.listeners.combatHeroPotion.effectsEvents.DashOrbListener;
import me.xxgradzix.gradzixcombatsystem.managers.magicEffects.MagicEffectManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Set;

public class SuperChargeOrb implements CustomBattlePotionOrb {

    public static final String CUSTOM_ID = "gradzixcombat_fallen_hero_potion_orb_super_charge";

    @Override
    public String getCustomId() {
        return CUSTOM_ID;
    }

    @Override
    public String getName(int tier) {
        return "ᴋʀʏꜱᴢᴛᴀłᴏᴡᴀ ᴋᴜʟᴀ ᴇɴᴇʀɢɪɪ ᴍᴀɢɪᴄᴢɴᴇᴊ";
    }

    @Override
    public int getModelData(int tier) {
        return 0;
    }

    @Override
    public void onDrink(Player player) {
        MagicEffectManager.superChargePlayer(player, 2*60, Set.of(MagicEffectManager.MagicEffectType.values()));
    }

    @Override
    public Material getMaterial(int tier) {
        return Material.NETHER_STAR;
    }

}
