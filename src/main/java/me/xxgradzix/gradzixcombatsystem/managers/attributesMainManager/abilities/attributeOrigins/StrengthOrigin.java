package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins;

import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.CombatAttribute;

public interface StrengthOrigin extends AttributeOrigin {

    default CombatAttribute getAttributeOrigin() {
        return CombatAttribute.STRENGTH;
    }


    default String getAttributeNameFormated() {
        return "&cꜱɪᴌʏ";
    }

}
