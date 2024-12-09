package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.endurance;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.guis.attributes.AbilitiesGuiManager;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.EnduranceOrigin;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.CombatAbility;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.EventableAbility;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public enum EffectResistance implements CombatAbility, EnduranceOrigin, EventableAbility {

    INSTANCE;

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_endurance_effect_resistance");
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
        return Set.of(QuickSpearReturn.INSTANCE);
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
        return "&#D08857&lɢ&#C48553&lʀ&#B9834F&lᴜ&#AD804C&lʙ&#A17D48&lᴏ&#967B44&lꜱ&#8A7840&lᴋ&#7E753C&ló&#727239&lʀ&#677035&lɴ&#5B6D31&lʏ";
    }

    @Override
    public List<String> getAbilityDescription(int playerLevel) {
        ArrayList<String> lore = new ArrayList<>();

        switch (playerLevel) {

            case 0 -> {
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));

                lore.add(ColorFixer.addColors("&7ᴢᴍɴɪᴇᴊꜱᴢᴀ ᴄᴢᴀꜱ ɪ ꜱɪłę ɴᴇɢᴀᴛʏᴡɴʏᴄʜ ᴇꜰᴇᴋᴛóᴡ"));
            }
            case 1 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));

                lore.add(ColorFixer.addColors("&7ᴢᴍɴɪᴇᴊꜱᴢᴀ ᴄᴢᴀꜱ ɪ ꜱɪłę ɴᴇɢᴀᴛʏᴡɴʏᴄʜ ᴇꜰᴇᴋᴛóᴡ"));

            }

        }
        return lore;
    }

    @Override
    public Set<AbilitiesGuiManager.SlotData> getCustomLines() {
        return Set.of(
                new AbilitiesGuiManager.SlotData(getRow() -1, getColumn(), AbilitiesGuiManager.CurveType.RIGHT_UP)
        );

    }


    @Override
    public Listener getListener() {
        return new Listener() {

            @EventHandler
            public void effectApplyEvent(EntityPotionEffectEvent event) {

                Bukkit.broadcastMessage("test effect resistance 1");
                if (!(event.getEntity() instanceof Player player)) return;
                Bukkit.broadcastMessage("test effect resistance 2");
                PotionEffect newEffect = event.getNewEffect();

                if(newEffect == null) return;
                Bukkit.broadcastMessage("test effect resistance 3");

                int abilityLevel = EffectResistance.INSTANCE.getAbilityLevel(player);

                if(abilityLevel == 0) return;

                Bukkit.broadcastMessage("test effect resistance 4");

                int amplifier = newEffect.getAmplifier();

                if(amplifier == 1) {
                    int duration = newEffect.getDuration();
                    newEffect.withDuration((int) (duration * 0.8));
                } else {
                    newEffect.withAmplifier(amplifier - 1);

                }
                Bukkit.broadcastMessage("test effect resistance");
                event.setOverride(true);

            }

        };
    }
}
