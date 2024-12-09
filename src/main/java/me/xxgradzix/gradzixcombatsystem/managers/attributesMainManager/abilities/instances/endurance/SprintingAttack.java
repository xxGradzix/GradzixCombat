package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.endurance;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.EnduranceOrigin;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.CombatAbility;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.EventableAbility;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public enum SprintingAttack implements CombatAbility, EnduranceOrigin, EventableAbility {

    INSTANCE;

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_endurance_sprinting_attack");
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
        return 6;
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
        return 2;
    }

    @Override
    public int getColumn() {
        return 7;
    }

    @Override
    public String getAbilityName() {
        return "&#007E96&lᴀ&#0F859B&lᴛ&#1E8BA0&lᴀ&#2D92A5&lᴋ &#4B9FAF&lᴡ &#69ADB9&lᴘ&#79B3BF&lʀ&#88BAC4&lᴢ&#97C1C9&lᴇ&#A6C7CE&lʟ&#B5CED3&lᴏ&#C4D5D8&lᴄ&#D3DBDD&lɪ&#E2E2E2&lᴇ";
    }

    @Override
    public List<String> getAbilityDescription(int playerLevel) {
        ArrayList<String> lore = new ArrayList<>();

        switch (playerLevel) {

            case 0 -> {
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));

                lore.add(ColorFixer.addColors("&7ᴀᴛᴀᴋ ᴘᴏᴅᴄᴢᴀꜱ ꜱᴘʀɪɴᴛᴜ ᴢᴀᴅᴀᴊᴇ"));
                lore.add(ColorFixer.addColors("&7ᴏʙʀᴀżᴇɴɪᴀ ᴡɪęᴋꜱᴢᴇ ᴏ &a10%"));
            }
            case 1 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴀᴛᴀᴋ ᴘᴏᴅᴄᴢᴀꜱ ꜱᴘʀɪɴᴛᴜ ᴢᴀᴅᴀᴊᴇ"));
                lore.add(ColorFixer.addColors("&7ᴏʙʀᴀżᴇɴɪᴀ ᴡɪęᴋꜱᴢᴇ ᴏ &a10%"));

            }

        }
        return lore;
    }


    @Override
    public Listener getListener() {
        return new Listener() {

            @EventHandler
            public void onSprintAttack(EntityDamageByEntityEvent event) {

                if(!(event.getDamager() instanceof Player damager)) return;

                if(!hasAbility(damager)) return;

                if(!damager.isSprinting()) return;

                event.setDamage(event.getDamage() * (1 + getAbilityLevel(damager) * 0.1));
                Bukkit.broadcastMessage("Sprinting attack triggered");
            }

        };
    }
}
