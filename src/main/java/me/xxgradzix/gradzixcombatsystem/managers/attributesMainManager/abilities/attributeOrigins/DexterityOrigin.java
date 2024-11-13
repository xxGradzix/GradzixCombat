package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins;

import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.CombatAttribute;

public interface DexterityOrigin extends AttributeOrigin {

    default CombatAttribute getAttributeOrigin() {
        return CombatAttribute.DEXTERITY;
    }


    default String getAttributeNameFormated() {
        return "#c3f075ᴢʀęᴄᴢɴᴏśᴄɪ";
    }
}
