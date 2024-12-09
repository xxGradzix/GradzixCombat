package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.strength;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.StrengthOrigin;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.CombatAbility;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.EventableAbility;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public enum StaggerAbility implements CombatAbility, StrengthOrigin, EventableAbility {

    INSTANCE;

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_strength_stagger");
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
        return Set.of(OneManArmyAbility.INSTANCE);
    }

    @Override
    public int getRow() {
        return 3;
    }

    @Override
    public int getColumn() {
        return 5;
    }

    @Override
    public String getAbilityName() {
        return "&#565656&lᴘ&#606060&lᴏ&#696969&lᴡ&#737373&lᴀ&#7C7C7C&lʟ&#878172&lᴀ&#928667&lᴊ&#9D8B5D&lą&#A89053&lᴄ&#B39548&lᴇ &#C8A034&lᴜ&#D3A529&lᴅ&#DEAA1F&lᴇ&#E9AF15&lʀ&#F4B40A&lᴢ&#FFB900&lᴇ&#FFB900&lɴ&#FFB900&lɪ&#FFB900&lᴇ";
    }

    @Override
    public List<String> getAbilityDescription(int playerLevel) {

        ArrayList<String> lore = new ArrayList<>();
        switch (playerLevel) {

            case 0 -> {
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴅᴀᴊᴇ ꜱᴢᴀɴꜱᴇ ɴᴀ ᴏꜱᴢᴏłᴏᴍɪᴇɴɪᴇ ᴘʀᴢᴇᴄɪᴡɴɪᴋᴀ,"));
                lore.add(ColorFixer.addColors("&7ꜱᴘᴏᴡᴀʟɴɪąᴄ ɢᴏ ɴᴀ ᴋʀóᴛᴋą ᴄʜᴡɪʟę"));

            }
            case 1 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴅᴀᴊᴇ ꜱᴢᴀɴꜱᴇ ɴᴀ ᴏꜱᴢᴏłᴏᴍɪᴇɴɪᴇ ᴘʀᴢᴇᴄɪᴡɴɪᴋᴀ,"));
                lore.add(ColorFixer.addColors("&7ꜱᴘᴏᴡᴀʟɴɪąᴄ ɢᴏ ɴᴀ ᴋʀóᴛᴋą ᴄʜᴡɪʟę"));

            }

        }
        return lore;
    }

    @Override
    public Listener getListener() {
        return new Listener() {


            @EventHandler
            public void applyBonus(EntityDamageByEntityEvent event) {

                if(!(event.getDamager() instanceof Player damager)) return;
                if(!(event.getEntity() instanceof Player damaged)) return;

                int abilityLevel = getAbilityLevel(damager);

                if(abilityLevel < 1) return;

                damaged.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 15, 50, true, false, false));

                // todo ciekawe - do sprawdzenia
//                damaged.startRiptideAttack(3, 10, );


            }


        };
    }
}
