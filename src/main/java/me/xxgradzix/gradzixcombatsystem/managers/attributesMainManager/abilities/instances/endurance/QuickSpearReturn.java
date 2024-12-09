package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.endurance;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.EnduranceOrigin;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.CombatAbility;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.EventableAbility;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public enum QuickSpearReturn implements CombatAbility, EnduranceOrigin, EventableAbility {

    INSTANCE;

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_endurance_quick_spear");
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
        return 2;
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
        return 2;
    }

    @Override
    public String getAbilityName() {
        return "&#ACACAC&lᴍ&#A4A8A8&lᴀ&#9DA4A4&lɢ&#959FA0&lɴ&#8D9B9C&lᴇ&#869798&lᴛ&#7E9394&lʏ&#768E90&lᴄ&#6F8A8D&lᴢ&#678689&lɴ&#5F8285&lʏ &#50797D&lᴄ&#487579&lʜ&#407175&lᴡ&#396C71&lʏ&#31686D&lᴛ";
    }

    @Override
    public List<String> getAbilityDescription(int playerLevel) {
        ArrayList<String> lore = new ArrayList<>();

        switch (playerLevel) {

            case 0 -> {
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ʀᴢᴜᴄᴏɴᴇ ᴡłóᴄᴢɴɪᴇ ᴡʀᴀᴄᴀᴊą ᴅᴏ ᴄɪᴇʙɪᴇ"));
                lore.add(ColorFixer.addColors("&7ꜱᴢʏʙᴄɪᴇᴊ ᴏ &a25%"));

            }
            case 1 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));

                lore.add(ColorFixer.addColors("&7ʀᴢᴜᴄᴏɴᴇ ᴡłóᴄᴢɴɪᴇ ᴡʀᴀᴄᴀᴊą ᴅᴏ ᴄɪᴇʙɪᴇ"));
                lore.add(ColorFixer.addColors("&7ꜱᴢʏʙᴄɪᴇᴊ ᴏ 25%"));

                lore.add(" ");
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));

                lore.add(ColorFixer.addColors("&7ʀᴢᴜᴄᴏɴᴇ ᴡłóᴄᴢɴɪᴇ ᴡʀᴀᴄᴀᴊą ᴅᴏ ᴄɪᴇʙɪᴇ"));
                lore.add(ColorFixer.addColors("&7ꜱᴢʏʙᴄɪᴇᴊ ᴏ &a50%"));
            }
            case 2 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ʀᴢᴜᴄᴏɴᴇ ᴡłóᴄᴢɴɪᴇ ᴡʀᴀᴄᴀᴊą ᴅᴏ ᴄɪᴇʙɪᴇ"));
                lore.add(ColorFixer.addColors("&7ꜱᴢʏʙᴄɪᴇᴊ ᴏ &a50%"));
            }

        }
        return lore;
    }


    @Override
    public Listener getListener() {
        return new Listener() {

            @EventHandler
            public void onSpearThrow(ProjectileLaunchEvent event) {

                if(!(event.getEntity().getShooter() instanceof Player player)) return;

                if(QuickSpearReturn.INSTANCE.getAbilityLevel(player) < 1) return;

                if(!(event.getEntity() instanceof Trident trident)) return;

                trident.setLoyaltyLevel(1 + getAbilityLevel(player));

            }

        };
    }
}
