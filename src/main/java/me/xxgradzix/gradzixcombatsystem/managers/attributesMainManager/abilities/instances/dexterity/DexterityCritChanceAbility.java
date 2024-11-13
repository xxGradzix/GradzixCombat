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

public enum DexterityCritChanceAbility implements CombatAbility, DexterityOrigin {

    INSTANCE;

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_dexterity_crit_chance");
    }

    @Override
    public void applyAbility(Player player, int level) {
        if(level > getMaxAbilityLevel()) level = getMaxAbilityLevel();
        removeAbility(player);
        setAbilityLevel(player, level);
        //TODO: Add crit chance
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
        return Set.of(AssassinAbility.INSTANCE, BowSwordComboAbility.INSTANCE);
    }

    @Override
    public int getRow() {
        return 3;
    }

    @Override
    public int getColumn() {
        return 4;
    }

    @Override
    public String getAbilityName() {
        return "&#BC001E&lᴜ&#B90623&lᴅ&#B60D28&lᴇ&#B4132D&lʀ&#B11A32&lᴢ&#AE2037&lᴇ&#AB273C&lɴ&#A92D41&lɪ&#A63446&lᴀ &#A04150&lᴋ&#9E4755&lʀ&#9B4E5A&lʏ&#98545F&lᴛ&#98545F&lʏ&#98545F&lᴄ&#98545F&lᴢ&#98545F&lɴ&#98545F&lᴇ";
    }

    @Override
    public List<String> getAbilityDescription(int playerLevel) {
        ArrayList<String> lore = new ArrayList<>();

        switch (playerLevel) {

            case 0 -> {
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));

                lore.add(ColorFixer.addColors("&7ᴢᴡɪęᴋꜱᴢᴀ ꜱᴢᴀɴꜱᴇ ɴᴀ ᴜᴅᴇʀᴢᴇɴɪᴇ ᴋʀʏᴛʏᴄᴢɴᴇ ᴏ &a5%"));
                lore.add(ColorFixer.addColors("&7ᴘʀᴇᴍɪᴀ ᴅᴏᴛʏᴄᴢʏ łᴜᴋóᴡ ɪ ᴍɪᴇᴄᴢʏ"));
            }
            case 1 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴢᴡɪęᴋꜱᴢᴀ ꜱᴢᴀɴꜱᴇ ɴᴀ ᴜᴅᴇʀᴢᴇɴɪᴇ ᴋʀʏᴛʏᴄᴢɴᴇ ᴏ 5%"));
                lore.add(ColorFixer.addColors("&7ᴘʀᴇᴍɪᴀ ᴅᴏᴛʏᴄᴢʏ łᴜᴋóᴡ ɪ ᴍɪᴇᴄᴢʏ"));

                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));

                lore.add(ColorFixer.addColors("&7ᴢᴡɪęᴋꜱᴢᴀ ꜱᴢᴀɴꜱᴇ ɴᴀ ᴜᴅᴇʀᴢᴇɴɪᴇ ᴋʀʏᴛʏᴄᴢɴᴇ ᴏ &a10%"));
                lore.add(ColorFixer.addColors("&7ᴘʀᴇᴍɪᴀ ᴅᴏᴛʏᴄᴢʏ łᴜᴋóᴡ ɪ ᴍɪᴇᴄᴢʏ"));
            }
            case 2 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));

                lore.add(ColorFixer.addColors("&7ᴢᴡɪęᴋꜱᴢᴀ ꜱᴢᴀɴꜱᴇ ɴᴀ ᴜᴅᴇʀᴢᴇɴɪᴇ ᴋʀʏᴛʏᴄᴢɴᴇ ᴏ 10%"));
                lore.add(ColorFixer.addColors("&7ᴘʀᴇᴍɪᴀ ᴅᴏᴛʏᴄᴢʏ łᴜᴋóᴡ ɪ ᴍɪᴇᴄᴢʏ"));

                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴢᴡɪęᴋꜱᴢᴀ ꜱᴢᴀɴꜱᴇ ɴᴀ ᴜᴅᴇʀᴢᴇɴɪᴇ ᴋʀʏᴛʏᴄᴢɴᴇ ᴏ &a15%"));
                lore.add(ColorFixer.addColors("&7ᴘʀᴇᴍɪᴀ ᴅᴏᴛʏᴄᴢʏ łᴜᴋóᴡ ɪ ᴍɪᴇᴄᴢʏ"));
            }
            case 3 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴢᴡɪęᴋꜱᴢᴀ ꜱᴢᴀɴꜱᴇ ɴᴀ ᴜᴅᴇʀᴢᴇɴɪᴇ ᴋʀʏᴛʏᴄᴢɴᴇ ᴏ &a15%"));
                lore.add(ColorFixer.addColors("&7ᴘʀᴇᴍɪᴀ ᴅᴏᴛʏᴄᴢʏ łᴜᴋóᴡ ɪ ᴍɪᴇᴄᴢʏ"));
            }

        }
        return lore;
    }

}
