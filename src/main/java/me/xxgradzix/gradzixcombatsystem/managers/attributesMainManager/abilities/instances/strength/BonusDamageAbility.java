package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.strength;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.guis.attributes.AbilitiesGuiManager;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.StrengthOrigin;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.CombatAbility;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
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
    private static final NamespacedKey STRENGTH_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_strength_bonus_damage_strength");

    @Override
    public void applyAbility(Player player, int level) {
        if(level > getMaxAbilityLevel()) level = getMaxAbilityLevel();
        removeAbility(player);
        setAbilityLevel(player, level);
        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).addModifier(new org.bukkit.attribute.AttributeModifier(STRENGTH_KEY, 1, org.bukkit.attribute.AttributeModifier.Operation.ADD_NUMBER));
    }

    @Override
    public void removeAbility(Player player) {
        setAbilityLevel(player, 0);
        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).removeModifier(STRENGTH_KEY);
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
        return Set.of(StrengthCritChanceAbility.INSTANCE, BerserkerAbility.INSTANCE);
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
        return "&#CA0000&lᴀ&#B90000&lɢ&#A70000&lʀ&#960000&lᴇ&#850000&lꜱ&#730000&lᴏ&#620000&lʀ";
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

    @Override
    public Set<AbilitiesGuiManager.SlotData> getCustomLines() {

        return Set.of(
                new AbilitiesGuiManager.SlotData(getRow(), getColumn()-1, AbilitiesGuiManager.CurveType.LEFT_DOWN),
                new AbilitiesGuiManager.SlotData(getRow(), getColumn()+1, AbilitiesGuiManager.CurveType.LEFT),
                new AbilitiesGuiManager.SlotData(getRow()-1, getColumn()-1, AbilitiesGuiManager.CurveType.RIGHT_UP)
        );
    }




}
