package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.endurance;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.EnduranceOrigin;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.CombatAbility;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.EventableAbility;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
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

public enum ParryAbility implements CombatAbility, EnduranceOrigin, EventableAbility {

    INSTANCE;

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_endurance_parry");
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
        return 1;
    }

    @Override
    public Set<CombatAbility> getRequiredAbilities() {
        return Set.of(MediumBonusSet.INSTANCE);
    }

    @Override
    public int getRow() {
        return 4;
    }

    @Override
    public int getColumn() {
        return 1;
    }

    @Override
    public String getAbilityName() {
        return "&#A5E763&lᴘ&#9CE068&lᴀ&#93D96D&lʀ&#8AD272&lᴏ&#82CC77&lᴡ&#79C57C&lᴀ&#70BE81&lɴ&#67B786&lɪ&#5EB08B&lᴇ";
    }

    @Override
    public List<String> getAbilityDescription(int playerLevel) {
        ArrayList<String> lore = new ArrayList<>();

        switch (playerLevel) {

            case 0 -> {
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));

                lore.add(ColorFixer.addColors("&7ᴜżʏᴄɪᴇ ᴛᴀʀᴄᴢʏ ɴᴀ ᴄʜᴡɪʟę ᴘʀᴢᴇᴅ ᴏᴛʀᴢʏᴍᴀɴɪᴇᴍ"));
                lore.add(ColorFixer.addColors("&7ᴏʙʀᴀżᴇń, ᴘᴏᴢᴡᴀʟᴀ ɴᴀ ꜱᴘᴀʀᴏᴡᴀɴɪᴇ ᴄɪᴏꜱᴜ"));
                lore.add(" ");
                lore.add(ColorFixer.addColors("&7ꜱᴘᴀʀᴏᴡᴀɴɪᴇ ᴀᴛᴀᴋᴜ ᴏᴅꜱłᴀɴɪᴀ ᴘʀᴢᴇᴄɪᴡɴɪᴋᴀ"));
                lore.add(ColorFixer.addColors("&7ɴᴀ ᴀᴛᴀᴋɪ ɪ ꜱᴘᴏᴡᴀʟɴɪᴀ ɢᴏ"));
            }
            case 1 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));

                lore.add(ColorFixer.addColors("&7ᴜżʏᴄɪᴇ ᴛᴀʀᴄᴢʏ ɴᴀ ᴄʜᴡɪʟę ᴘʀᴢᴇᴅ ᴏᴛʀᴢʏᴍᴀɴɪᴇᴍ"));
                lore.add(ColorFixer.addColors("&7ᴏʙʀᴀżᴇń, ᴘᴏᴢᴡᴀʟᴀ ɴᴀ ꜱᴘᴀʀᴏᴡᴀɴɪᴇ ᴄɪᴏꜱᴜ"));
                lore.add(" ");
                lore.add(ColorFixer.addColors("&7ꜱᴘᴀʀᴏᴡᴀɴɪᴇ ᴀᴛᴀᴋᴜ ᴏᴅꜱłᴀɴɪᴀ ᴘʀᴢᴇᴄɪᴡɴɪᴋᴀ"));
                lore.add(ColorFixer.addColors("&7ɴᴀ ᴀᴛᴀᴋɪ ɪ ꜱᴘᴏᴡᴀʟɴɪᴀ ɢᴏ"));
            }

        }
        return lore;
    }


    @Override
    public Listener getListener() {
        return new Listener() {



            @EventHandler
            public void onPlayerAttack(EntityDamageByEntityEvent event) {

                if(!(event.getEntity() instanceof Player player)) return;
                if(!(event.getDamager() instanceof LivingEntity damager)) return;

                if(ParryAbility.INSTANCE.getAbilityLevel(player) < 1) return;

                boolean cancelled = event.isCancelled();

                int handRaisedTime = player.getHandRaisedTime();

                if(handRaisedTime < 10 && handRaisedTime > 0) {
                    event.setCancelled(true);
                    Bukkit.broadcastMessage("Parry ability triggered");
                    player.playSound(player.getLocation(), Sound.ENTITY_WIND_CHARGE_WIND_BURST, 1, 1.5f);
                    damager.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 15, 10, true, false, false));
                }

            }

        };
    }
}
