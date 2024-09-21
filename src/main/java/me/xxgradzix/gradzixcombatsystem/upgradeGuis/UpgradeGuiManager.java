package me.xxgradzix.gradzixcombatsystem.upgradeGuis;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcombatsystem.ArmorTierManager;
import me.xxgradzix.gradzixcombatsystem.items.ItemManager;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class UpgradeGuiManager {

    private static final int MAX_TIER = 5;

    public static void openUpgradeGui(Player player, ArmorTierManager.ArmorWeight armorWeight, double priceModifier, int initialTier) {

        if(initialTier < 1 || (initialTier + 3) > MAX_TIER) {
            return;
        }

        Gui gui = Gui.gui()
                .disableAllInteractions()
                .rows(6)
                .title(Component.text(ColorFixer.addColors("&f七七七七七七七七≆")))
                .create();

        boolean isLeftActive = initialTier > 1;
        boolean isRightActive = initialTier < MAX_TIER;

        ItemStack leftArrowItem = ItemManager.getLeftArrowItem(!isLeftActive);
        ItemStack rightArrowItem = ItemManager.getRightArrowItem(!isRightActive);

        GuiItem leftArrowGuiItem = new GuiItem(leftArrowItem);
        GuiItem rightArrowGuiItem = new GuiItem(rightArrowItem);

        leftArrowGuiItem.setAction(event -> {
            if(isLeftActive) {
                openUpgradeGui(player, armorWeight, priceModifier, initialTier - 1);
                return;
            }
        });
        rightArrowGuiItem.setAction(event -> {
            if(isRightActive) {
                openUpgradeGui(player, armorWeight, priceModifier, initialTier + 1);
                return;
            }
        });

        gui.setItem(48, leftArrowGuiItem);
        gui.setItem(50, rightArrowGuiItem);

        int initialSlot = 10;

        for (int tier = initialTier; tier < (initialTier + 4); tier++) {

            ItemStack tierItem = ItemManager.getTierItem(tier);

            GuiItem guiItem = new GuiItem(tierItem);

            gui.setItem((initialSlot - 9), guiItem);

            ArrayList<ItemStack> armorPieces = ItemManager.getArmorPiecesOfWeightAndTier(armorWeight, tier);

            int armorSlot = initialSlot;
            for (ItemStack armorPiece : armorPieces) {

                GuiItem armorPieceGuiItem = new GuiItem(armorPiece);

                armorPieceGuiItem.setAction(event -> {
                    //TODO change inv
                    event.setCancelled(true);
                    return;
                });

                gui.setItem(armorSlot, armorPieceGuiItem);
                armorSlot += 9;
            }
            initialSlot += 2;
        }


        gui.open(player);
    }



}
