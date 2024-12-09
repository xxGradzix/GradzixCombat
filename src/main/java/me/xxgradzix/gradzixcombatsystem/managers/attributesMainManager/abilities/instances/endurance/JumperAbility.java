package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.endurance;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.EnduranceOrigin;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.CombatAbility;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public enum JumperAbility implements CombatAbility, EnduranceOrigin {

    INSTANCE;

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_endurance_jumper");
    }

    private static final NamespacedKey JUMP_HEIGHT_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_endurance_jumper_height");

    @Override
    public void applyAbility(Player player, int level) {
        if(level > getMaxAbilityLevel()) level = getMaxAbilityLevel();
        removeAbility(player);
        setAbilityLevel(player, level);
        player.getAttribute(Attribute.GENERIC_JUMP_STRENGTH).addModifier(new AttributeModifier(JUMP_HEIGHT_KEY, 0.15, AttributeModifier.Operation.MULTIPLY_SCALAR_1));
    }

    @Override
    public void removeAbility(Player player) {
        setAbilityLevel(player, 0);
        player.getAttribute(Attribute.GENERIC_JUMP_STRENGTH).removeModifier(JUMP_HEIGHT_KEY);
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
        return Set.of(SprinterAbility.INSTANCE);
    }

    @Override
    public int getRow() {
        return 5;
    }

    @Override
    public int getColumn() {
        return 6;
    }

    @Override
    public String getAbilityName() {
        return "&#00BC3A&lꜱ&#11B24B&lᴋ&#23A85C&lᴏ&#349E6D&lᴄ&#45947E&lᴢ&#45947E&lᴇ&#45947E&lᴋ";
    }

    @Override
    public List<String> getAbilityDescription(int playerLevel) {
        ArrayList<String> lore = new ArrayList<>();

        switch (playerLevel) {

            case 0 -> {
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));

                lore.add(ColorFixer.addColors("&7ᴢᴡɪᴇᴋꜱᴢᴀ ᴡʏꜱᴏᴋᴏść ꜱᴋᴏᴋᴜ ᴏ &a15%"));
            }
            case 1 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴢᴡɪᴇᴋꜱᴢᴀ ᴡʏꜱᴏᴋᴏść ꜱᴋᴏᴋᴜ ᴏ 15%"));

            }

        }
        return lore;
    }


}
