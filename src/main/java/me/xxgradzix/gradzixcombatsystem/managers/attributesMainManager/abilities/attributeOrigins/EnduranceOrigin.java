package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins;

import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.CombatAttribute;

public interface EnduranceOrigin extends AttributeOrigin{

    default CombatAttribute getAttributeOrigin() {
        return CombatAttribute.ENDURANCE;
    }

    default String getAttributeNameFormated() {
        return "&7ᴡʏᴛʀᴢʏᴍᴀłᴏśᴄɪ";
    }
}
