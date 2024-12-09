package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.endurance;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.guis.attributes.AbilitiesGuiManager;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.EnduranceOrigin;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.CombatAbility;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.EventableAbility;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public enum ShieldCooldown implements CombatAbility, EnduranceOrigin, EventableAbility {

    INSTANCE;

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_endurance_shield_cooldown");
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
        return 12;
    }

    @Override
    public int getMaxAbilityLevel() {
        return 1;
    }

    @Override
    public Set<CombatAbility> getRequiredAbilities() {
        return Set.of(ShieldJavelinComboAbility.INSTANCE, JumperAbility.INSTANCE);
    }

    @Override
    public int getRow() {
        return 6;
    }

    @Override
    public int getColumn() {
        return 4;
    }

    @Override
    public String getAbilityName() {
        return "&#0061BC&lɴ&#1162AE&lɪ&#22639F&lᴇ&#336491&lᴢ&#446483&lł&#556574&lᴏ&#666666&lᴍ&#666666&lɴ&#666666&lʏ";
    }

    @Override
    public List<String> getAbilityDescription(int playerLevel) {
        ArrayList<String> lore = new ArrayList<>();

        switch (playerLevel) {

            case 0 -> {
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴢᴍɴɪᴇᴊꜱᴢᴀ ᴄᴏᴏʟᴅᴏᴡɴ ᴛᴀʀᴄᴢʏ ᴏ &a20%"));
            }
            case 1 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴢᴍɴɪᴇᴊꜱᴢᴀ ᴄᴏᴏʟᴅᴏᴡɴ ᴛᴀʀᴄᴢʏ ᴏ &a20%"));

            }

        }
        return lore;
    }

    @Override
    public Set<AbilitiesGuiManager.SlotData> getCustomLines() {
        return Set.of(
                new AbilitiesGuiManager.SlotData(6, 3, AbilitiesGuiManager.CurveType.LEFT),
                new AbilitiesGuiManager.SlotData(6, 5, AbilitiesGuiManager.CurveType.RIGHT),
                new AbilitiesGuiManager.SlotData(6, 6, AbilitiesGuiManager.CurveType.RIGHT_DOWN),
                new AbilitiesGuiManager.SlotData(6, 2, AbilitiesGuiManager.CurveType.LEFT_DOWN)
        );
    }


    @Override
    public Listener getListener() {
        return new Listener() {

            @EventHandler
            public void shieldBreak(EntityDamageEvent event) {

                if(!(event.getEntity() instanceof Player player)) return;

                if (!hasAbility(player)) return;

                Bukkit.getScheduler().runTaskLater(GradzixCombatSystem.plugin, () -> {

                    int cooldown = player.getCooldown(Material.SHIELD);
                    if(cooldown > 0) {
                        player.setCooldown(Material.SHIELD, (int) (cooldown * 0.8));
                    }

                }, 1);


            }

        };
    }
}
