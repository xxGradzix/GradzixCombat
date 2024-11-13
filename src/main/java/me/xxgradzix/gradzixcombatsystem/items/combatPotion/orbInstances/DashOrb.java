package me.xxgradzix.gradzixcombatsystem.items.combatPotion.orbInstances;

import me.xxgradzix.gradzixcombatsystem.items.combatPotion.CustomBattlePotionOrb;
import me.xxgradzix.gradzixcombatsystem.listeners.combatHeroPotion.effectsEvents.DashOrbListener;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class DashOrb implements CustomBattlePotionOrb {

    public static final String CUSTOM_ID = "gradzixcombat_fallen_hero_potion_orb_dash";

    @Override
    public String getCustomId() {
        return CUSTOM_ID;
    }

    @Override
    public String getName(int tier) {
        return "ᴋʀʏꜱᴢᴛᴀł ᴢʀʏᴡᴜ";
    }

    @Override
    public int getModelData(int tier) {
        return 0;
    }

    @Override
    public void onDrink(Player player) {
        DashOrbListener.addPlayerWithDashEffect(player, 3 * 60);
    }

    @Override
    public Material getMaterial(int tier) {
        return Material.ENDER_PEARL;
    }

}
