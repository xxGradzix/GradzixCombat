package me.xxgradzix.gradzixcombatsystem.upgradeGuis;

import dev.triumphteam.gui.components.GuiType;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.items.CustomItem;
import me.xxgradzix.gradzixcombatsystem.items.CustomItemManager;
import me.xxgradzix.gradzixcombatsystem.items.ItemManager;
import me.xxgradzix.gradzixcombatsystem.items.armors.CustomArmor;
import me.xxgradzix.gradzixcombatsystem.items.weapons.ModifiableWeapon;
import me.xxgradzix.gradzixcombatsystem.managers.modifiersManager.ModifiersManager;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class MainBlacksmithGui {

    private final double priceModifier = 1.0;

    private final Gui weaponGui;
    private final Gui armorGui;
    private final Gui otherGui;
    private final Gui reforgeGui;

    private final Player player;

    private static final ArrayList<CustomItem> weapons = new ArrayList();

    static {
        weapons.add(CustomItemManager.SWORD.getCustomItem());
        weapons.add(CustomItemManager.AXE.getCustomItem());
        weapons.add(CustomItemManager.JAVELIN.getCustomItem());
        weapons.add(CustomItemManager.BOW.getCustomItem());
        weapons.add(CustomItemManager.CROSSBOW.getCustomItem());
    }

    private static final ArrayList<CustomItem> armors = new ArrayList();

    static {
        armors.add(CustomItemManager.LIGHT_ARMOR.getCustomItem());
        armors.add(CustomItemManager.MEDIUM_ARMOR.getCustomItem());
        armors.add(CustomItemManager.HEAVY_ARMOR.getCustomItem());
    }

    private static final ArrayList<CustomItem> other = new ArrayList();

    static {
        other.add(CustomItemManager.SHIELD.getCustomItem());
    }



    private GuiItem weaponsButton= new GuiItem(ItemManager.weaponButtonItem);
    private GuiItem armorsButton= new GuiItem(ItemManager.armorButtonItem);
    private GuiItem othersButton= new GuiItem(ItemManager.otherButtonItem);
    private GuiItem reforgeButton= new GuiItem(ItemManager.reforgeButtonItem);

    private static final GuiItem invisibleFiller = new GuiItem(ItemManager.invisibleFiller);

    static {
        invisibleFiller.setAction(event -> event.setCancelled(true));
    }


    public MainBlacksmithGui(Player player, int initialPage) {
        this.player = player;

        weaponGui = Gui.gui(GuiType.CHEST)
                .title(Component.text(ColorFixer.addColors("&f七七七七七七七七≌")))
                .rows(6)
                .disableAllInteractions()
                .create();

        armorGui = Gui.gui(GuiType.CHEST)
                .title(Component.text(ColorFixer.addColors("&f七七七七七七七七≍")))
                .rows(6)
                .disableAllInteractions()
                .create();

        otherGui = Gui.gui(GuiType.CHEST)
                .title(Component.text(ColorFixer.addColors("&f七七七七七七七七≎")))
                .rows(6)
                .disableAllInteractions()
                .create();

        reforgeGui = Gui.gui(GuiType.CHEST)
                .title(Component.text(ColorFixer.addColors("&f七七七七七七七七≏")))
                .rows(6)
                .create();

        weaponsButton.setAction((event) -> {
            weaponGui.open(player);
            event.setCancelled(true);
        });

        armorsButton.setAction((event) -> {
            armorGui.open(player);
            event.setCancelled(true);
        });

        othersButton.setAction((event -> {
            otherGui.open(player);
            event.setCancelled(true);
        }));

        reforgeButton.setAction((event -> {
            reforgeGui.open(player);
            event.setCancelled(true);
        }));

        setArmorButtons();
        setWeaponButtons();
        setOtherButtons();
        setReforgeButtons();

        switch (initialPage) {
            case 2:
                armorGui.open(player);
                break;
            case 3:
                otherGui.open(player);
                break;
            case 4:
                reforgeGui.open(player);
                break;
            default:
                weaponGui.open(player);
                break;
        }

    }

    private void setArmorButtons() {


        armorGui.getFiller().fill(invisibleFiller);

        armorGui.setItem(10, weaponsButton);
        armorGui.setItem(12, armorsButton);
        armorGui.setItem(14, othersButton);
        armorGui.setItem(16, reforgeButton);

        GuiItem lightArmorButton = new GuiItem(ItemManager.lightArmorButtonItem);
        GuiItem mediumArmorButton = new GuiItem(ItemManager.mediumArmorButtonItem);
        GuiItem heavyArmorButton = new GuiItem(ItemManager.heavyArmorButtonItem);

        lightArmorButton.setAction((e) -> {
            new ArmorUpgradeGuiManager(player, (CustomArmor) CustomItemManager.LIGHT_ARMOR.getCustomItem());
        });
        mediumArmorButton.setAction((e) -> {
            new ArmorUpgradeGuiManager(player, (CustomArmor) CustomItemManager.MEDIUM_ARMOR.getCustomItem());
        });
        heavyArmorButton.setAction((e) -> {
            new ArmorUpgradeGuiManager(player, (CustomArmor) CustomItemManager.HEAVY_ARMOR.getCustomItem());
        });

        armorGui.getFiller().fillBetweenPoints(4, 1, 5, 3, lightArmorButton);
        armorGui.getFiller().fillBetweenPoints(4, 4, 5, 6, mediumArmorButton);
        armorGui.getFiller().fillBetweenPoints(4, 7, 5, 9, heavyArmorButton);

    }

    private void setWeaponButtons() {

        weaponGui.getFiller().fill(invisibleFiller);

        weaponGui.setItem(10, weaponsButton);
        weaponGui.setItem(12, armorsButton);
        weaponGui.setItem(14, othersButton);
        weaponGui.setItem(16, reforgeButton);


        int currentIteration = 0;

        for (CustomItem customItem : weapons) {

            GuiItem item = new GuiItem(customItem.getDefaultItemStack(0));

//            int middleIndex = currentIteration;
//            int startIndex = Math.max(0, middleIndex - 2);
//            int endIndex = Math.min(weapons.size(), middleIndex + 3);


            item.setAction((event) -> {

                ArrayList<CustomItem> customItems = new ArrayList<>();

                int startIndex = weapons.indexOf(customItem);

                for(int i = 0; i < Math.min(weapons.size(), 5); i++) {
                    int index = startIndex + i;
                    if(index >= weapons.size()) {
                        index -= weapons.size();
                    }
                    customItems.add(weapons.get(index));
                }

//                customItems.addAll(weapons.subList(startIndex, endIndex));
//
//                while (customItems.size() < 5) {
//                    for (int i = 0; i < weapons.size() && customItems.size() < 5; i++) {
//                        customItems.add(weapons.get(i));
//                    }
//                }

                new WeaponUpgradeGuiManager(player, customItems);
            });

            weaponGui.setItem(29 + currentIteration + (currentIteration > 4 ? 6 : 0), item);

            currentIteration++;
        }
    }

    private void setOtherButtons() {

        otherGui.getFiller().fill(invisibleFiller);

        otherGui.setItem(10, weaponsButton);
        otherGui.setItem(12, armorsButton);
        otherGui.setItem(14, othersButton);
        otherGui.setItem(16, reforgeButton);

        int currentIteration = 0;

        for (CustomItem customItem : other) {

            GuiItem item = new GuiItem(customItem.getDefaultItemStack(0));

            item.setAction((event) -> {

                ArrayList<CustomItem> customItems = new ArrayList<>();

                int startIndex = other.indexOf(customItem);

                for(int i = 0; i < Math.min(other.size(), 5); i++) {
                    int index = startIndex + i;
                    if(index >= other.size()) {
                        index -= other.size();
                    }
                    customItems.add(other.get(index));
                }

                new WeaponUpgradeGuiManager(player, customItems);
            });

            otherGui.setItem(29 + currentIteration + (currentIteration > 4 ? 6 : 0), item);

            currentIteration++;
        }
    }

    private void setReforgeButtons() {

        reforgeGui.getFiller().fill(invisibleFiller);

        reforgeGui.setItem(10, weaponsButton);
        reforgeGui.setItem(12, armorsButton);
        reforgeGui.setItem(14, othersButton);
        reforgeGui.setItem(16, reforgeButton);

        reforgeGui.removeItem(REFORGE_ITEM_SLOT);

        reforgeGui.setDragAction(event -> event.setCancelled(true));
        reforgeGui.disableItemSwap();

        reforgeGui.disableOtherActions();

        reforgeGui.setDefaultClickAction(event -> {
//            if(event.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY) && !reforgeGui.getInventory().equals(event.getClickedInventory())) {
//
//                if(reforgeGui.getInventory().getItem(REFORGE_ITEM_SLOT) != null) {
//                    event.setCancelled(true);
//                    return;
//                }
//
//                ItemStack currentItem = event.getCurrentItem();
//                if(currentItem != null) {
//                    reforgeGui.getInventory().setItem(REFORGE_ITEM_SLOT, currentItem);
//                    event.setCurrentItem(null);
                    updateReforgeGuiItem(reforgeGui, priceModifier);
////                }
//            }
//            if(!reforgeGui.getInventory().equals(event.getClickedInventory())) {
//                return;
//            }
//            int slot = event.getSlot();
//            if(slot == REFORGE_ITEM_SLOT) {
//                event.setCancelled(false);
//                updateReforgeGuiItem(reforgeGui, priceModifier);
//            } else {
//                event.setCancelled(true);
//            }
        });

        updateReforgeGuiItem(reforgeGui, priceModifier);

        reforgeGui.setCloseGuiAction(event -> {
            ItemStack item = reforgeGui.getInventory().getItem(REFORGE_ITEM_SLOT);

            if(item != null) {
                if(player.getInventory().firstEmpty() == -1) {
                    player.getLocation().getWorld().dropItemNaturally(player.getLocation(), item);
                } else {
                    player.getInventory().addItem(item);
                }
            }
        });
        reforgeGui.open(player);

    }

    private static final int REFORGE_ITEM_SLOT = 29;
    private static final int REFORGE_BUTTON_SLOT = 33;

    private void updateReforgeGuiItem(Gui gui, double priceModifier) {

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


//                boolean success = EconomyManager.withdrawMoney(player, price);
//
//                if (!success) {
//                    player.sendMessage(ColorFixer.addColors("&cNie posiadasz wystarczająco pieniędzy"));
//                    return;
//                }
                CustomItem weaponType = CustomItemManager.getCustomItem(reforgeItem);

                if(weaponType instanceof ModifiableWeapon) {
                    ModifiersManager.applyRandomModifier(reforgeItem);
                    ((Player) event.getWhoClicked()).playSound(event.getWhoClicked(), Sound.BLOCK_ANVIL_USE, 1.0f, 1.0f);
                }
                updateReforgeGuiItem(gui, priceModifier);
            });
            gui.updateItem(REFORGE_BUTTON_SLOT, guiItem);

        }, 0L);
    }

}
