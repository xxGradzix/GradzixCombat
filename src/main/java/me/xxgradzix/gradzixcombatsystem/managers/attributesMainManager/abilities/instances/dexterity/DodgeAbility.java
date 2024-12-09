package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.dexterity;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.CombatAbility;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.DexterityOrigin;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.EventableAbility;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public enum DodgeAbility implements CombatAbility, DexterityOrigin, EventableAbility {

    INSTANCE;

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_dexterity_dodge");
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
        return 2;
    }

    @Override
    public Set<CombatAbility> getRequiredAbilities() {
        return Set.of(AssassinAbility.INSTANCE);
    }

    @Override
    public int getRow() {
        return 2;
    }

    @Override
    public int getColumn() {
        return 1;
    }

    @Override
    public String getAbilityName() {
        return "&#4A5762&lᴜ&#3E4E5D&lɴ&#334557&lɪ&#273B52&lᴋ&#1B324C&lɪ";
    }


    @Override
    public List<String> getAbilityDescription(int playerLevel) {
        ArrayList<String> lore = new ArrayList<>();

        switch (playerLevel) {

            case 0 -> {
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));

                lore.add(ColorFixer.addColors("&7ᴅᴀᴊᴇ ꜱᴢᴀɴꜱę ɴᴀ ᴜɴɪᴋɴɪęᴄɪᴇ ᴀᴛᴀᴋᴜ"));
                lore.add(ColorFixer.addColors("&7ꜱᴢᴀɴꜱᴇ ɴᴀ ᴜɴɪᴋɴɪęᴄɪᴇ ᴡʏɴᴏꜱɪ &a4%"));
            }
            case 1 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴅᴀᴊᴇ ꜱᴢᴀɴꜱę ɴᴀ ᴜɴɪᴋɴɪęᴄɪᴇ ᴀᴛᴀᴋᴜ"));
                lore.add(ColorFixer.addColors("&7ꜱᴢᴀɴꜱᴇ ɴᴀ ᴜɴɪᴋɴɪęᴄɪᴇ ᴡʏɴᴏꜱɪ 4%"));
                lore.add(" ");

                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));

                lore.add(ColorFixer.addColors("&7ᴅᴀᴊᴇ ꜱᴢᴀɴꜱę ɴᴀ ᴜɴɪᴋɴɪęᴄɪᴇ ᴀᴛᴀᴋᴜ ᴘᴏᴅᴄᴢᴀꜱ ꜱᴘʀɪɴᴛᴜ"));
                lore.add(ColorFixer.addColors("&7ꜱᴢᴀɴꜱᴇ ɴᴀ ᴜɴɪᴋɴɪęᴄɪᴇ ᴡʏɴᴏꜱɪ &a8%"));
            }
            case 2 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));

                lore.add(ColorFixer.addColors("&7ᴅᴀᴊᴇ ꜱᴢᴀɴꜱę ɴᴀ ᴜɴɪᴋɴɪęᴄɪᴇ ᴀᴛᴀᴋᴜ ᴘᴏᴅᴄᴢᴀꜱ ꜱᴘʀɪɴᴛᴜ"));
                lore.add(ColorFixer.addColors("&7ꜱᴢᴀɴꜱᴇ ɴᴀ ᴜɴɪᴋɴɪęᴄɪᴇ ᴡʏɴᴏꜱɪ &a8%"));
            }

        }
        return lore;
    }

    @Override
    public Listener getListener() {
        return new Listener() {

            @EventHandler
            public void onEntityDamage(EntityDamageByEntityEvent event) {
                if(!(event.getEntity() instanceof Player player)) return;

                if(event.getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK) return;

                if(DodgeAbility.INSTANCE.getAbilityLevel(player) * 0.04 > Math.random()) {
                    Bukkit.broadcastMessage("test Dodge ability triggered");
                    event.setCancelled(true);
                }

            }

        };
    }
}
