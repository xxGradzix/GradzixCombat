package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager;

import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;

public enum CombatAttribute {
    STRENGTH("&cꜱɪᴌᴀ"), ENDURANCE("&7ᴡʏᴛʀᴢʏᴍᴀᴌᴏść"), DEXTERITY("#c3f075ᴢʀęᴄᴢɴᴏść"), INTELLIGENCE("#81ecf0ɪɴᴛᴇʟɪɢᴇɴᴄᴊᴀ");

    private String name;

    CombatAttribute(String name) {
        this.name = ColorFixer.addColors(name);
    }

    public String getCustomName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}