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

public enum AmmoSavingAbility implements CombatAbility, StrengthOrigin {

    INSTANCE;

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_strength_ammo_saving");
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
        return 1;
    }

    @Override
    public Set<CombatAbility> getRequiredAbilities() {
        return Set.of();
    }

    @Override
    public int getRow() {
        return 1;
    }

    @Override
    public int getColumn() {
        return 7;
    }

    @Override
    public String getAbilityName() {
        return "ᴏꜱᴢᴄᴢęᴅᴢᴀɴɪᴇ ᴀᴍᴜɴɪᴄᴊɪ";
    }



    @Override
    public List<String> getAbilityDescription(int playerLevel) {

        ArrayList<String> lore = new ArrayList<>();
        switch (playerLevel) {

            case 0 -> {
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴘᴏ ᴢᴀʙɪᴄɪᴜ ɢʀᴀᴄᴢᴀ ᴀᴋᴛʏᴡᴜᴊᴇ ʀᴇɢᴇɴᴇʀᴀᴄᴊᴇ ᴢᴅʀᴏᴡɪᴀ ɴᴀ &a5 ꜱᴇᴋᴜɴᴅ"));

            }
            case 1 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴘᴏ ᴢᴀʙɪᴄɪᴜ ɢʀᴀᴄᴢᴀ ᴀᴋᴛʏᴡᴜᴊᴇ ʀᴇɢᴇɴᴇʀᴀᴄᴊᴇ ᴢᴅʀᴏᴡɪᴀ ɴᴀ 5 ꜱᴇᴋᴜɴᴅ"));

            }

        }
        return lore;
    }

}
