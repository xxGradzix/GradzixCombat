package me.xxgradzix.gradzixcombatsystem.guis.attributes;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcombatsystem.items.ItemManager;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.AbilitiesPointsManager;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.AttributePointsManager;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.CombatAttribute;
import me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager;
import me.xxgradzix.gradzixcombatsystem.managers.messages.MessageType;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public class AttributesGuiManager {

    private final Gui gui;
    private final Player player;

    public AttributesGuiManager(Player player) {

        this.player = player;

        this.gui = Gui.gui()
                .title(Component.text(ColorFixer.addColors("&f七七七七七七七七七七≒")))
                .rows(4)
                .disableAllInteractions()
                .create();

        gui.disableAllInteractions();

        setAttributeGuiItems();

        gui.open(player);
    }

    private void setAttributeGuiItems() {

        int slot = 1;

        for (CombatAttribute attribute : CombatAttribute.values()) {

            for (int i = 1; i<=2; i++) {
                GuiItem attributeGuiItem = new GuiItem(ItemManager.getAttributeItem(player, attribute, AttributePointsManager.getAttributeLevel(player, attribute), i));

                attributeGuiItem.setAction((e) -> {

                    if(e.isRightClick()) {
                        AbilitiesPointsManager.openAbilitiesGui(player, attribute);
                        return;
                    }

                    boolean isSuccessful = AttributePointsManager.incrementAttributeLevel(player, attribute);

                    if(isSuccessful) {
                        setAttributeGuiItems();
                    } else {
                        MessageManager.sendMessageFormated(player, MessageManager.NOT_ENOUGH_FREE_POINTS, MessageType.CHAT);
                    }

                });
                gui.updateItem(i == 2 ? slot + 9 : slot, attributeGuiItem);

            }

            slot+=2;
        }

        int freeAttributePoints = AttributePointsManager.getFreeAttributePoints(player);
        GuiItem freePointsItem = new GuiItem(ItemManager.getFreePointsItem(freeAttributePoints));

        gui.updateItem(4, 5, freePointsItem);
    }

}
