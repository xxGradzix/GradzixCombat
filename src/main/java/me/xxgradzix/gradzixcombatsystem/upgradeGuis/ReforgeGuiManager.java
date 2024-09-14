package me.xxgradzix.gradzixcombatsystem.upgradeGuis;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcombatsystem.items.ItemManager;
import me.xxgradzix.gradzixcombatsystem.managers.modifiersManager.ModifiersManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

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

        ItemStack reforgeItem = gui.getInventory().getItem(REFORGE_ITEM_SLOT);

        GuiItem guiItem = new GuiItem(ItemManager.createReforgeItem(ModifiersManager.getReforgePrice(reforgeItem)));

        gui.setCloseGuiAction(event -> {
            ItemStack item = gui.getInventory().getItem(REFORGE_ITEM_SLOT);

            if(item != null) {
                if(player.getInventory().firstEmpty() == -1) {
                    player.getLocation().getWorld().dropItemNaturally(player.getLocation(), item);
                } else {
                    player.getInventory().addItem(item);
                }
            }
        });


    }


}
