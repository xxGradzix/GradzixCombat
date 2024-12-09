package me.xxgradzix.gradzixcombatsystem.items.combatPotion.orbInstances;

import me.xxgradzix.gradzixcombatsystem.items.combatPotion.CustomBattlePotionOrb;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HeartOrb implements CustomBattlePotionOrb {

    public static final String CUSTOM_ID = "gradzixcombat_fallen_hero_potion_orb_heart";

    @Override
    public String getCustomId() {
        return CUSTOM_ID;
    }

    @Override
    public String getName(int tier) {
        return "ᴋʀʏꜱᴢᴛᴀł żʏᴄɪᴏᴡᴇᴊ ᴇɴᴇʀɢɪɪ";
    }

    @Override
    public int getModelData(int tier) {
        return 77007;
    }

    @Override
    public void onDrink(Player player) {

        player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 60 * 3 * 20, 1, true, false, false));

    }

    @Override
    public Material getMaterial(int tier) {
        return Material.FLINT;
    }


}
