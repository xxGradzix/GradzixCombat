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

public enum BuildRange implements CombatAbility, EnduranceOrigin {

    INSTANCE;

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_endurance_build_range");
    }


    private static final NamespacedKey BLOCK_INTERACT_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_endurance_build_range_interact");

    @Override
    public void applyAbility(Player player, int level) {
        if(level > getMaxAbilityLevel()) level = getMaxAbilityLevel();
        removeAbility(player);
        setAbilityLevel(player, level);
        player.getAttribute(Attribute.PLAYER_BLOCK_INTERACTION_RANGE).addModifier(new AttributeModifier(BLOCK_INTERACT_KEY, 1, AttributeModifier.Operation.ADD_NUMBER));
    }

    @Override
    public void removeAbility(Player player) {
        setAbilityLevel(player, 0);
        player.getAttribute(Attribute.PLAYER_BLOCK_INTERACTION_RANGE).removeModifier(BLOCK_INTERACT_KEY);
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
        return Set.of(QuickSneak.INSTANCE);
    }

    @Override
    public int getRow() {
        return 2;
    }

    @Override
    public int getColumn() {
        return 5;
    }

    @Override
    public String getAbilityName() {
        return "&#532C00&lʙ&#4E2B02&lᴜ&#492905&lᴅ&#442807&lᴏ&#3F260A&lᴡ&#3A250C&lɴ&#35230E&lɪ&#302211&lᴄ&#2B2013&lᴢ&#2B2013&lʏ";
    }

    @Override
    public List<String> getAbilityDescription(int playerLevel) {
        ArrayList<String> lore = new ArrayList<>();

        switch (playerLevel) {

            case 0 -> {
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));

                lore.add(ColorFixer.addColors("&7ᴢᴡɪęᴋꜱᴢᴀ ᴢᴀꜱɪęɢ ʙᴜᴅᴏᴡᴀɴɪᴀ ɪ ɴɪꜱᴢᴄᴢᴇɴɪᴀ"));
                lore.add(ColorFixer.addColors("&7ʙʟᴏᴋóᴡ ᴏ &a1 &7ʙʟᴏᴋ"));
            }
            case 1 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴢᴡɪęᴋꜱᴢᴀ ᴢᴀꜱɪęɢ ʙᴜᴅᴏᴡᴀɴɪᴀ ɪ ɴɪꜱᴢᴄᴢᴇɴɪᴀ"));
                lore.add(ColorFixer.addColors("&7ʙʟᴏᴋóᴡ ᴏ &a1 &7ʙʟᴏᴋ"));

            }

        }
        return lore;
    }


}
