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

public enum BowSwordComboAbility implements CombatAbility, DexterityOrigin {

    INSTANCE;

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_dexterity_bow_sword_combo");
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
        return 3;
    }

    @Override
    public int getMaxAbilityLevel() {
        return 1;
    }

    @Override
    public Set<CombatAbility> getRequiredAbilities() {
        return Set.of(StandingShotAbility.INSTANCE);
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
        return "&#3EB873&lʜ&#50BD72&lᴀ&#61C271&lʀ&#73C770&lᴍ&#85CC6F&lᴏ&#97D26E&lɴ&#A8D76D&lɪ&#BADC6C&lᴀ &#DDE66A&lʙ&#EFEB69&lɪ&#EFEB69&lᴛ&#EFEB69&lᴇ&#EFEB69&lᴡ&#EFEB69&lɴ&#EFEB69&lᴀ";
    }

    @Override
    public List<String> getAbilityDescription(int playerLevel) {

        int currentLevel = playerLevel;

        ArrayList<String> lore = new ArrayList<>();

        switch (currentLevel) {

            case 0 -> {
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7XXXX"));

            }
            case 1 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7XXXX"));

            }

        }
        return lore;

    }

}
