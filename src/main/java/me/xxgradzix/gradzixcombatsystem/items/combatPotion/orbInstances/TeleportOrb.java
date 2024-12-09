package me.xxgradzix.gradzixcombatsystem.items.combatPotion.orbInstances;

import me.xxgradzix.gradzixcombatsystem.items.combatPotion.CustomBattlePotionOrb;
import me.xxgradzix.gradzixcombatsystem.listeners.combatHeroPotion.effectsEvents.TeleportOrbListener;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class TeleportOrb implements CustomBattlePotionOrb {

    public static final String CUSTOM_ID = "gradzixcombat_fallen_hero_potion_orb_teleport";

    @Override
    public String getCustomId() {
        return CUSTOM_ID;
    }

    @Override
    public String getName(int tier) {
        return "ᴋʀʏꜱᴢᴛᴀł ᴘʀᴢᴇꜱᴋᴏᴋᴜ ᴄᴢᴀꜱᴏᴘʀᴢᴇꜱᴛʀᴢᴇɴɴᴇɢᴏ";
    }

    @Override
    public int getModelData(int tier) {
        return 77012;
    }

    @Override
    public void onDrink(Player player) {
        TeleportOrbListener.addPlayerWithBackTeleportEffect(player, 3 * 60);
    }

    @Override
    public Material getMaterial(int tier) {
        return Material.FLINT;
    }

}
