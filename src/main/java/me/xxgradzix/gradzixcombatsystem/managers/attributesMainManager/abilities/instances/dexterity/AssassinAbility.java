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

public enum AssassinAbility implements CombatAbility, DexterityOrigin, EventableAbility {

    INSTANCE;

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_dexterity_assassin");
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
        return Set.of(OneHandAbility.INSTANCE);
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
        return "&#B1B1B1&lꜱ&#BCBCBC&lᴋ&#C7C7C7&lʀ&#D2D2D2&lʏ&#DEDEDE&lᴛ&#E9E9E9&lᴏ&#F4F4F4&lʙ&#FFFFFF&ló&#E2E2E2&lᴊ&#C4C4C4&lᴄ&#A7A7A7&lᴀ";
    }


    @Override
    public List<String> getAbilityDescription(int playerLevel) {

        int currentLevel = playerLevel;

        ArrayList<String> lore = new ArrayList<>();

        switch (currentLevel) {

            case 0 -> {
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴢᴡɪęᴋꜱᴢᴀ ᴏʙʀᴀżᴇɴɪᴀ ᴢᴀᴅᴀɴᴇ ᴏᴅ ᴛʏłᴜ ᴏ &a50%"));

            }
            case 1 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴢᴡɪęᴋꜱᴢᴀ ᴏʙʀᴀżᴇɴɪᴀ ᴢᴀᴅᴀɴᴇ ᴏᴅ ᴛʏłᴜ ᴏ&a 50%"));

            }

        }
        return lore;

    }


    @Override
    public Listener getListener() {
        return new Listener() {


            @EventHandler
            public void onEntityDamageByEntityTeleport(EntityDamageByEntityEvent event) {

                if(!(event.getDamager() instanceof Player damager)) return;
                if(!(event.getEntity() instanceof Player damaged)) return;

                boolean canSee = damaged.canSee(damager);
                if(canSee) return;

                // TODO check if have ability
//                CombatAbility

                // TODO do usuniecia
                Bukkit.broadcastMessage("Assassin ability triggered");
                event.setDamage(event.getDamage() * 1.5);
            }
        };
    }
}
