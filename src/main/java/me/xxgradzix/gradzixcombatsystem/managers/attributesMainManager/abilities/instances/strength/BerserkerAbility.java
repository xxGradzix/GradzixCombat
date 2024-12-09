package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.strength;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.StrengthOrigin;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.CombatAbility;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.EventableAbility;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.dexterity.OneHandAbility;
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

public enum BerserkerAbility implements CombatAbility, StrengthOrigin, EventableAbility {

    INSTANCE;

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_strength_berserker");
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
        return 9;
    }

    @Override
    public int getMaxAbilityLevel() {
        return 2;
    }

    @Override
    public Set<CombatAbility> getRequiredAbilities() {
        return Set.of(AdrenalineRushAbility.INSTANCE);
    }

    @Override
    public int getRow() {
        return 5;
    }

    @Override
    public int getColumn() {
        return 2;
    }

    @Override
    public String getAbilityName() {
        return "&#8D7534&lʙ&#8C6F37&lᴇ&#8B693A&lʀ&#8A633D&lꜱ&#885C41&lᴇ&#875644&lᴋ&#865047&lᴇ&#854A4A&lʀ";
    }

    @Override
    public List<String> getAbilityDescription(int playerLevel) {

        int currentLevel = playerLevel;

        ArrayList<String> lore = new ArrayList<>();

        switch (currentLevel) {
            case 0 -> {
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ɢᴅʏ żʏᴄɪᴇ ꜱᴘᴀᴅɴɪᴇ ᴘᴏɴɪżᴇᴊ &a30%"));
                lore.add(ColorFixer.addColors("&7ᴢᴡɪęᴋꜱᴢᴀ ᴢᴀᴅᴀᴡᴀɴᴇ ᴏʙʀᴀżᴇɴɪᴀ ᴏ &a30%"));
            }
            case 1 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ɢᴅʏ żʏᴄɪᴇ ꜱᴘᴀᴅɴɪᴇ ᴘᴏɴɪżᴇᴊ 30%"));
                lore.add(ColorFixer.addColors("&7ᴢᴡɪęᴋꜱᴢᴀ ᴢᴀᴅᴀᴡᴀɴᴇ ᴏʙʀᴀżᴇɴɪᴀ ᴏ 30%"));
                lore.add(" ");

                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ɢᴅʏ żʏᴄɪᴇ ꜱᴘᴀᴅɴɪᴇ ᴘᴏɴɪżᴇᴊ &a30%"));
                lore.add(ColorFixer.addColors("&7ᴢᴡɪęᴋꜱᴢᴀ ᴢᴀᴅᴀᴡᴀɴᴇ ᴏʙʀᴀżᴇɴɪᴀ ᴏ &a40%"));
            }
            case 2 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ɢᴅʏ żʏᴄɪᴇ ꜱᴘᴀᴅɴɪᴇ ᴘᴏɴɪżᴇᴊ &a30%"));
                lore.add(ColorFixer.addColors("&7ᴢᴡɪęᴋꜱᴢᴀ ᴢᴀᴅᴀᴡᴀɴᴇ ᴏʙʀᴀżᴇɴɪᴀ ᴏ &a40%"));
            }

        }
        return lore;

    }

    @Override
    public Listener getListener() {
        return new Listener() {

            @EventHandler
            public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

                if (!(event.getDamager() instanceof Player damager)) return;

                if(damager.getHealth() > 0.3 * damager.getMaxHealth()) return;

                int abilityLevel = getAbilityLevel(damager);

                if (abilityLevel <= 0) return;

                Bukkit.broadcastMessage("Berserker ability triggered");
                if(abilityLevel == 1) event.setDamage(event.getDamage() * 1.3);
                if(abilityLevel == 2) event.setDamage(event.getDamage() * 1.4);

            }
        };
    }
}
