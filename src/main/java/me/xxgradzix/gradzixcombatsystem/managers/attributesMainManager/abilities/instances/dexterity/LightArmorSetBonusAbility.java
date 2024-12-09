package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.dexterity;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.CombatAbility;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.DexterityOrigin;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public enum LightArmorSetBonusAbility implements CombatAbility, DexterityOrigin {

    INSTANCE;

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_dexterity_set_bonus_light_armor");
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
        return Set.of(BowSwordComboAbility.INSTANCE);
    }

    @Override
    public int getRow() {
        return 2;
    }

    @Override
    public int getColumn() {
        return 7;
    }


    @Override
    public String getAbilityName() {
        return "&7&lᴘʀᴇᴍɪᴀ ᴢᴀ ᴢᴇꜱᴛᴀᴡ: " + "&#305F9Cʟ&#3B659Eᴇ&#466CA1ᴋ&#5072A3ᴋ&#5B78A5ɪ" + " &7ᴘᴀɴᴄᴇʀᴢ";
    }


    @Override
    public List<String> getAbilityDescription(int playerLevel) {
        ArrayList<String> lore = new ArrayList<>();

        switch (playerLevel) {

            case 0 -> {
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));

                lore.add(ColorFixer.addColors("&7ᴘᴇłɴᴀ ʟᴇᴋᴋᴀ ᴢʙʀᴏᴊᴀ ᴅᴀᴊᴇ"));
                lore.add(ColorFixer.addColors("&7XXXXX"));
            }
            case 1 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));

                lore.add(ColorFixer.addColors("&7ᴘᴇłɴᴀ ʟᴇᴋᴋᴀ ᴢʙʀᴏᴊᴀ ᴅᴀᴊᴇ"));
                lore.add(ColorFixer.addColors("&7XXXXX"));
            }

        }
        return lore;
    }

}