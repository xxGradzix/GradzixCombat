package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins;

import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.CombatAttribute;

public interface IntelligenceOrigin extends AttributeOrigin {

    default CombatAttribute getAttributeOrigin() {
        return CombatAttribute.INTELLIGENCE;
    }

    default String getAttributeNameFormated() {
        return "#81ecf0ɪɴᴛᴇʟɪɢᴇɴᴄᴊɪ";
    }
}
