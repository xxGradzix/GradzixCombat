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

public enum CombatHealingAbility implements CombatAbility, StrengthOrigin {

    INSTANCE;

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_strength_combat_healing");
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
        return Set.of(FattyAbility.INSTANCE);
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
        return "ʟᴇᴄᴢᴇɴɪᴇ ᴅᴏʀᴀźɴᴇ";
    }




    @Override
    public List<String> getAbilityDescription(int playerLevel) {

        ArrayList<String> lore = new ArrayList<>();

        switch (playerLevel) {

            case 0 -> {
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴘᴏ ᴏᴛʀᴢʏᴍᴀɴɪᴜ ᴏʙʀᴀżᴇń ʀᴏᴢᴘᴏᴄᴢɴɪᴇ ᴘᴏᴡᴏʟɴą ʀᴇɢᴇɴᴇʀᴀᴄᴊᴇ ᴢᴅʀᴏᴡɪᴀ: &a0.5 ꜱᴇʀᴄᴀ ᴄᴏ 15 ꜱᴇᴋᴜɴᴅ"));

            }
            case 1 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴘᴏ ᴏᴛʀᴢʏᴍᴀɴɪᴜ ᴏʙʀᴀżᴇń ʀᴏᴢᴘᴏᴄᴢɴɪᴇ ᴘᴏᴡᴏʟɴą ʀᴇɢᴇɴᴇʀᴀᴄᴊᴇ ᴢᴅʀᴏᴡɪᴀ: 0.5 ꜱᴇʀᴄᴀ ᴄᴏ 15 ꜱᴇᴋᴜɴᴅ"));

                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴘᴏ ᴏᴛʀᴢʏᴍᴀɴɪᴜ ᴏʙʀᴀżᴇń ʀᴏᴢᴘᴏᴄᴢɴɪᴇ ᴘᴏᴡᴏʟɴą ʀᴇɢᴇɴᴇʀᴀᴄᴊᴇ ᴢᴅʀᴏᴡɪᴀ: &a1 ꜱᴇʀᴄᴇ ᴄᴏ 15 ꜱᴇᴋᴜɴᴅ"));
            }

            case 2 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴘᴏ ᴏᴛʀᴢʏᴍᴀɴɪᴜ ᴏʙʀᴀżᴇń ʀᴏᴢᴘᴏᴄᴢɴɪᴇ ᴘᴏᴡᴏʟɴą ʀᴇɢᴇɴᴇʀᴀᴄᴊᴇ ᴢᴅʀᴏᴡɪᴀ: 1 ꜱᴇʀᴄᴇ ᴄᴏ 15 ꜱᴇᴋᴜɴᴅ"));

                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴘᴏ ᴏᴛʀᴢʏᴍᴀɴɪᴜ ᴏʙʀᴀżᴇń ʀᴏᴢᴘᴏᴄᴢɴɪᴇ ᴘᴏᴡᴏʟɴą ʀᴇɢᴇɴᴇʀᴀᴄᴊᴇ ᴢᴅʀᴏᴡɪᴀ: &a1.5 ꜱᴇʀᴄᴀ ᴄᴏ 15 ꜱᴇᴋᴜɴᴅ"));
            }


            case 3 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴘᴏ ᴏᴛʀᴢʏᴍᴀɴɪᴜ ᴏʙʀᴀżᴇń ʀᴏᴢᴘᴏᴄᴢɴɪᴇ ᴘᴏᴡᴏʟɴą ʀᴇɢᴇɴᴇʀᴀᴄᴊᴇ ᴢᴅʀᴏᴡɪᴀ:&a 1.5 ꜱᴇʀᴄᴀ ᴄᴏ 15 ꜱᴇᴋᴜɴᴅ"));
            }
        }
        return lore;
    }

}
