package me.xxgradzix.gradzixcombatsystem.guis.upgradeGuis;

import dev.triumphteam.gui.components.GuiType;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcombatsystem.items.*;
import me.xxgradzix.gradzixcombatsystem.items.weapons.*;
import me.xxgradzix.gradzixcombatsystem.managers.EconomyManager;
import me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import net.kyori.adventure.text.Component;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class WeaponUpgradeGuiManager {

    private static final int MAX_TIER = 5;

    /** START OF WEAPON GUI **/

    private final ArrayList<ItemStack> currentItemsNeeded = new ArrayList<>();
    private final AtomicReference<ItemStack> currentItemToGet = new AtomicReference<>();
    private final AtomicReference<ItemStack> previousItem = new AtomicReference<>();

    private final AtomicInteger currentPrice = new AtomicInteger();

    private int currentTier = 1;
    private final Gui gui;

    private final Player player;

    private final ArrayList<CustomItem> weapons;

    private void clearCraftSection() {
        currentPrice.set(0);
        currentItemToGet.set(null);
        currentItemsNeeded.clear();
        previousItem.set(null);
    }

    public WeaponUpgradeGuiManager(Player player, ArrayList<CustomItem> weapons) {
        this.player = player;
        this.weapons = weapons;
        this.gui = Gui.gui(GuiType.CHEST)
                .title(Component.text("&f七七七七七七七七≋".replace("&", "§")))
                .disableAllInteractions()
                .rows(6)
                .create();

        gui.getFiller().fill(new GuiItem(ItemManager.invisibleFiller));

        setScrollItems();
        setCraftButton();
        refreshTierItems();
        refreshNeededItems();
        refreshCurrentItem(currentItemToGet.get());

        gui.open(player);

        gui.setDefaultClickAction(event -> {
            if(!gui.getInventory().equals(event.getClickedInventory())) {
                setNewItemToCraft(event.getCurrentItem());
            }
        });
    }

    private void setCraftButton() {
        GuiItem upgradeButton = new GuiItem(ItemManager.universalCreateItemButton(player, currentItemsNeeded, previousItem.get(), currentPrice.get()));

        upgradeButton.setAction(event -> {
            if(currentItemToGet.get() == null) return;
            buyItemIfPossible(player, currentItemToGet.get(), currentItemsNeeded, Optional.ofNullable(previousItem.get()), currentPrice.get());
        });
        gui.getFiller().fillBetweenPoints(6, 4, 6, 6, upgradeButton);
        gui.update();
    }

    private void setScrollItems() {

        int i = 0;
        for (CustomItem customItem : weapons) {
            if(i > 6) break;


            if (!(customItem instanceof Upgradable)) continue;

            ItemStack weapon = customItem.getDefaultItemStack(0);

            GuiItem item = new GuiItem(weapon);

            item.setAction(event -> {
                if(event.isLeftClick()) {

                    setNewItemToCraft(customItem);
                }
            });
            gui.setItem(i * 9, item);

            i += 1;
        }
        GuiItem moreItems = new GuiItem(ItemManager.moreItemsItemButton);
        moreItems.setAction(event -> {
            event.setCancelled(true);
            new MainBlacksmithGui(player, 1);
        });
        gui.setItem(45, moreItems);
    }

    private void setNewItemToCraft(CustomItem customItem) {

        clearCraftSection();

        if (customItem == null) {
            refreshTierItems();
            return;
        }

        if(!(customItem instanceof Upgradable)) return;

        if(currentTier == 1) {
            ItemStack defaultItemStack = customItem.getDefaultItemStack(1);
            setNewItemToCraftByTargetItem(defaultItemStack);
        } else {
            ItemStack defaultItemStack = customItem.getDefaultItemStack(currentTier - 1);
            setNewItemToCraft(defaultItemStack);
        }

    }

    private void setNewItemToCraftByTargetItem(ItemStack itemToCraft) {

        clearCraftSection();

        if (itemToCraft == null) {
            refreshTierItems();
            return;
        }

        CustomItem customItem = CustomItemManager.getCustomItem(itemToCraft);

        if(!(customItem instanceof Upgradable upgradable)) return;

        if(customItem instanceof Tierable) {

            int targetTier = Tierable.getTier(itemToCraft);

            if(targetTier == 0) return;
            if(targetTier >= MAX_TIER) return;
        }

        currentItemToGet.set(itemToCraft);

        currentItemsNeeded.addAll(upgradable.getRequiredItems(currentTier));
        currentPrice.set(upgradable.getRequiredMoney(currentTier));

        if(upgradable.isLowerTierItemRequired(currentTier)) previousItem.set(itemToCraft);

        refreshPreviousItem();
        refreshNeededItems();
        refreshCurrentItem(itemToCraft);
        setWeaponStats();

        setCraftButton();
    }

    private void setNewItemToCraft(ItemStack itemToUpgrade) {

        CustomItem customItem = CustomItemManager.getCustomItem(itemToUpgrade);

        if(!(customItem instanceof Upgradable upgradable)) return;

        clearCraftSection();

        int previousTier = Tierable.getTier(itemToUpgrade);

        if(customItem instanceof Tierable) {

            if(previousTier == 0) return;
            if(previousTier >= MAX_TIER) return;
        }

        currentTier = previousTier + 1;

        ItemStack itemToGet = CustomItemManager.getUpgradedVersionOfItem(itemToUpgrade);
        currentItemToGet.set(itemToGet);

        currentItemsNeeded.addAll(upgradable.getRequiredItems(currentTier));

        currentPrice.set(upgradable.getRequiredMoney(currentTier));

        if(upgradable.isLowerTierItemRequired(currentTier)) {
            previousItem.set(itemToUpgrade);
        }

        refreshPreviousItem();
        refreshTierItems();
        refreshNeededItems();
        refreshCurrentItem(itemToGet);
        setWeaponStats();

        setCraftButton();
    }

    private void setNewItemToCraft() {

        if(currentItemToGet.get() == null) return;

        ItemStack defaultItemStack = currentItemToGet.get();

        clearCraftSection();

        if (defaultItemStack == null) return;

        CustomItem customWeapon = CustomItemManager.getCustomItem(defaultItemStack);

        if(!(customWeapon instanceof Upgradable upgradable)) return;

        ItemStack newItem = customWeapon.getDefaultItemStack(currentTier);

        currentItemToGet.set(newItem);
        currentItemsNeeded.addAll(upgradable.getRequiredItems(currentTier));
        currentPrice.set(upgradable.getRequiredMoney(currentTier));
        if(upgradable.isLowerTierItemRequired(currentTier)) previousItem.set(customWeapon.getDefaultItemStack(currentTier - 1));

        setWeaponStats();
        refreshPreviousItem();
        refreshNeededItems();
        refreshCurrentItem(newItem);

        setCraftButton();
    }

    private void refreshTierItems() {

        if(currentItemToGet.get() != null) {
            CustomItem customItem = CustomItemManager.getCustomItem(currentItemToGet.get());

            if(!(customItem instanceof Tierable)) {
                currentTier = 1;
            }
        }


        GuiItem leftArrow = new GuiItem(ItemManager.getSmallLeftArrowItem(currentTier != 1, currentTier - 1));
        GuiItem tierItem = new GuiItem(ItemManager.getTierItem(currentTier));
        GuiItem rightArrow = new GuiItem(ItemManager.getSmallRightArrowItem(currentTier != MAX_TIER, currentTier + 1));

        leftArrow.setAction(event -> {
            if (currentTier > 1) {
                currentTier -= 1;
                refreshTierItems();
                setNewItemToCraft();
            }
        });
        rightArrow.setAction(event -> {
            if (currentTier < MAX_TIER) {
                currentTier += 1;
                refreshTierItems();
                setNewItemToCraft();
            }
        });
        gui.updateItem(40, tierItem);
        gui.updateItem(39, leftArrow);
        gui.updateItem(41, rightArrow);
    }

    private void refreshPreviousItem() {
        gui.removeItem(4);
        if(previousItem.get() == null) return;
        gui.updateItem(4, new GuiItem(previousItem.get()));
    }

    private void refreshNeededItems() {
        int slot;
        gui.removeItem(11);
        gui.removeItem(15);
        gui.removeItem(29);
        gui.removeItem(33);
        for (int i = 0; i < currentItemsNeeded.size(); i++) {
            switch (i) {
                case 0 -> slot = 11;
                case 1 -> slot = 15;
                case 2 -> slot = 29;
                case 3 -> slot = 33;
                default -> {
                    return;
                }
            }
            gui.updateItem(slot, new GuiItem(currentItemsNeeded.get(i)));
        }
    }

    private void refreshCurrentItem(ItemStack itemStack) {
        gui.removeItem(22);
        if(itemStack == null) return;
        gui.updateItem(22, new GuiItem(itemStack));
    }


    private void setWeaponStats() {

        gui.removeItem(8);
        gui.removeItem(17);
        gui.removeItem(26);
        gui.removeItem(35);
        gui.removeItem(44);

        if(currentItemToGet.get() == null) return;

        CustomItem customWeapon = CustomItemManager.getCustomItem(currentItemToGet.get());

        double damage = 0;
        double attackSpeed = 0;

        if(customWeapon instanceof MelleWeapon melleWeapon) {
            damage = melleWeapon.getAttackDamage(currentTier);
            attackSpeed = melleWeapon.getAttackSpeed(currentTier);
        }
        if(customWeapon instanceof ShootableWeapon shootableWeapon) {
            damage = shootableWeapon.getWeaponDamage(currentTier);
        }

        if(damage != 0) {
            gui.updateItem(8, new GuiItem(ItemManager.getDamageItem(damage)));
        }
        if(attackSpeed != 0) {
            gui.updateItem(17, new GuiItem(ItemManager.getSpeedItem(attackSpeed)));
        }

    }

    private void buyItemIfPossible(Player player, ItemStack targetItem, List<ItemStack> itemsNeeded, Optional<ItemStack> optionalNeededItem, int requiredMoney) {

        boolean haveAllItems = true;
        boolean haveMoney = true;
        boolean havePreviousItem = true;

        if(!player.getGameMode().equals(GameMode.CREATIVE)) {

            for (ItemStack item : itemsNeeded) {
                int currAmount = CustomItemNbtItemUtil.calcItemAmount(player, item);
                if(currAmount < item.getAmount()) {
                    player.sendMessage(ColorFixer.addColors(MessageManager.getHaveRequiredItemLoreString(player, item)));
                    haveAllItems = false;
                }
            }

            if(optionalNeededItem.isPresent()) {
                havePreviousItem = CustomItemNbtItemUtil.calcItemAmount(player, optionalNeededItem.get()) >= optionalNeededItem.get().getAmount();
            }

//        haveMoney = EconomyManager.getBalance(player) >= requiredMoney;

        }

        if (!(haveAllItems) || !haveMoney || (!havePreviousItem)) {
            player.sendMessage(ColorFixer.addColors("&cNie posiadasz wszystkich wymaganych przedmiotów"));
            return;
        }

        EconomyManager.withdrawMoney(player, requiredMoney);

        for (ItemStack itemNeeded : itemsNeeded) {
            CustomItemNbtItemUtil.removeItemsWithCustomItemNBT(player, itemNeeded, itemNeeded.getAmount());
        }
        optionalNeededItem.ifPresent(itemStack -> CustomItemNbtItemUtil.removeItemsWithCustomItemNBT(player, itemStack, itemStack.getAmount()));
        player.getInventory().addItem(targetItem);
    }


}
