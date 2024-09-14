package me.xxgradzix.gradzixcombatsystem.upgradeGuis;

import dev.triumphteam.gui.guis.Gui;
import org.bukkit.entity.Player;

public class ReforgeGuiManager {

    private static final int REFORGE_ITEM_SLOT = 11;

    public static void openReforgeGui(Player player, double priceModifier) {

        Gui gui = new Gui(3, "Reforge");

        gui.setDragAction(event -> event.setCancelled(true));

        gui.setDefaultClickAction(event -> {
            int slot = event.getSlot();
            if(slot != REFORGE_ITEM_SLOT) {
                event.setCancelled(true);
            }
        });



    }

}
