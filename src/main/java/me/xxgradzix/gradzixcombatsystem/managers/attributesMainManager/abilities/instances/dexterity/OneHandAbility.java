package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.dexterity;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.CombatAbility;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.EventableAbility;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.DexterityOrigin;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public enum OneHandAbility implements CombatAbility, DexterityOrigin, EventableAbility {

    INSTANCE;

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_dexterity_one_hand");
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
        return 1;
    }

    @Override
    public int getMaxAbilityLevel() {
        return 3;
    }

    @Override
    public Set<CombatAbility> getRequiredAbilities() {
        return Set.of();
    }

    @Override
    public int getRow() {
        return 0;
    }

    @Override
    public int getColumn() {
        return 3;
    }

    @Override
    public Listener getListener() {
        return new Listener() {

            @EventHandler
            public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

                if(!(event.getDamager() instanceof Player damager)) return;

                // TODO do usuniecia
                Bukkit.broadcastMessage("One hand ability triggered");
                event.setDamage(event.getDamage() * (1 + getAbilityLevel(damager) * 0.4));
            }
        };
    }

    @Override
    public String getAbilityName() {
        return "&#581717&lᴍ&#5F1616&lɪ&#671414&lꜱ&#6E1313&lᴛ&#761111&lʀ&#7D1010&lᴢ &#8C0D0D&lꜱ&#940C0C&lᴢ&#9B0A0A&lᴇ&#A20909&lʀ&#AA0707&lᴍ&#B10606&lɪ&#B90404&lᴇ&#C00303&lʀ&#C80101&lᴋ&#CF0000&lɪ";
    }


    @Override
    public List<String> getAbilityDescription(int playerLevel) {
        ArrayList<String> lore = new ArrayList<>();

        switch (playerLevel) {

            case 0 -> {
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));

                lore.add(ColorFixer.addColors("&7ɢᴅʏ ᴜżʏᴡᴀꜱᴢ ᴍɪᴇᴄᴢᴀ ɪ ᴅʀᴜɢᴀ ʀęᴋᴀ ᴊᴇꜱᴛ ᴡᴏʟɴᴀ"));
                lore.add(ColorFixer.addColors("&7ᴢᴀᴅᴀᴊᴇꜱᴢ ᴅᴏᴅᴀᴛᴋᴏᴡᴇ &a5 ᴏʙʀᴀżᴇń"));
            }
            case 1 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));

                lore.add(ColorFixer.addColors("&7ɢᴅʏ ᴜżʏᴡᴀꜱᴢ ᴍɪᴇᴄᴢᴀ ɪ ᴅʀᴜɢᴀ ʀęᴋᴀ ᴊᴇꜱᴛ ᴡᴏʟɴᴀ"));
                lore.add(ColorFixer.addColors("&7ᴢᴀᴅᴀᴊᴇꜱᴢ ᴅᴏᴅᴀᴛᴋᴏᴡᴇ 5 ᴏʙʀᴀżᴇń"));

                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));

                lore.add(ColorFixer.addColors("&7ɢᴅʏ ᴜżʏᴡᴀꜱᴢ ᴍɪᴇᴄᴢᴀ ɪ ᴅʀᴜɢᴀ ʀęᴋᴀ ᴊᴇꜱᴛ ᴡᴏʟɴᴀ"));
                lore.add(ColorFixer.addColors("&7ᴢᴀᴅᴀᴊᴇꜱᴢ ᴅᴏᴅᴀᴛᴋᴏᴡᴇ &a10 ᴏʙʀᴀżᴇń"));
            }
            case 2 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));

                lore.add(ColorFixer.addColors("&7ɢᴅʏ ᴜżʏᴡᴀꜱᴢ ᴍɪᴇᴄᴢᴀ ɪ ᴅʀᴜɢᴀ ʀęᴋᴀ ᴊᴇꜱᴛ ᴡᴏʟɴᴀ"));
                lore.add(ColorFixer.addColors("&7ᴢᴀᴅᴀᴊᴇꜱᴢ ᴅᴏᴅᴀᴛᴋᴏᴡᴇ 10 ᴏʙʀᴀżᴇń"));

                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));

                lore.add(ColorFixer.addColors("&7ɢᴅʏ ᴜżʏᴡᴀꜱᴢ ᴍɪᴇᴄᴢᴀ ɪ ᴅʀᴜɢᴀ ʀęᴋᴀ ᴊᴇꜱᴛ ᴡᴏʟɴᴀ"));
                lore.add(ColorFixer.addColors("&7ᴢᴀᴅᴀᴊᴇꜱᴢ ᴅᴏᴅᴀᴛᴋᴏᴡᴇ &a15 ᴏʙʀᴀżᴇń"));
            }
            case 3 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));

                lore.add(ColorFixer.addColors("&7ɢᴅʏ ᴜżʏᴡᴀꜱᴢ ᴍɪᴇᴄᴢᴀ ɪ ᴅʀᴜɢᴀ ʀęᴋᴀ ᴊᴇꜱᴛ ᴡᴏʟɴᴀ"));
                lore.add(ColorFixer.addColors("&7ᴢᴀᴅᴀᴊᴇꜱᴢ ᴅᴏᴅᴀᴛᴋᴏᴡᴇ &a15 ᴏʙʀᴀżᴇń"));
            }

        }
        return lore;
    }
}
