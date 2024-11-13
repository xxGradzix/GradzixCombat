package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.strength;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.StrengthOrigin;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.CombatAbility;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public enum BonusDamageAbility implements CombatAbility, StrengthOrigin {

    INSTANCE;

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_strength_bonus_damage");
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
        return Set.of(AdrenalineRushAbility.INSTANCE);
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
        return "ᴀɢʀᴇꜱᴏʀ";
    }

    @Override
    public List<String> getAbilityDescription(int playerLevel) {

        int currentLevel = playerLevel;

        ArrayList<String> lore = new ArrayList<>();

        switch (currentLevel) {
            case 0 -> {
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴢᴡɪęᴋꜱᴢᴀ ᴏʙʀᴀżᴇɴɪᴀ ᴡꜱᴢʏꜱᴛᴋɪᴄʜ ʙʀᴏɴɪ ᴏ &a1"));
            }
            case 1 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴢᴡɪęᴋꜱᴢᴀ ᴏʙʀᴀżᴇɴɪᴀ ᴡꜱᴢʏꜱᴛᴋɪᴄʜ ʙʀᴏɴɪ ᴏ &a1"));
            }

        }
        return lore;

    }

}
