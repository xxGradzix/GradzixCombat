package me.xxgradzix.gradzixcombatsystem.upgradeGuis;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcombatsystem.ArmorTierManager;
import me.xxgradzix.gradzixcombatsystem.armors.CustomArmor;
import me.xxgradzix.gradzixcombatsystem.armors.instances.UpgradableArmor;
import me.xxgradzix.gradzixcombatsystem.items.ItemIsNotCustomItem;
import me.xxgradzix.gradzixcombatsystem.items.ItemManager;
import me.xxgradzix.gradzixcombatsystem.items.NbtItemUtil;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UpgradeGuiManager {

    private static final int MAX_TIER = 5;

    public static void openArmorsGui(Player player, ArmorTierManager.ArmorWeight armorWeight, double priceModifier, int initialTier) {

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
                openArmorsGui(player, armorWeight, priceModifier, initialTier - 1);
                return;
            }
        });
        rightArrowGuiItem.setAction(event -> {
            if(isRightActive) {
                openArmorsGui(player, armorWeight, priceModifier, initialTier + 1);
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

//            ArrayList<ItemStack> armorPieces = ItemManager.getArmorPiecesOfWeightAndTier(armorWeight, tier);
            ArrayList<ItemStack> armorPieces = new ArrayList<>();

            for (ArmorTierManager.ArmorType armorType : ArmorTierManager.ArmorType.values()) {
                armorPieces.add(ItemManager.getArmorTypeByWeight(armorWeight).getItemStack(tier, armorType));
            }

            int armorSlot = initialSlot;
            for (ItemStack armorPiece : armorPieces) {

                GuiItem armorPieceGuiItem = new GuiItem(armorPiece);

                armorPieceGuiItem.setAction(event -> {
                    openCraftGui(player, armorPiece);
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

    private static void openCraftGui(Player player, ItemStack item) {

        CustomArmor customArmorType = ItemManager.getCustomArmor(item);

        if(!(customArmorType instanceof UpgradableArmor upgradableArmor)) {
            return;
        }

        int wantedItemTier = customArmorType.getArmorTier(item);

        List<ItemStack> itemsNeeded = upgradableArmor.getRequiredItems(wantedItemTier);

        ArmorTierManager.ArmorType armorType = null;
        if(item.getType().name().toUpperCase().contains("HELMET")) {
            armorType = ArmorTierManager.ArmorType.HELMET;
        } else if(item.getType().name().toUpperCase().contains("CHESTPLATE")) {
            armorType = ArmorTierManager.ArmorType.CHESTPLATE;
        } else if(item.getType().name().toUpperCase().contains("LEGGINGS") || item.getType().name().toUpperCase().contains("PANTS")) {
            armorType = ArmorTierManager.ArmorType.LEGGINGS;
        } else if(item.getType().name().toUpperCase().contains("BOOTS")) {
            armorType = ArmorTierManager.ArmorType.BOOTS;
        }

        Optional<ItemStack> optionalNeededItem = upgradableArmor.isLowerTierItemRequired(wantedItemTier) ? Optional.of(customArmorType.getItemStack(wantedItemTier - 1, armorType)) : Optional.empty();

        int requiredMoney = upgradableArmor.getRequiredMoney(wantedItemTier);

        int initialSlot;
        switch (itemsNeeded.size()) {
            case 1 -> initialSlot = 4;
            case 2, 3 -> initialSlot = 3;
            default -> initialSlot = 2;
        }

        Gui gui = Gui.gui()
                .disableAllInteractions()
                .rows(4)
                .title(Component.text(ColorFixer.addColors("&f七七七七七七七七≉")))
                .create();

        for (ItemStack itemNeeded : itemsNeeded) {
            GuiItem guiItem = new GuiItem(itemNeeded);
            gui.setItem(initialSlot, guiItem);
            initialSlot += 1;
        }
        if(requiredMoney > 0) {
            ItemStack moneyItem = new ItemStack(Material.SUNFLOWER, requiredMoney);
            GuiItem moneyGuiItem = new GuiItem(moneyItem);
            gui.setItem(initialSlot, moneyGuiItem);
        }

        if(optionalNeededItem.isPresent()) {
            GuiItem neededItemGuiItem = new GuiItem(optionalNeededItem.get());
            gui.setItem(13, neededItemGuiItem);
        }

        GuiItem upgradeButton = new GuiItem(ItemManager.upgradeWeaponButton());
        GuiItem itemToUpgrade = new GuiItem(ItemManager.getItemToUpgrade(item));

        itemToUpgrade.setAction(event -> {
            checkNeededItems(player, item, itemsNeeded, optionalNeededItem);
        });
        upgradeButton.setAction(event -> {
            checkNeededItems(player, item, itemsNeeded, optionalNeededItem);
        });


        gui.getFiller().fillBetweenPoints(4, 4, 4, 5, upgradeButton);
        // Item to upgrade
        gui.setItem(4, 6, itemToUpgrade);

        gui.open(player);

    }

    private static void checkNeededItems(Player player, ItemStack item, List<ItemStack> itemsNeeded, Optional<ItemStack> optionalNeededItem) {
        boolean haveAllItems = true;

        for (ItemStack itemNeeded : itemsNeeded) {
            if (NbtItemUtil.calcItemAmount(player, itemNeeded) < itemNeeded.getAmount()) {
                haveAllItems = false;
                break;
            }
        }

        if(optionalNeededItem.isPresent()) {
            haveAllItems = NbtItemUtil.calcItemAmount(player, optionalNeededItem.get()) >= optionalNeededItem.get().getAmount();
        }

        if (!haveAllItems) {
            player.sendMessage(ColorFixer.addColors("&cNie posiadasz wszystkich wymaganych przedmiotów"));
            return;
        }

        // TODO money check

        for (ItemStack itemNeeded : itemsNeeded) {
            NbtItemUtil.removeItemsWithCustomItemNBT(player, itemNeeded, itemNeeded.getAmount());
        }
        player.getInventory().addItem(item);
    }


}
