package me.xxgradzix.gradzixcombatsystem.items.combatPotion.orbInstances;

import me.xxgradzix.gradzixcombatsystem.items.combatPotion.CustomBattlePotionOrb;
import me.xxgradzix.gradzixcombatsystem.listeners.combatHeroPotion.effectsEvents.ExplosionOrbListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class VolcanoOrb implements CustomBattlePotionOrb {

    public static final String CUSTOM_ID = "gradzixcombat_fallen_hero_potion_orb_volcano";

    @Override
    public String getCustomId() {
        return CUSTOM_ID;
    }

    @Override
    public String getName(int tier) {
        return "ᴏᴅłᴀᴍᴇᴋ ᴡᴜʟᴋᴀɴɪᴄᴢɴʏ";
    }

    @Override
    public int getModelData(int tier) {
        return 0;
    }

    @Override
    public void onDrink(Player player) {
        ExplosionOrbListener.addPlayerWithExplosionEffect(player.getUniqueId(), 3 * 60);
    }

    @Override
    public Material getMaterial(int tier) {
        return Material.FIRE_CHARGE;
    }

}
