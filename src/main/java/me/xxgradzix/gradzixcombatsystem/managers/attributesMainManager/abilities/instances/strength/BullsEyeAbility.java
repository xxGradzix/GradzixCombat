package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.strength;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.StrengthOrigin;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.CombatAbility;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.EventableAbility;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.projectiles.ProjectileSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public enum BullsEyeAbility implements CombatAbility, StrengthOrigin, EventableAbility {

    INSTANCE;

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_strength_bullseye");
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
        return 1;
    }

    @Override
    public Set<CombatAbility> getRequiredAbilities() {
        return Set.of(StaggerAbility.INSTANCE, WarMachineAbility.INSTANCE);
    }

    @Override
    public int getRow() {
        return 4;
    }

    @Override
    public int getColumn() {
        return 6;
    }

    @Override
    public String getAbilityName() {
        return "&#006A79&lꜱ&#086570&lᴏ&#106167&lᴋ&#195C5E&lᴏ&#215855&lʟ&#29534B&lᴇ &#3A4A39&lᴏ&#424630&lᴋ&#4A4127&lᴏ";
    }

    @Override
    public List<String> getAbilityDescription(int playerLevel) {

        ArrayList<String> lore = new ArrayList<>();
        switch (playerLevel) {

            case 0 -> {
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴛʀᴀꜰɪᴇɴɪᴇ ᴘʀᴢᴇᴄɪᴡɴɪᴋᴀ ᴢ ᴋᴜꜱᴢʏ ᴘᴏᴡᴏᴅᴜᴊᴇ,"));
                lore.add(ColorFixer.addColors("&7żᴇ ɴᴀꜱᴛęᴘɴʏ ᴀᴛᴀᴋ ᴛᴏᴘᴏʀᴀ ᴢᴀᴅᴀ &a200%&7 ᴏʙʀᴀżᴇń"));

            }
            case 1 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴛʀᴀꜰɪᴇɴɪᴇ ᴘʀᴢᴇᴄɪᴡɴɪᴋᴀ ᴢ ᴋᴜꜱᴢʏ ᴘᴏᴡᴏᴅᴜᴊᴇ,"));
                lore.add(ColorFixer.addColors("&7żᴇ ɴᴀꜱᴛęᴘɴʏ ᴀᴛᴀᴋ ᴛᴏᴘᴏʀᴀ ᴢᴀᴅᴀ &a200%&7 ᴏʙʀᴀżᴇń"));

            }

        }
        return lore;
    }

    @Override
    public Listener getListener() {
        return new Listener() {

            private final HashMap<Player, HashMap<Entity, Long>> lastHit = new HashMap<>();

            @EventHandler
            public void axeHitEvent(EntityDamageByEntityEvent event) {


                if (!(event.getDamager() instanceof Player player)) return;
                int abilityLevel = getAbilityLevel(player);
                if (abilityLevel <= 0) return;

                HashMap<Entity, Long> taggedPlayers = lastHit.getOrDefault(player, new HashMap<>());

                long lastHit = taggedPlayers.getOrDefault(event.getEntity(), 0L);

                if(System.currentTimeMillis() - lastHit <= 10 * 1000) {
                    event.setDamage(event.getDamage() * 2);
                }
            }

            @EventHandler
            public void onPlayerHitArrow(ProjectileHitEvent event) {

                ProjectileSource shooter = event.getEntity().getShooter();

                if(!(shooter instanceof Player player)) return;

                int abilityLevel = getAbilityLevel(player);

                if(abilityLevel <= 0) return;

                Entity hitEntity = event.getHitEntity();

                if(hitEntity == null) return;

                Bukkit.broadcastMessage("test Bulls eye ability tagged");

                HashMap<Entity, Long> lastHitMap = lastHit.getOrDefault(player, new HashMap<>());

                lastHitMap.put(hitEntity, System.currentTimeMillis());

                lastHit.put(player, lastHitMap);

            }
        };
    }
}