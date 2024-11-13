package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.strength;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.StrengthOrigin;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.CombatAbility;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.dexterity.OneHandAbility;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public enum WarMachineAbility implements CombatAbility, StrengthOrigin {

    INSTANCE;

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_strength_war_machine");
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
        return Set.of(AmmoSavingAbility.INSTANCE);
    }

    @Override
    public int getRow() {
        return 3;
    }

    @Override
    public int getColumn() {
        return 7;
    }

    @Override
    public String getAbilityName() {
        return "ᴍᴀᴄʜɪɴᴀ ᴡᴏᴊᴇɴɴᴀ";
    }

    @Override
    public List<String> getAbilityDescription(int playerLevel) {

        ArrayList<String> lore = new ArrayList<>();
        switch (playerLevel) {

            case 0 -> {
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ɢᴅʏ ᴜżʏᴡᴀꜱᴢ ᴛᴏᴘᴏʀᴜ ᴡ ᴘᴏłąᴄᴢᴇɴɪᴜ ᴢ ᴋᴜꜱᴢą, ᴢʏꜱᴋᴜᴊᴇꜱᴢ &a10% ᴘʀᴇᴍɪɪ ᴅᴏ ᴏʙʀᴀżᴇń"));

            }
            case 1 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ɢᴅʏ ᴜżʏᴡᴀꜱᴢ ᴛᴏᴘᴏʀᴜ ᴡ ᴘᴏłąᴄᴢᴇɴɪᴜ ᴢ ᴋᴜꜱᴢą, ᴢʏꜱᴋᴜᴊᴇꜱᴢ 10% ᴘʀᴇᴍɪɪ ᴅᴏ ᴏʙʀᴀżᴇń"));
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ɢᴅʏ ᴜżʏᴡᴀꜱᴢ ᴛᴏᴘᴏʀᴜ ᴡ ᴘᴏłąᴄᴢᴇɴɪᴜ ᴢ ᴋᴜꜱᴢą, ᴢʏꜱᴋᴜᴊᴇꜱᴢ &a15% ᴘʀᴇᴍɪɪ ᴅᴏ ᴏʙʀᴀżᴇń"));
            }
            case 2 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ɢᴅʏ ᴜżʏᴡᴀꜱᴢ ᴛᴏᴘᴏʀᴜ ᴡ ᴘᴏłąᴄᴢᴇɴɪᴜ ᴢ ᴋᴜꜱᴢą, ᴢʏꜱᴋᴜᴊᴇꜱᴢ &a15% ᴘʀᴇᴍɪɪ ᴅᴏ ᴏʙʀᴀżᴇń"));

            }

        }
        return lore;
    }

}
