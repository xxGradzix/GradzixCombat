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

    private static final NamespacedKey TOTAL_ATTRIBUTE_POINTS_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "total_attribute_points");
    private static final NamespacedKey FREE_ATTRIBUTE_POINTS_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "free_attribute_points");

    private static final NamespacedKey LAST_ACTION_TIMESTAMP_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "last_action_timestamp");

    private static final NamespacedKey STRENGTH_ATTRIBUTE_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "strength_attribute");
    private static final NamespacedKey ENDURANCE_ATTRIBUTE_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "endurance_attribute");
    private static final NamespacedKey DEXTERITY_ATTRIBUTE_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "dexterity_attribute");
    private static final NamespacedKey INTELLIGENCE_ATTRIBUTE_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "intelligence_attribute");

    private static final NamespacedKey STRENGTH_REQUIREMENT_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "strength_requirement");
    private static final NamespacedKey ENDURANCE_REQUIREMENT_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "endurance_requirement");
    private static final NamespacedKey DEXTERITY_REQUIREMENT_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "dexterity_requirement");
    private static final NamespacedKey INTELLIGENCE_REQUIREMENT_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "intelligence_requirement");

    public static int getTotalAttributePoints(Player player) {
        PersistentDataContainer persistentDataContainer = player.getPersistentDataContainer();
        return persistentDataContainer.getOrDefault(TOTAL_ATTRIBUTE_POINTS_KEY, PersistentDataType.INTEGER, 0);
    }
    public static int getFreeAttributePoints(Player player) {
        PersistentDataContainer persistentDataContainer = player.getPersistentDataContainer();
        return persistentDataContainer.getOrDefault(FREE_ATTRIBUTE_POINTS_KEY, PersistentDataType.INTEGER, 0);
    }

    private static void setTotalAttributePoints(LivingEntity player, int value) {
        PersistentDataContainer persistentDataContainer = player.getPersistentDataContainer();
        persistentDataContainer.set(TOTAL_ATTRIBUTE_POINTS_KEY, PersistentDataType.INTEGER, value);
        updateTimeStamp(player);
    }
    private static void setFreeAttributePointsKey(LivingEntity player, int value) {
        PersistentDataContainer persistentDataContainer = player.getPersistentDataContainer();
        persistentDataContainer.set(FREE_ATTRIBUTE_POINTS_KEY, PersistentDataType.INTEGER, value);
        updateTimeStamp(player);
    }

    public static void givePointsToPlayer(Player player, int points) {
        int totalAttributePoints = getTotalAttributePoints(player);
        if(totalAttributePoints + points < 0 || totalAttributePoints + points > 25) {
            return;
        }
        setTotalAttributePoints(player, totalAttributePoints + points);
        setFreeAttributePointsKey(player, getFreeAttributePoints(player) + points);
        updateTimeStamp(player);
    }

    public static void updateTimeStamp(LivingEntity player) {
        PersistentDataContainer persistentDataContainer = player.getPersistentDataContainer();
        persistentDataContainer.set(LAST_ACTION_TIMESTAMP_KEY, PersistentDataType.LONG, System.currentTimeMillis());
    }

    public static boolean incrementAttributeLevel(LivingEntity player, CombatAttribute combatAttribute) {
        final int POINTS_TO_ADD = 1;
        PersistentDataContainer persistentDataContainer = player.getPersistentDataContainer();

        int freeAttributePoints = getFreeAttributePoints((Player) player);
        if (freeAttributePoints - POINTS_TO_ADD < 0) {
            return false;
        }

        NamespacedKey playerAttributeKeyByAttribute = getPlayerAttributeKeyByAttribute(combatAttribute);
        int attributeLevel = persistentDataContainer.getOrDefault(playerAttributeKeyByAttribute, PersistentDataType.INTEGER, 0);

        if(haveMaxAttributeLevel(combatAttribute, attributeLevel)) {
            return false;
        }
        persistentDataContainer.set(playerAttributeKeyByAttribute, PersistentDataType.INTEGER, attributeLevel + POINTS_TO_ADD);
        setFreeAttributePointsKey((Player) player, freeAttributePoints - POINTS_TO_ADD);
        updateTimeStamp(player);
        return true;
    }

    private static void setAttributeLevel(LivingEntity player, CombatAttribute attribute, int value) {
        PersistentDataContainer persistentDataContainer = player.getPersistentDataContainer();
        persistentDataContainer.set(getPlayerAttributeKeyByAttribute(attribute), PersistentDataType.INTEGER, value);
    }

    public static int getAttributeLevel(LivingEntity player, CombatAttribute attribute) {
        PersistentDataContainer persistentDataContainer = player.getPersistentDataContainer();
        return persistentDataContainer.getOrDefault(getPlayerAttributeKeyByAttribute(attribute), PersistentDataType.INTEGER, 0);
    }

    public static boolean haveMaxAttributeLevel(CombatAttribute attribute, int value) {
        switch (attribute) {
            case STRENGTH, INTELLIGENCE, ENDURANCE, DEXTERITY -> {
                return value >= 12;
            }
            default -> {
                throw new IllegalArgumentException("Unexpected value: " + attribute);
            }
        }
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
            case ENDURANCE -> {
                return ENDURANCE_ATTRIBUTE_KEY;
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
            case ENDURANCE -> {
                return ENDURANCE_REQUIREMENT_KEY;
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

    public static void resetAttributes(Player player, int initialPoints) {
        for (CombatAttribute attribute : CombatAttribute.values()) {
            setAttributeLevel(player, attribute, 0);
        }
        setTotalAttributePoints(player, initialPoints);
        setFreeAttributePointsKey(player, initialPoints);
        updateTimeStamp(player);
    }
}
