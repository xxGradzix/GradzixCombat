package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances;

import me.xxgradzix.gradzixcombatsystem.guis.attributes.AbilitiesGuiManager;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.AttributeOrigin;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.DexterityOrigin;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.EnduranceOrigin;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.IntelligenceOrigin;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface CombatAbility{

    NamespacedKey getKey();

    default int getAbilityLevel(Player player) {
        return player.getPersistentDataContainer().getOrDefault(getKey(), PersistentDataType.INTEGER, 0);
    }
    default void setAbilityLevel(Player player, int level) {
        player.getPersistentDataContainer().set(getKey(), PersistentDataType.INTEGER, level);
    }

    int getRequiredAttributeLevel();

    int getMaxAbilityLevel();

    void applyAbility(Player player, int level);
    void removeAbility(Player player);

    Set<CombatAbility> getRequiredAbilities();

    default boolean hasAbility(Player player) {
        return getAbilityLevel(player) > 0;
    }

    int getRow();
    int getColumn();

    String getAbilityName();
    List<String> getAbilityDescription(int playerLevel);

    default ItemStack getDisplayItem(Player player) {
        ItemStack item = new ItemStack(Material.GLASS_PANE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setCustomModelData(getCustomModelData(player));

        int level = getAbilityLevel(player);
        int maxLevel = getMaxAbilityLevel();
        boolean hasAbility = level > 0;

        String color = hasAbility ? "&a" : "&c";
        itemMeta.setDisplayName(ColorFixer.addColors( getAbilityName() + " " + color + "§l" + level + "§l" + "/" + "§l" + maxLevel));


        ArrayList<String> lore = new ArrayList<>();
        lore.add(ColorFixer.addColors("&8&l⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺⎺"));

        String unlockStatus = hasAbility ? "&a§lᴏᴅʙʟᴏᴋᴏᴡᴀɴᴇ" : "&c§lᴢᴀʙʟᴏᴋᴏᴡᴀɴᴇ";
        lore.add(ColorFixer.addColors(unlockStatus));
        lore.add(" ");
        lore.addAll(getAbilityDescription(level));
        lore.add(" ");
        if(this instanceof AttributeOrigin attributeOrigin) {
            lore.add(ColorFixer.addColors("&7ᴡʏᴍᴀɢᴀɴʏ ᴘᴏᴢɪᴏᴍ " + attributeOrigin.getAttributeNameFormated() + "&7: #877239" + "§l" + getRequiredAttributeLevel()));
        }
        lore.add(ColorFixer.addColors("&8&l⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽"));

        Set<CombatAbility> requiredAbilities = getRequiredAbilities();

        if(!requiredAbilities.isEmpty() && !hasAbility) {
            lore.add(" ");
            lore.add(ColorFixer.addColors("&7ᴀʙʏ ᴏᴅʙʟᴏᴋᴏᴡᴀć ᴜᴍɪᴇᴊęᴛɴᴏść ᴍᴜꜱɪꜱᴢ ᴘᴏꜱɪᴀᴅᴀć:"));
            for(CombatAbility ability : requiredAbilities) {

                String haveAbilityColor = ability.hasAbility(player) ? "&a" : "&c";
                lore.add(ColorFixer.addColors("&8• &7" + haveAbilityColor + ability.getAbilityName()));
            }
        }

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }
    default int getCustomModelData(Player player) {

        int originOffSet = 0;

        if(this instanceof EnduranceOrigin) originOffSet = 1000;
        if(this instanceof DexterityOrigin) originOffSet = 2000;
        if(this instanceof IntelligenceOrigin) originOffSet = 3000;


        boolean hasAbility = hasAbility(player);

        boolean isInitialLevel = getRequiredAbilities().isEmpty();

        boolean havePreviousAbilities = getRequiredAbilities().stream().allMatch(ability -> ability.hasAbility(player));

        if(isInitialLevel) {
            if(hasAbility) {
                // unlocked and active initial level
                return 1020774 + originOffSet;
            } else {
                // default not unlocked initial level
                return 1020771;
            }
        }

        // unlocked and active
        if(hasAbility) return 1020773 + originOffSet;

        // possible to unlock
        if(havePreviousAbilities) return 1020772;

        // default not unlocked (invisible)
        return 1020770;

    }

    default Set<AbilitiesGuiManager.SlotData> getCustomLines() {
        return Set.of();
    }
}
