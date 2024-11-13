package me.xxgradzix.gradzixcombatsystem.items.weapons;

import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.AttributePointsManager;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.CombatAttribute;
import org.bukkit.inventory.ItemStack;

public interface Attributable {

    default void setAttributes(ItemStack itemStack, int tier) {
        for (CombatAttribute combatAttribute : CombatAttribute.values()) {
            int requiredAttribute = getRequiredAttribute(tier, combatAttribute);
            if(requiredAttribute != 0) {
                AttributePointsManager.setAttributeRequirement(itemStack, combatAttribute, requiredAttribute);
            }
        }
    }

    int getRequiredAttribute(int tier, CombatAttribute attribute);

}
