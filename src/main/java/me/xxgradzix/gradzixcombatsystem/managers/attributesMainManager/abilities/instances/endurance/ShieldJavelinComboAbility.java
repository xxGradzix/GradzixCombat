package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.endurance;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.EnduranceOrigin;
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
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public enum ShieldJavelinComboAbility implements CombatAbility, EnduranceOrigin, EventableAbility {

    INSTANCE;

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_endurance_combo_shield_javelin");
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
        return Set.of(ParryAbility.INSTANCE, EnduranceCritChanceAbility.INSTANCE);
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
        return "&#0006BC&lɢ&#001AB9&lᴡ&#002DB5&lᴀ&#0041B2&lʀ&#0055AE&lᴅ&#0069AB&lᴢ&#007CA7&lɪ&#0090A4&lꜱ&#0090A4&lᴛ&#0090A4&lᴀ";
    }

    @Override
    public List<String> getAbilityDescription(int playerLevel) {

        ArrayList<String> lore = new ArrayList<>();

        switch (playerLevel) {

            case 0 -> {
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));

                lore.add(ColorFixer.addColors("&7ɢᴅʏ ᴘʀᴢᴇᴄɪᴡɴɪᴋ ᴘʀᴢᴇʙɪᴊᴇ ᴛᴀʀᴄᴢę, ᴢᴏꜱᴛᴀɴɪᴇ ᴏɴ"));
                lore.add(ColorFixer.addColors("&7ᴏᴅᴇᴘᴄʜɴɪᴜęᴛʏ ᴏᴅ ᴄɪᴇʙɪᴇ ᴀ ᴛʏ ᴏᴛʀᴢʏᴍᴀꜱᴢ "));
                lore.add(ColorFixer.addColors("&7ᴅᴏᴅᴀᴛᴋᴏᴡᴇ &a1 ꜱᴇʀᴄᴇ&7 ɴᴀ 5 ꜱᴇᴋᴜɴᴅ"));
            }
            case 1 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ɢᴅʏ ᴘʀᴢᴇᴄɪᴡɴɪᴋ ᴘʀᴢᴇʙɪᴊᴇ ᴛᴀʀᴄᴢę, ᴢᴏꜱᴛᴀɴɪᴇ ᴏɴ"));
                lore.add(ColorFixer.addColors("&7ᴏᴅᴇᴘᴄʜɴɪᴜęᴛʏ ᴏᴅ ᴄɪᴇʙɪᴇ ᴀ ᴛʏ ᴏᴛʀᴢʏᴍᴀꜱᴢ"));
                lore.add(ColorFixer.addColors("&7ᴅᴏᴅᴀᴛᴋᴏᴡᴇ 1 ꜱᴇʀᴄᴇ ɴᴀ 5 ꜱᴇᴋᴜɴᴅ"));

                lore.add(" ");

                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ɢᴅʏ ᴘʀᴢᴇᴄɪᴡɴɪᴋ ᴘʀᴢᴇʙɪᴊᴇ ᴛᴀʀᴄᴢę, ᴢᴏꜱᴛᴀɴɪᴇ ᴏɴ"));
                lore.add(ColorFixer.addColors("&7ᴏᴅᴇᴘᴄʜɴɪᴜęᴛʏ ᴏᴅ ᴄɪᴇʙɪᴇ  ᴀ ᴛʏ ᴏᴛʀᴢʏᴍᴀꜱᴢ"));
                lore.add(ColorFixer.addColors("&7ᴅᴏᴅᴀᴛᴋᴏᴡᴇ &a2 ꜱᴇʀᴄᴀ&7 ɴᴀ 5 ꜱᴇᴋᴜɴᴅ"));
            }

            case 2 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ɢᴅʏ ᴘʀᴢᴇᴄɪᴡɴɪᴋ ᴘʀᴢᴇʙɪᴊᴇ ᴛᴀʀᴄᴢę, ᴢᴏꜱᴛᴀɴɪᴇ ᴏɴ"));
                lore.add(ColorFixer.addColors("&7ᴏᴅᴇᴘᴄʜɴɪᴜęᴛʏ ᴏᴅ ᴄɪᴇʙɪᴇ  ᴀ ᴛʏ ᴏᴛʀᴢʏᴍᴀꜱᴢ"));
                lore.add(ColorFixer.addColors("&7ᴅᴏᴅᴀᴛᴋᴏᴡᴇ &a2 ꜱᴇʀᴄᴀ&7 ɴᴀ 5 ꜱᴇᴋᴜɴᴅ"));
            }

        }

        lore.add(" ");
        lore.add(ColorFixer.addColors("&7uᴍɪᴇᴊęᴛɴᴏść ᴍᴏżᴇ ʙʏć ᴀᴋᴛʏᴡᴏᴡᴀɴᴀ ᴛʏʟᴋᴏ ᴡᴛᴇᴅʏ,"));
        lore.add(ColorFixer.addColors("&7ɢᴅʏ ᴡ ʀęᴋᴜ ᴛʀᴢʏᴍᴀꜱᴢ ᴛᴀʀᴄᴢę ɪ ᴡłóᴄᴢɴɪę"));
        return lore;
    }


    @Override
    public Listener getListener() {

        return new Listener() {

            @EventHandler
            public void shieldBreak(EntityDamageByEntityEvent event) {

                if(!(event.getEntity() instanceof Player player)) return;
                if(!(event.getDamager() instanceof Player damager)) return;

                if (!hasAbility(player)) return;

                Bukkit.getScheduler().runTaskLater(GradzixCombatSystem.plugin, () -> {

                    int cooldown = player.getCooldown(Material.SHIELD);

                    if(cooldown > 0) {
                        damager.setVelocity(damager.getLocation().getDirection().multiply(-1).setY(0.5));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 100, getAbilityLevel(player), true, false, false));
                    }

                }, 1);


            }

        };

    }
}
