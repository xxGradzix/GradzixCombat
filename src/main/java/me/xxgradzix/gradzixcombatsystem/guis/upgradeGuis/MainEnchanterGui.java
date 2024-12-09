package me.xxgradzix.gradzixcombatsystem.guis.upgradeGuis;

import dev.triumphteam.gui.components.GuiType;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.items.*;
import me.xxgradzix.gradzixcombatsystem.items.enchantBooks.CustomEnchantBook;
import me.xxgradzix.gradzixcombatsystem.items.weapons.EnchantableWeapon;
import me.xxgradzix.gradzixcombatsystem.managers.EnchantManager.EnchantManager;
import me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainEnchanterGui {

    private static final int BOOK_ITEM_SLOT = 20;
    private static final int TIER_ITEM_SLOT = 24;
    private static final int WEAPON_SLOT = 22;

    private final Gui enchantGui;
    private final Player player;

    private int currentTier = 1;
    private EnchantManager.Enchant currentEnchant = null;

    private static final GuiItem invisibleFiller = new GuiItem(ItemManager.invisibleFiller);
    static {invisibleFiller.setAction(event -> event.setCancelled(true));}


    public MainEnchanterGui(Player player) {

        this.player = player;

        enchantGui = Gui.gui(GuiType.CHEST)
                .title(Component.text(ColorFixer.addColors("&f七七七七七七七七≐")))
                .rows(6)
                .create();

        enchantGui.getFiller().fill(invisibleFiller);
        enchantGui.removeItem(WEAPON_SLOT);

        enchantGui.setCloseGuiAction(event -> {

            ItemStack item = enchantGui.getInventory().getItem(WEAPON_SLOT);

            if(item != null) {
                if(player.getInventory().firstEmpty() == -1) {
                    player.getLocation().getWorld().dropItemNaturally(player.getLocation(), item);
                } else {
                    player.getInventory().addItem(item);
                }
            }

        });

        enchantGui.setDefaultClickAction(event -> updateItems());

        updateItems();

        enchantGui.open(player);

    }

    private void updateItems() {
        Bukkit.getScheduler().runTaskLater(GradzixCombatSystem.plugin, () -> {

            ItemStack itemOnSlot = enchantGui.getInventory().getItem(WEAPON_SLOT);

            if(itemOnSlot == null) {
                currentTier = 1;
                currentEnchant = null;
            }

            CustomItem customItem = CustomItemManager.getCustomItem(itemOnSlot);

            Set<EnchantManager.Enchant> applicableEnchants = Set.of();

            if(!(customItem instanceof EnchantableWeapon)) {
                currentEnchant = null;
                currentTier = 1;

                if(itemOnSlot != null) {

                    enchantGui.removeItem(WEAPON_SLOT);

                    if(player.getInventory().firstEmpty() == -1) {
                        player.getLocation().getWorld().dropItemNaturally(player.getLocation(), itemOnSlot);
                    } else {
                        player.getInventory().addItem(itemOnSlot);
                    }
                }


            } else {
                applicableEnchants = ((EnchantableWeapon) customItem).getApplicableEnchants(currentTier);
            }

            if(currentEnchant != null) {
                if(currentTier > currentEnchant.maxLevel) currentTier = currentEnchant.maxLevel;
                if(!applicableEnchants.contains(currentEnchant)) currentEnchant = null;
            }

            setEnchantListGuiItems(applicableEnchants);
            setBookVisualizerGuiItem();
            setTierGuiItem();
            List<ItemStack> neededItems = new ArrayList<>();
            CustomItem enchantBookCustomItemByEnchant = CustomItemManager.getEnchantBookCustomItemByEnchant(currentEnchant);
            if(enchantBookCustomItemByEnchant != null) {
                ItemStack defaultItemStack = enchantBookCustomItemByEnchant.getDefaultItemStack(currentTier);
                neededItems.add(defaultItemStack);
            }
            setEnchantGuiItem(neededItems);


        }, 0L);
    }


    private void setEnchantListGuiItems(Set<EnchantManager.Enchant> enchants) {

        for (int i = 1; i <= 7; i++) {
            enchantGui.updateItem(i, invisibleFiller);
        }

        int startingSlot = (enchants.size() >= 6 ? 1 : 2);

        int i = 0;
        for (EnchantManager.Enchant enchant : enchants) {

            CustomItem customItem = CustomItemManager.getEnchantBookCustomItemByEnchant(enchant);

            if(!(customItem instanceof CustomEnchantBook book)) continue;

            GuiItem item = new GuiItem(book.getDefaultItemStack(currentTier));

            item.setAction(event -> {
                event.setCancelled(true);
                currentEnchant = enchant;

                ((Player) event.getWhoClicked()).playSound(event.getWhoClicked(), Sound.ITEM_BOOK_PAGE_TURN, 1f, 0.2f);

                updateItems();
            });

            enchantGui.updateItem(startingSlot + i, item);
            i++;
        }
    }

    private void setBookVisualizerGuiItem() {

        int maxTier = (currentEnchant == null ? 3 : currentEnchant.maxLevel);

        if(currentTier > maxTier) {
            currentTier = maxTier;
            setTierGuiItem();
        }

        ItemStack itemStack = ItemManager.getBookVisualizer(currentEnchant,  currentTier);
        GuiItem bookVisualizer= new GuiItem(itemStack);

        bookVisualizer.setAction(event -> event.setCancelled(true));

        enchantGui.updateItem(BOOK_ITEM_SLOT, bookVisualizer);

    }


    private void setTierGuiItem() {

        int maxTier = (currentEnchant == null ? 3 : currentEnchant.maxLevel);


        GuiItem tierButton = new GuiItem(ItemManager.enchantTierButton(currentTier, maxTier));

        tierButton.setAction(event -> {
            event.setCancelled(true);

            if(event.isLeftClick()) {
                if(currentTier >= 3) return;
                currentTier++;
            } else {
                if(currentTier  <= 1) return;
                currentTier--;
            }
            updateItems();
        });

        enchantGui.updateItem(TIER_ITEM_SLOT, tierButton);
    }

    private void setEnchantGuiItem(List<ItemStack> neededItems) {


        ItemStack itemToGet = enchantGui.getInventory().getItem(WEAPON_SLOT);

        if(itemToGet != null) {

            itemToGet = itemToGet.clone();
            EnchantManager.setEnchant(itemToGet, currentEnchant, currentTier);


        }

        GuiItem enchant = new GuiItem(ItemManager.enchantWeaponButton(player, neededItems, itemToGet, 0));

        ItemStack itemStack = enchantGui.getInventory().getItem(WEAPON_SLOT);

        enchant.setAction(event -> {
            event.setCancelled(true);

            if(currentEnchant == null) return;

            if(CustomItemManager.getCustomItem(itemStack) instanceof EnchantableWeapon) {

                boolean haveAllItems = true;
                if(!player.getGameMode().equals(GameMode.CREATIVE)) {
                    for (ItemStack item : neededItems) {
                        int currAmount = CustomItemNbtItemUtil.calcItemAmount(player, item);
                        if(currAmount < item.getAmount()) {
                            player.sendMessage(ColorFixer.addColors(MessageManager.getHaveRequiredItemLoreString(player, item)));
                            haveAllItems = false;
                        }
                    }
                }
                if (!haveAllItems) return;


                boolean enchantSuccess = EnchantManager.setEnchant(itemStack, currentEnchant, currentTier);

                if(enchantSuccess) {
                    ((Player) event.getWhoClicked()).playSound(event.getWhoClicked(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 0.3f, 1.0f);

                } else {
                    ((Player) event.getWhoClicked()).playSound(event.getWhoClicked(), Sound.BLOCK_NOTE_BLOCK_BIT, 0.1f, 0.2f);
                }

            }
            updateItems();
        });

        for (int i = 39; i<=41; i++) enchantGui.updateItem(i, enchant);



    }

}
