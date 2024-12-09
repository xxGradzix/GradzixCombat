package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.endurance;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.guis.attributes.AbilitiesGuiManager;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.EnduranceOrigin;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.CombatAbility;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public enum MediumBonusSet implements CombatAbility, EnduranceOrigin {

    INSTANCE;

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_endurance_set_bonus_light_armor");
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
        return 3;
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
        return 1;
    }

    @Override
    public String getAbilityName() {
        return "&7&lᴘʀᴇᴍɪᴀ ᴢᴀ ᴢᴇꜱᴛᴀᴡ: " + "&#CC691Aś&#CD7027ʀ&#CE7734ᴇ&#D07D40ᴅ&#D1844Dɴ&#D28B5Aɪ" + " &7ᴘᴀɴᴄᴇʀᴢ ";
    }

    @Override
    public List<String> getAbilityDescription(int playerLevel) {
        ArrayList<String> lore = new ArrayList<>();

        switch (playerLevel) {

            case 0 -> {
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7XXXX"));
            }
            case 1 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7XXX"));
            }

        }
        return lore;
    }

    @Override
    public Set<AbilitiesGuiManager.SlotData> getCustomLines() {
        return Set.of(
                new AbilitiesGuiManager.SlotData(getRow() -1, getColumn(), AbilitiesGuiManager.CurveType.LEFT_UP)
        );

    }

}
