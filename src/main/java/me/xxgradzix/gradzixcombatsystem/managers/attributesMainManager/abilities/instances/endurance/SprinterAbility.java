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

public enum SprinterAbility implements CombatAbility, EnduranceOrigin {

    INSTANCE;

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_endurance_sprinter");
    }


    private static final NamespacedKey RUN_SPEED_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_endurance_sprinter_move_speed");

    @Override
    public void applyAbility(Player player, int level) {
        if(level > getMaxAbilityLevel()) level = getMaxAbilityLevel();
        removeAbility(player);
        setAbilityLevel(player, level);
        player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).addModifier(new AttributeModifier(RUN_SPEED_KEY, 0.1, AttributeModifier.Operation.MULTIPLY_SCALAR_1));
    }

    @Override
    public void removeAbility(Player player) {
        setAbilityLevel(player, 0);
        player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).removeModifier(RUN_SPEED_KEY);
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
        return Set.of(SprintingAttack.INSTANCE, BuildRange.INSTANCE);
    }

    @Override
    public int getRow() {
        return 3;
    }

    @Override
    public int getColumn() {
        return 6;
    }

    @Override
    public String getAbilityName() {
        return "&#FFC400&lꜱ&#FFCC24&lᴘ&#FFD549&lʀ&#FFDD6D&lɪ&#FFE692&lɴ&#FFEEB6&lᴛ&#FFF7DB&lᴇ&#FFFFFF&lʀ";
    }

    @Override
    public List<String> getAbilityDescription(int playerLevel) {
        ArrayList<String> lore = new ArrayList<>();

        switch (playerLevel) {

            case 0 -> {
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));

                lore.add(ColorFixer.addColors("&7ᴢᴡɪᴇᴋꜱᴢᴀ ꜱᴢʏʙᴋᴏść ʙɪᴇɢᴜ ᴏ &a10%"));
            }
            case 1 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴢᴡɪᴇᴋꜱᴢᴀ ꜱᴢʏʙᴋᴏść ʙɪᴇɢᴜ ᴏ 10%"));

            }

        }
        return lore;
    }


}
