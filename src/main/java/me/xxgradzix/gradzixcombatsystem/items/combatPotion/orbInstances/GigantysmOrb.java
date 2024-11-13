package me.xxgradzix.gradzixcombatsystem.items.combatPotion.orbInstances;

import me.xxgradzix.gradzixcombatsystem.items.combatPotion.CustomBattlePotionOrb;
import me.xxgradzix.gradzixcombatsystem.listeners.combatHeroPotion.effectsEvents.GigantysmOrbListener;
import me.xxgradzix.gradzixcombatsystem.managers.magicEffects.MagicEffectManager;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class GigantysmOrb implements CustomBattlePotionOrb {

    public static final String CUSTOM_ID = "gradzixcombat_fallen_hero_potion_orb_gigantism";

    @Override
    public String getCustomId() {
        return CUSTOM_ID;
    }

    @Override
    public String getName(int tier) {
        return "ᴋʀʏꜱᴢᴛᴀł ɢɪɢᴀɴᴛʏᴢᴍᴜ";
    }

    @Override
    public int getModelData(int tier) {
        return 0;
    }

    @Override
    public void onDrink(Player player) {

        boolean equals = player.getGameMode().equals(GameMode.CREATIVE);
        GigantysmOrbListener.addPlayer(player, 3*60, equals);
    }

    @Override
    public Material getMaterial(int tier) {
        return Material.HEAVY_CORE;
    }

}
