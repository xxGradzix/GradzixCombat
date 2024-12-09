package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.endurance;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.CombatAbility;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.EnduranceOrigin;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public enum QuickSneak implements CombatAbility, EnduranceOrigin {

    INSTANCE;

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_endurance_quick_sneak");
    }


    private static final NamespacedKey SNEAK_SPEED_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_endurance_quick_sneak_sneak_speed");

    @Override
    public void applyAbility(Player player, int level) {
        if(level > getMaxAbilityLevel()) level = getMaxAbilityLevel();
        removeAbility(player);
        setAbilityLevel(player, level);
        player.getAttribute(Attribute.PLAYER_SNEAKING_SPEED).addModifier(new AttributeModifier(SNEAK_SPEED_KEY, 0.15 * level, AttributeModifier.Operation.MULTIPLY_SCALAR_1));
    }

    @Override
    public void removeAbility(Player player) {
        setAbilityLevel(player, 0);
        player.getAttribute(Attribute.PLAYER_SNEAKING_SPEED).removeModifier(SNEAK_SPEED_KEY);
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
        return 0;
    }

    @Override
    public int getColumn() {
        return 5;
    }

    @Override
    public String getAbilityName() {
        return "&#001096&lɴ&#000D8A&lɪ&#000A7F&lɴ&#000673&lᴊ&#000367&lᴀ";
    }

    @Override
    public List<String> getAbilityDescription(int playerLevel) {
        ArrayList<String> lore = new ArrayList<>();

        switch (playerLevel) {

            case 0 -> {
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));

                lore.add(ColorFixer.addColors("&7ᴢᴡɪęᴋꜱᴢᴀ ᴘʀęᴅᴋᴏść ꜱᴋʀᴀᴅᴀɴɪᴀ ꜱɪę ᴏ &a15%"));
            }
            case 1 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴢᴡɪęᴋꜱᴢᴀ ᴘʀęᴅᴋᴏść ꜱᴋʀᴀᴅᴀɴɪᴀ ꜱɪę ᴏ 15%"));

                lore.add(" ");

                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴢᴡɪęᴋꜱᴢᴀ ᴘʀęᴅᴋᴏść ꜱᴋʀᴀᴅᴀɴɪᴀ ꜱɪę ᴏ &a30%"));
            }
            case 2 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴢᴡɪęᴋꜱᴢᴀ ᴘʀęᴅᴋᴏść ꜱᴋʀᴀᴅᴀɴɪᴀ ꜱɪę ᴏ 30%"));

                lore.add(" ");
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴢᴡɪęᴋꜱᴢᴀ ᴘʀęᴅᴋᴏść ꜱᴋʀᴀᴅᴀɴɪᴀ ꜱɪę ᴏ &a45%"));
            }
            case 3 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));

                lore.add(ColorFixer.addColors("&7ᴢᴡɪęᴋꜱᴢᴀ ᴘʀęᴅᴋᴏść ꜱᴋʀᴀᴅᴀɴɪᴀ ꜱɪę ᴏ 45%"));
            }

        }
        return lore;
    }


}
