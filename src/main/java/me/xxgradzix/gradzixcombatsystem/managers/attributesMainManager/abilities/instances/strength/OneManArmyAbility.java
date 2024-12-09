package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.strength;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.StrengthOrigin;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.CombatAbility;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.EventableAbility;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public enum OneManArmyAbility implements CombatAbility, StrengthOrigin, EventableAbility {

    INSTANCE;

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_strength_one_man_army");
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
        return 3;
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
        return 5;
    }

    @Override
    public String getAbilityName() {
        return "&#8991C1&lᴏ&#8289B9&lɴ&#7A81B0&lᴇ &#6C70A0&lᴍ&#646898&lᴀ&#5D608F&lɴ &#4E507F&lᴀ&#474777&lʀ&#3F3F6E&lᴍ&#383766&lʏ";
    }

    @Override
    public List<String> getAbilityDescription(int playerLevel) {

        ArrayList<String> lore = new ArrayList<>();

        switch (playerLevel) {

            case 0 -> {
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ɢᴅʏ ᴡᴀʟᴄᴢʏꜱᴢ ᴢ ᴍɪɴ. 3 ɢʀᴀᴄᴢᴀᴍɪ ɴᴀ ʀᴀᴢ"));
                lore.add(ColorFixer.addColors("&7ᴢᴀ ᴋᴀżᴅᴇɢᴏ ᴘʀᴢᴇᴄɪᴡɴɪᴋᴀ ᴋᴛóʀʏ ᴢᴀᴀᴛᴀᴋᴏᴡᴀł ᴄɪę"));
                lore.add(ColorFixer.addColors("&7ᴡ ᴄɪąɢᴜ ᴏꜱᴛᴀᴛɴɪᴄʜ &a5 &7ꜱᴇᴋᴜɴᴅ"));

                lore.add(ColorFixer.addColors("&7ᴢᴀᴅᴀᴊᴇꜱᴢ &a3% &7ᴡɪęᴄᴇᴊ ᴏʙʀᴀżᴇń ᴅᴏ &aᴍᴀx 75%"));
            }
            case 1 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ɢᴅʏ ᴡᴀʟᴄᴢʏꜱᴢ ᴢ ᴍɪɴ. 3 ɢʀᴀᴄᴢᴀᴍɪ ɴᴀ ʀᴀᴢ"));

                lore.add(ColorFixer.addColors("&7ᴢᴀ ᴋᴀżᴅᴇɢᴏ ᴘʀᴢᴇᴄɪᴡɴɪᴋᴀ ᴋᴛóʀʏ ᴢᴀᴀᴛᴀᴋᴏᴡᴀł ᴄɪę"));
                lore.add(ColorFixer.addColors("&7ᴡ ᴄɪąɢᴜ ᴏꜱᴛᴀᴛɴɪᴄʜ 5&7 ꜱᴇᴋᴜɴᴅ"));

                lore.add(ColorFixer.addColors("&7ᴢᴀᴅᴀᴊᴇꜱᴢ 3% &7ᴡɪęᴄᴇᴊ ᴏʙʀᴀżᴇń ᴅᴏ ᴍᴀx 75%"));
                lore.add(" ");

                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ɢᴅʏ ᴡᴀʟᴄᴢʏꜱᴢ ᴢ ᴍɪɴ. 3 ɢʀᴀᴄᴢᴀᴍɪ ɴᴀ ʀᴀᴢ"));

                lore.add(ColorFixer.addColors("&7ᴢᴀ ᴋᴀżᴅᴇɢᴏ ᴘʀᴢᴇᴄɪᴡɴɪᴋᴀ ᴋᴛóʀʏ ᴢᴀᴀᴛᴀᴋᴏᴡᴀł ᴄɪę"));
                lore.add(ColorFixer.addColors("&7ᴡ ᴄɪąɢᴜ ᴏꜱᴛᴀᴛɴɪᴄʜ &a7&7 ꜱᴇᴋᴜɴᴅ"));
                lore.add(ColorFixer.addColors("&7ᴢᴀᴅᴀᴊᴇꜱᴢ 3% &7ᴡɪęᴄᴇᴊ ᴏʙʀᴀżᴇń ᴅᴏ &aᴍᴀx 100%"));
            }
            case 2 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ɢᴅʏ ᴡᴀʟᴄᴢʏꜱᴢ ᴢ ᴍɪɴ. 3 ɢʀᴀᴄᴢᴀᴍɪ ɴᴀ ʀᴀᴢ"));

                lore.add(ColorFixer.addColors("&7ᴢᴀ ᴋᴀżᴅᴇɢᴏ ᴘʀᴢᴇᴄɪᴡɴɪᴋᴀ ᴋᴛóʀʏ ᴢᴀᴀᴛᴀᴋᴏᴡᴀł ᴄɪę"));
                lore.add(ColorFixer.addColors("&7ᴡ ᴄɪąɢᴜ ᴏꜱᴛᴀᴛɴɪᴄʜ 7 ꜱᴇᴋᴜɴᴅ"));
                lore.add(ColorFixer.addColors("&7ᴢᴀᴅᴀᴊᴇꜱᴢ 3% ᴡɪęᴄᴇᴊ ᴏʙʀᴀżᴇń ᴅᴏ ᴍᴀx 100%"));

                lore.add(" ");
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ɢᴅʏ ᴡᴀʟᴄᴢʏꜱᴢ ᴢ ᴍɪɴ. 3 ɢʀᴀᴄᴢᴀᴍɪ ɴᴀ ʀᴀᴢ"));

                lore.add(ColorFixer.addColors("&7ᴢᴀ ᴋᴀżᴅᴇɢᴏ ᴘʀᴢᴇᴄɪᴡɴɪᴋᴀ ᴋᴛóʀʏ ᴢᴀᴀᴛᴀᴋᴏᴡᴀł ᴄɪę"));
                lore.add(ColorFixer.addColors("&7ᴡ ᴄɪąɢᴜ ᴏꜱᴛᴀᴛɴɪᴄʜ 7 ꜱᴇᴋᴜɴᴅ"));
                lore.add(ColorFixer.addColors("&7ᴢᴀᴅᴀᴊᴇꜱᴢ &a6% &7ᴡɪęᴄᴇᴊ ᴏʙʀᴀżᴇń ᴅᴏ ᴍᴀx 100%"));
            }
            case 3 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ɢᴅʏ ᴡᴀʟᴄᴢʏꜱᴢ ᴢ ᴍɪɴ. 3 ɢʀᴀᴄᴢᴀᴍɪ ɴᴀ ʀᴀᴢ"));

                lore.add(ColorFixer.addColors("&7ᴢᴀ ᴋᴀżᴅᴇɢᴏ ᴘʀᴢᴇᴄɪᴡɴɪᴋᴀ ᴋᴛóʀʏ ᴢᴀᴀᴛᴀᴋᴏᴡᴀł ᴄɪę"));
                lore.add(ColorFixer.addColors("&7ᴡ ᴄɪąɢᴜ ᴏꜱᴛᴀᴛɴɪᴄʜ 7 ꜱᴇᴋᴜɴᴅ"));
                lore.add(ColorFixer.addColors("&7ᴢᴀᴅᴀᴊᴇꜱᴢ 6% &7ᴡɪęᴄᴇᴊ ᴏʙʀᴀżᴇń ᴅᴏ ᴍᴀx 100%"));
            }

        }
        return lore;

    }

    @Override
    public Listener getListener() {
        return new Listener() {

            final private HashMap<Player, HashMap<Entity, Long>> damageSources = new HashMap<>();

            @EventHandler
            public void saveDamageSource(EntityDamageByEntityEvent event) {

                if(!(event.getEntity() instanceof Player damaged)) return;

                int abilityLevel = getAbilityLevel(damaged);

                if(abilityLevel < 1) return;

                HashMap<Entity, Long> damageSource = damageSources.getOrDefault(damaged, new HashMap<>());

                damageSource.put(event.getDamager(), System.currentTimeMillis());

                damageSources.put(damaged, damageSource);
            }

            @EventHandler
            public void applyBonus(EntityDamageByEntityEvent event) {

                if(!(event.getDamager() instanceof Player damager)) return;

                int abilityLevel = getAbilityLevel(damager);

                if(abilityLevel < 1) return;


                int totalPlayers = damageSources.getOrDefault(damager, new HashMap<>()).values().stream().filter(time -> System.currentTimeMillis() - time <= 7000).toArray().length;

                if(totalPlayers < 3) return;

                double damageBonus = (abilityLevel >= 3 ? 0.06 : 0.03) * totalPlayers;

                if(damageBonus > (abilityLevel >= 2 ? 1 : 0.75)) damageBonus = (abilityLevel >= 2 ? 1 : 0.75);

                event.setDamage(event.getDamage() + (event.getDamage() * damageBonus));

            }

        };
    }
}
