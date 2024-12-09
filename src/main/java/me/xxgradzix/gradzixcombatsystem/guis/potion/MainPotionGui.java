package me.xxgradzix.gradzixcombatsystem.guis.potion;

import dev.triumphteam.gui.components.GuiType;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcombatsystem.items.CustomItemManager;
import me.xxgradzix.gradzixcombatsystem.items.ItemManager;
import me.xxgradzix.gradzixcombatsystem.items.combatPotion.CustomBattlePotion;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

public class MainPotionGui {

    private static final int CUSTOM_POTION_TIER_SLOT = 16;


    private static final CustomItemManager customPotionToBuy = CustomItemManager.FALLEN_HERO_POTION;

    private final Gui mainWitchGui;
    private final Gui customPotionGui;
    private final Player player;

    private int currentTier = 1;


    public MainPotionGui(Player player) {

        this.player = player;

        mainWitchGui = Gui.gui(GuiType.CHEST)
                .title(Component.text(ColorFixer.addColors("&f七七七七七七七七≕")))
                .rows(4)
                .disableAllInteractions()
                .create();

        customPotionGui = Gui.gui(GuiType.CHEST)
                .title(Component.text(ColorFixer.addColors("&f七七七七七七七七≔")))
                .rows(5)
                .disableAllInteractions()
                .create();

        setupMainGui();
        updateCustomPotionItems();

        mainWitchGui.open(player);

    }

    private void setupMainGui() {

        GuiItem refillButton = new GuiItem(ItemManager.refillButton);

        refillButton.setAction((a) -> {

            for(ItemStack itemStack : player.getInventory().getContents()) {

                if(!(CustomItemManager.getCustomItem(itemStack) instanceof CustomBattlePotion potion)) continue;

                potion.refillPotion(itemStack);

            }

        });


        mainWitchGui.getFiller().fillBetweenPoints(1,1,3,3, refillButton);

        GuiItem customPotionButton = new GuiItem(ItemManager.customPotionButton);
        customPotionButton.setAction((event) -> {
            customPotionGui.open(player);
        });
        mainWitchGui.getFiller().fillBetweenPoints(1,4,3,6, customPotionButton);


        GuiItem throwablePotionButton = new GuiItem(ItemManager.thrownBottlesButton);
        throwablePotionButton.setAction((event -> {
            new ThrowPotionUpgradeGuiManager(player);
        }));
        mainWitchGui.getFiller().fillBetweenPoints(1,7,3,9, throwablePotionButton);

        mainWitchGui.update();
    }

    private void updateCustomPotionItems() {

        setTierGuiItem();

        GuiItem backItem = new GuiItem(ItemManager.returnButton);
        backItem.setAction((a) -> {
            mainWitchGui.open(player);
        });

        customPotionGui.setItem(2, 2, backItem);

        int price = 10;

        ItemStack previousTierItem = null;

        if (currentTier > 1) previousTierItem = customPotionToBuy.getCustomItem().getDefaultItemStack(currentTier - 1);

        GuiItem createItem = new GuiItem(ItemManager.universalCreateItemButton(player, Collections.emptyList(), previousTierItem, price));
        customPotionGui.getFiller().fillBetweenPoints(4, 4, 4, 6, createItem);


        GuiItem itemToCraft = new GuiItem(customPotionToBuy.getCustomItem().getDefaultItemStack(currentTier));

        customPotionGui.setItem(2, 5, itemToCraft);

        customPotionGui.update();

    }

    private void setTierGuiItem() {

        int maxTier = 5;


        GuiItem tierButton = new GuiItem(ItemManager.potionTierButton(currentTier, maxTier));

        tierButton.setAction(event -> {
            event.setCancelled(true);

            if(event.isLeftClick()) {
                if(currentTier >= 3) return;
                currentTier++;
            } else {
                if(currentTier  <= 1) return;
                currentTier--;
            }
            updateCustomPotionItems();
//            updateThrowablePotionItems();
        });

        customPotionGui.updateItem(CUSTOM_POTION_TIER_SLOT, tierButton);
//        throwablePotionGui.updateItem(THROWABLE_POTION_TIER_SLOT, tierButton);
    }

}
