package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.strength;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.DexterityOrigin;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.StrengthOrigin;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.CombatAbility;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.dexterity.OneHandAbility;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public enum OneManArmyAbility implements CombatAbility, StrengthOrigin {

    INSTANCE;

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_strength_one_man_army");
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
        return 3;
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
        return 5;
    }

    @Override
    public String getAbilityName() {
        return "ᴏɴᴇ ᴍᴀɴ ᴀʀᴍʏ";
    }

    @Override
    public List<String> getAbilityDescription(int playerLevel) {

        int currentLevel = playerLevel;

        ArrayList<String> lore = new ArrayList<>();

        switch (currentLevel) {

            case 0 -> {
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴢᴀ ᴋᴀżᴅᴇɢᴏ ᴘʀᴢᴇᴄɪᴡɴɪᴋᴀ ᴋᴛóʀʏ ᴢᴀᴀᴛᴀᴋᴏᴡᴀł ᴄɪę ᴡ ᴄɪąɢᴜ ᴏꜱᴛᴀᴛɴɪᴄʜ &a5 ꜱᴇᴋᴜɴᴅ"));
                lore.add(ColorFixer.addColors("&7ᴢᴀᴅᴀᴊᴇꜱᴢ &a3% &7ᴡɪęᴄᴇᴊ ᴏʙʀᴀżᴇń ᴅᴏ &aᴍᴀx 75%"));
            }
            case 1 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴢᴀ ᴋᴀżᴅᴇɢᴏ ᴘʀᴢᴇᴄɪᴡɴɪᴋᴀ ᴋᴛóʀʏ ᴢᴀᴀᴛᴀᴋᴏᴡᴀł ᴄɪę ᴡ ᴄɪąɢᴜ ᴏꜱᴛᴀᴛɴɪᴄʜ 5 ꜱᴇᴋᴜɴᴅ"));
                lore.add(ColorFixer.addColors("&7ᴢᴀᴅᴀᴊᴇꜱᴢ 3% &7ᴡɪęᴄᴇᴊ ᴏʙʀᴀżᴇń ᴅᴏ ᴍᴀx 75%"));

                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴢᴀ ᴋᴀżᴅᴇɢᴏ ᴘʀᴢᴇᴄɪᴡɴɪᴋᴀ ᴋᴛóʀʏ ᴢᴀᴀᴛᴀᴋᴏᴡᴀł ᴄɪę ᴡ ᴄɪąɢᴜ ᴏꜱᴛᴀᴛɴɪᴄʜ &a7 ꜱᴇᴋᴜɴᴅ"));
                lore.add(ColorFixer.addColors("&7ᴢᴀᴅᴀᴊᴇꜱᴢ 3% &7ᴡɪęᴄᴇᴊ ᴏʙʀᴀżᴇń ᴅᴏ &aᴍᴀx 100%"));
            }
            case 2 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴢᴀ ᴋᴀżᴅᴇɢᴏ ᴘʀᴢᴇᴄɪᴡɴɪᴋᴀ ᴋᴛóʀʏ ᴢᴀᴀᴛᴀᴋᴏᴡᴀł ᴄɪę ᴡ ᴄɪąɢᴜ ᴏꜱᴛᴀᴛɴɪᴄʜ 7 ꜱᴇᴋᴜɴᴅ"));
                lore.add(ColorFixer.addColors("&7ᴢᴀᴅᴀᴊᴇꜱᴢ 3% ᴡɪęᴄᴇᴊ ᴏʙʀᴀżᴇń ᴅᴏ ᴍᴀx 100%"));

                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴢᴀ ᴋᴀżᴅᴇɢᴏ ᴘʀᴢᴇᴄɪᴡɴɪᴋᴀ ᴋᴛóʀʏ ᴢᴀᴀᴛᴀᴋᴏᴡᴀł ᴄɪę ᴡ ᴄɪąɢᴜ ᴏꜱᴛᴀᴛɴɪᴄʜ 7 ꜱᴇᴋᴜɴᴅ"));
                lore.add(ColorFixer.addColors("&7ᴢᴀᴅᴀᴊᴇꜱᴢ &a6% &7ᴡɪęᴄᴇᴊ ᴏʙʀᴀżᴇń ᴅᴏ ᴍᴀx 100%"));
            }
            case 3 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴢᴀ ᴋᴀżᴅᴇɢᴏ ᴘʀᴢᴇᴄɪᴡɴɪᴋᴀ ᴋᴛóʀʏ ᴢᴀᴀᴛᴀᴋᴏᴡᴀł ᴄɪę ᴡ ᴄɪąɢᴜ ᴏꜱᴛᴀᴛɴɪᴄʜ 7 ꜱᴇᴋᴜɴᴅ"));
                lore.add(ColorFixer.addColors("&7ᴢᴀᴅᴀᴊᴇꜱᴢ 6% &7ᴡɪęᴄᴇᴊ ᴏʙʀᴀżᴇń ᴅᴏ ᴍᴀx 100%"));
            }

        }
        return lore;

    }

}
