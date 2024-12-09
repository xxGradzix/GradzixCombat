package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.strength;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.StrengthOrigin;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.CombatAbility;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.EventableAbility;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public enum CombatHealingAbility implements CombatAbility, StrengthOrigin, EventableAbility {

    INSTANCE;

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_strength_combat_healing");
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
        return Set.of(FattyAbility.INSTANCE);
    }

    @Override
    public int getRow() {
        return 2;
    }

    @Override
    public int getColumn() {
        return 3;
    }

    @Override
    public String getAbilityName() {
        return "&#FF0000&lʟ&#F50000&lᴇ&#EC0000&lᴄ&#E20000&lᴢ&#D80000&lᴇ&#CF0000&lɴ&#C50000&lɪ&#BB0000&lᴇ &#A80000&lᴅ&#9E0000&lᴏ&#940000&lʀ&#8B0000&lᴀ&#810000&lź&#810000&lɴ&#810000&lᴇ";
    }




    @Override
    public List<String> getAbilityDescription(int playerLevel) {

        ArrayList<String> lore = new ArrayList<>();

        switch (playerLevel) {

            case 0 -> {
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴘᴏ ᴏᴛʀᴢʏᴍᴀɴɪᴜ ᴏʙʀᴀżᴇń ʀᴏᴢᴘᴏᴄᴢɴɪᴇ ᴘᴏᴡᴏʟɴą"));
                lore.add(ColorFixer.addColors("&7ʀᴇɢᴇɴᴇʀᴀᴄᴊᴇ ᴢᴅʀᴏᴡɪᴀ: &a0.5&7 ꜱᴇʀᴄᴀ ᴄᴏ &a15 ꜱᴇᴋᴜɴᴅ"));

            }
            case 1 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴘᴏ ᴏᴛʀᴢʏᴍᴀɴɪᴜ ᴏʙʀᴀżᴇń ʀᴏᴢᴘᴏᴄᴢɴɪᴇ ᴘᴏᴡᴏʟɴą"));
                lore.add(ColorFixer.addColors("&7ʀᴇɢᴇɴᴇʀᴀᴄᴊᴇ ᴢᴅʀᴏᴡɪᴀ: 0.5 ꜱᴇʀᴄᴀ ᴄᴏ 15 ꜱᴇᴋᴜɴᴅ"));

                lore.add(" ");

                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴘᴏ ᴏᴛʀᴢʏᴍᴀɴɪᴜ ᴏʙʀᴀżᴇń ʀᴏᴢᴘᴏᴄᴢɴɪᴇ ᴘᴏᴡᴏʟɴą"));
                lore.add(ColorFixer.addColors("&7ʀᴇɢᴇɴᴇʀᴀᴄᴊᴇ ᴢᴅʀᴏᴡɪᴀ: &a1&7 ꜱᴇʀᴄᴇ ᴄᴏ &a15 ꜱᴇᴋᴜɴᴅ"));
            }

            case 2 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴘᴏ ᴏᴛʀᴢʏᴍᴀɴɪᴜ ᴏʙʀᴀżᴇń ʀᴏᴢᴘᴏᴄᴢɴɪᴇ ᴘᴏᴡᴏʟɴą"));
                lore.add(ColorFixer.addColors("&7ʀᴇɢᴇɴᴇʀᴀᴄᴊᴇ ᴢᴅʀᴏᴡɪᴀ: 1 ꜱᴇʀᴄᴇ ᴄᴏ 15 ꜱᴇᴋᴜɴᴅ"));

                lore.add(" ");

                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴘᴏ ᴏᴛʀᴢʏᴍᴀɴɪᴜ ᴏʙʀᴀżᴇń ʀᴏᴢᴘᴏᴄᴢɴɪᴇ ᴘᴏᴡᴏʟɴą"));
                lore.add(ColorFixer.addColors("&7ʀᴇɢᴇɴᴇʀᴀᴄᴊᴇ ᴢᴅʀᴏᴡɪᴀ: &a1.5&7 ꜱᴇʀᴄᴀ ᴄᴏ &a15 ꜱᴇᴋᴜɴᴅ"));

            }


            case 3 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴘᴏ ᴏᴛʀᴢʏᴍᴀɴɪᴜ ᴏʙʀᴀżᴇń ʀᴏᴢᴘᴏᴄᴢɴɪᴇ ᴘᴏᴡᴏʟɴą"));
                lore.add(ColorFixer.addColors("&7ʀᴇɢᴇɴᴇʀᴀᴄᴊᴇ ᴢᴅʀᴏᴡɪᴀ: 1.5 ꜱᴇʀᴄᴀ ᴄᴏ 15 ꜱᴇᴋᴜɴᴅ"));
            }
        }
        return lore;
    }

    @Override
    public Listener getListener() {
        return new Listener() {

            final private HashMap<Player, BukkitTask> runningTasks = new HashMap<>();

            @EventHandler
            public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

                if(!(event.getDamager() instanceof Player damager)) return;
                if(!(event.getEntity() instanceof Player damaged)) return;

                int abilityLevel = getAbilityLevel(damager);

                if(abilityLevel <= 0) return;

                BukkitTask bukkitTask = runningTasks.get(damager);

                if(!bukkitTask.isCancelled()) return;

                Bukkit.getScheduler().runTaskLaterAsynchronously(GradzixCombatSystem.plugin, () -> {

                        damaged.heal(abilityLevel * 0.5, EntityRegainHealthEvent.RegainReason.CUSTOM);

                        }, 15 * 20);



            }

        };
    }
}
