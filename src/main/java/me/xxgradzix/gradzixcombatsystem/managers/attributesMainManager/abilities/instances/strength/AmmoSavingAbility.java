package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.strength;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.StrengthOrigin;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.CombatAbility;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.EventableAbility;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public enum AmmoSavingAbility implements CombatAbility, StrengthOrigin, EventableAbility {

    INSTANCE;

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_strength_ammo_saving");
    }

    @Override
    public void applyAbility(Player player, int level) {
        if(level > getMaxAbilityLevel()) level = getMaxAbilityLevel();
        removeAbility(player);
        setAbilityLevel(player, level);
    }

    @Override
    public void removeAbility(Player player) {
        setAbilityLevel(player, 0);
    }

    @Override
    public int getRequiredAttributeLevel() {
        return 3;
    }

    @Override
    public int getMaxAbilityLevel() {
        return 1;
    }

    @Override
    public Set<CombatAbility> getRequiredAbilities() {
        return Set.of();
    }

    @Override
    public int getRow() {
        return 1;
    }

    @Override
    public int getColumn() {
        return 7;
    }

    @Override
    public String getAbilityName() {
        return "&#C8B048&lᴏ&#C0AB44&lꜱ&#B8A641&lᴢ&#B0A13D&lᴄ&#A99C3A&lᴢ&#A19736&lę&#999132&lᴅ&#918C2F&lᴢ&#89872B&lᴀ&#818228&lɴ&#7A7D24&lɪ&#727820&lᴇ &#626E19&lᴀ&#5A6916&lᴍ&#526412&lᴜ&#4A5E0E&lɴ&#43590B&lɪ&#3B5407&lᴄ&#334F04&lᴊ&#2B4A00&lɪ";
    }



    @Override
    public List<String> getAbilityDescription(int playerLevel) {

        ArrayList<String> lore = new ArrayList<>();
        switch (playerLevel) {

            case 0 -> {
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴋᴜꜱᴢᴀ ᴍᴀ ꜱᴢᴀɴꜱᴇ ɴᴀ ɴɪᴇᴢᴜżʏᴄɪᴇ ꜱᴛʀᴢᴀłʏ"));
                lore.add(ColorFixer.addColors("&7ꜱᴢᴀɴꜱᴀ ɴᴀ ɴɪᴇᴢᴜżʏᴄɪᴇ ꜱᴛʀᴢᴀłʏ ᴡʏɴᴏꜱɪ §a10%"));

            }
            case 1 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴋᴜꜱᴢᴀ ᴍᴀ ꜱᴢᴀɴꜱᴇ ɴᴀ ɴɪᴇᴢᴜżʏᴄɪᴇ ꜱᴛʀᴢᴀłʏ"));
                lore.add(ColorFixer.addColors("&7ꜱᴢᴀɴꜱᴀ ɴᴀ ɴɪᴇᴢᴜżʏᴄɪᴇ ꜱᴛʀᴢᴀłʏ ᴡʏɴᴏꜱɪ §a10%"));

            }

        }
        return lore;
    }

    @Override
    public Listener getListener() {
        return new Listener() {

            @EventHandler
            public void projectileShootEvent(EntityShootBowEvent event) {

                if(!(event.getEntity() instanceof Player player)) return;

                int abilityLevel = getAbilityLevel(player);

                if(abilityLevel <= 0) return;

                if(!Material.CROSSBOW.equals(event.getBow().getType())) return;

                if(Math.random() > 0.1) return;

                Bukkit.broadcastMessage("test Ammo saving ability triggered");
                event.setConsumeItem(false);


            }

        };
    }
}
