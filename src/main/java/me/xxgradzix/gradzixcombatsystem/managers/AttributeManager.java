package me.xxgradzix.gradzixcombatsystem.managers;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class AttributeManager {

    private static final NamespacedKey STRENGTH_ATTRIBUTE_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "strength_attribute");
    private static final NamespacedKey AGILITY_ATTRIBUTE_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "agility_attribute");
    private static final NamespacedKey DEXTERITY_ATTRIBUTE_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "dexterity_attribute");
    private static final NamespacedKey INTELLIGENCE_ATTRIBUTE_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "intelligence_attribute");

    private static final NamespacedKey STRENGTH_REQUIREMENT_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "strength_requirement");
    private static final NamespacedKey AGILITY_REQUIREMENT_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "agility_requirement");
    private static final NamespacedKey DEXTERITY_REQUIREMENT_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "dexterity_requirement");
    private static final NamespacedKey INTELLIGENCE_REQUIREMENT_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "intelligence_requirement");


    public static void incrementAttributeLevel(LivingEntity player, CombatAttribute combatAttribute) {
        PersistentDataContainer persistentDataContainer = player.getPersistentDataContainer();
        NamespacedKey playerAttributeKeyByAttribute = getPlayerAttributeKeyByAttribute(combatAttribute);
        int attributeLevel = persistentDataContainer.getOrDefault(playerAttributeKeyByAttribute, PersistentDataType.INTEGER, 0);

        persistentDataContainer.set(playerAttributeKeyByAttribute, PersistentDataType.INTEGER, attributeLevel + 1);
    }

    public static void setAttributeLevel(LivingEntity player, CombatAttribute attribute, int value) {
        PersistentDataContainer persistentDataContainer = player.getPersistentDataContainer();
        persistentDataContainer.set(getPlayerAttributeKeyByAttribute(attribute), PersistentDataType.INTEGER, value);
    }

    public static int getAttributeLevel(LivingEntity player, CombatAttribute attribute) {
        PersistentDataContainer persistentDataContainer = player.getPersistentDataContainer();
        return persistentDataContainer.getOrDefault(getPlayerAttributeKeyByAttribute(attribute), PersistentDataType.INTEGER, 0);
    }

    public static int getAttributeRequirement(ItemStack item, CombatAttribute attribute) {
        if(item == null || item.getItemMeta() == null) return 0;
        PersistentDataContainer persistentDataContainer = item.getItemMeta().getPersistentDataContainer();
        return persistentDataContainer.getOrDefault(getRequirementKeyByAttribute(attribute), PersistentDataType.INTEGER, 0);
    }

    public static void setAttributeRequirement(ItemStack item, CombatAttribute attribute, int value) {
        if(item == null || item.getItemMeta() == null) return;
        ItemMeta itemMeta = item.getItemMeta();
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        persistentDataContainer.set(getRequirementKeyByAttribute(attribute), PersistentDataType.INTEGER, value);
        item.setItemMeta(itemMeta);
    }

    private static NamespacedKey getPlayerAttributeKeyByAttribute(CombatAttribute attribute) {
        switch (attribute) {
            case STRENGTH -> {
                return STRENGTH_ATTRIBUTE_KEY;
            }
            case AGILITY -> {
                return AGILITY_ATTRIBUTE_KEY;
            }
            case DEXTERITY -> {
                return DEXTERITY_ATTRIBUTE_KEY;
            }
            case INTELLIGENCE -> {
                return INTELLIGENCE_ATTRIBUTE_KEY;
            }
            default -> {
                throw new IllegalArgumentException("Unexpected value: " + attribute);
            }
        }
    }

    private static NamespacedKey getRequirementKeyByAttribute(CombatAttribute attribute) {
        switch (attribute) {
            case STRENGTH -> {
                return STRENGTH_REQUIREMENT_KEY;
            }
            case AGILITY -> {
                return AGILITY_REQUIREMENT_KEY;
            }
            case DEXTERITY -> {
                return DEXTERITY_REQUIREMENT_KEY;
            }
            case INTELLIGENCE -> {
                return INTELLIGENCE_REQUIREMENT_KEY;
            }
            default -> {
                throw new IllegalArgumentException("Unexpected value: " + attribute);
            }
        }
    }

    public static boolean hasRequiredAttribute(ItemStack item, Player player) {

        for (CombatAttribute attribute : CombatAttribute.values()) {
            int requiredLevel = getAttributeRequirement(item, attribute);

            int playerLevel = getAttributeLevel(player, attribute);

            if (playerLevel < requiredLevel) {
                return false;
            }
        }
        return true;

    }

    public static void resetAttributes(Player player) {
        for (CombatAttribute attribute : CombatAttribute.values()) {
            setAttributeLevel(player, attribute, 0);
        }
    }
}