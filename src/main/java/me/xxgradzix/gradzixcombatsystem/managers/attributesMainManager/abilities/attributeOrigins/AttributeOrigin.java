package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins;

import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.CombatAttribute;
import org.bukkit.attribute.Attribute;

public interface AttributeOrigin {

    CombatAttribute getAttributeOrigin();

    String getAttributeNameFormated();
}
