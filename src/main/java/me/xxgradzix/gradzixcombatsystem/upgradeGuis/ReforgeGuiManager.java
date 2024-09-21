package me.xxgradzix.gradzixcombatsystem.upgradeGuis;

import dev.triumphteam.gui.components.GuiType;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.items.ItemManager;
import me.xxgradzix.gradzixcombatsystem.managers.modifiersManager.ModifiersManager;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import me.xxgradzix.gradzixcombatsystem.weapons.CustomWeapon;
import me.xxgradzix.gradzixcombatsystem.weapons.ModifiableWeapon;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Random;

public class ReforgeGuiManager {

    private static final int REFORGE_ITEM_SLOT = 11;

    public static void openReforgeGui(Player player, double priceModifier) {

        Gui gui = new Gui(3, ColorFixer.addColors("&f七七七七七七七七≅"));

        gui.setDragAction(event -> event.setCancelled(true));
        gui.disableItemSwap();

        gui.disableOtherActions();

        gui.setDefaultClickAction(event -> {
            if(event.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY) && !gui.getInventory().equals(event.getClickedInventory())) {

                if(gui.getInventory().getItem(REFORGE_ITEM_SLOT) != null) {
                    event.setCancelled(true);
                    return;
                }

                ItemStack currentItem = event.getCurrentItem();
                if(currentItem != null) {
                    gui.getInventory().setItem(REFORGE_ITEM_SLOT, currentItem);
                    event.setCurrentItem(null);
                    updateGuiItem(gui, priceModifier);
                }
            }
            if(!gui.getInventory().equals(event.getClickedInventory())) {
                return;
            }
            int slot = event.getSlot();
            if(slot == REFORGE_ITEM_SLOT) {
                event.setCancelled(false);
                updateGuiItem(gui, priceModifier);
            } else {
                event.setCancelled(true);
            }
        });

        updateGuiItem(gui, priceModifier);

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
        gui.open(player);
    }

    private static void updateGuiItem(Gui gui, double priceModifier) {

        Bukkit.getScheduler().runTaskLater(GradzixCombatSystem.plugin, () -> {
            ItemStack reforgeItem = gui.getInventory().getItem(REFORGE_ITEM_SLOT);
            int reforgePrice = ModifiersManager.getReforgePrice(reforgeItem);
            int price = (int) (reforgePrice * priceModifier);


            String quality = ColorFixer.addColors("&7ᴢᴡʏᴋᴌʏ");
            ArrayList<String> lore = new ArrayList<>();

            if(reforgeItem != null) {
                quality = ModifiersManager.getModifierName(reforgeItem.getItemMeta());
                lore = ModifiersManager.getModifiersLore(reforgeItem.getItemMeta());
            }

            GuiItem guiItem = new GuiItem(ItemManager.createReforgeItem(price, quality, lore));

            guiItem.setAction(event -> {
                event.setCancelled(true);
                CustomWeapon weaponType = ItemManager.getWeaponType(reforgeItem);
                if(weaponType instanceof ModifiableWeapon) {
                    ModifiersManager.applyRandomModifier(reforgeItem);
                    ((Player) event.getWhoClicked()).playSound(event.getWhoClicked(), Sound.BLOCK_ANVIL_USE, 1.0f, 1.0f);
                }
                updateGuiItem(gui, priceModifier);
            });
            gui.updateItem(15, guiItem);

        }, 0L);
    }


}
