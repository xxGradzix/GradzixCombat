package me.xxgradzix.gradzixcombatsystem.listeners.combatHeroPotion;

import dev.triumphteam.gui.components.GuiType;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import dev.triumphteam.gui.guis.StorageGui;
import me.xxgradzix.gradzixcombatsystem.items.CustomItem;
import me.xxgradzix.gradzixcombatsystem.items.CustomItemManager;
import me.xxgradzix.gradzixcombatsystem.items.ItemManager;
import me.xxgradzix.gradzixcombatsystem.items.combatPotion.CustomBattlePotion;
import me.xxgradzix.gradzixcombatsystem.items.combatPotion.CustomBattlePotionOrb;
import me.xxgradzix.gradzixcombatsystem.items.weapons.Tierable;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class LeftClickHeroPotion implements Listener {


    private static final int ITEM1_SLOT = 2;
    private static final int ITEM2_SLOT = 6;

    @EventHandler
    public void onLeftClick(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        if(!player.isSneaking()) return;

        if(!event.getAction().equals(Action.RIGHT_CLICK_AIR) && !event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;

        ItemStack potion = event.getItem();

        CustomItem customItem = CustomItemManager.getCustomItem(potion);

        if(!(customItem instanceof CustomBattlePotion customBattlePotion)) return;


        event.setCancelled(true);
        ItemMeta itemMeta = potion.getItemMeta();

        HashMap<Integer, CustomBattlePotionOrb> orbs = customBattlePotion.getOrbs(itemMeta);

        StorageGui gui = Gui.storage()
                .title(Component.text(ColorFixer.addColors("&f七七七七七七七七≓")))
                .rows(2)
                .create();

        GuiItem invisibleItem = new GuiItem(ItemManager.invisibleFiller, e -> {
            e.setCancelled(true);
        });


        gui.getFiller().fill(invisibleItem);

        gui.removeItem(ITEM1_SLOT);
        gui.removeItem(ITEM2_SLOT);

        for (int i = 1; i <=2; i++) {
            CustomBattlePotionOrb customBattlePotionOrb = orbs.get(i);
            if (customBattlePotionOrb == null) continue;
            ItemStack orbItem = customBattlePotionOrb.getDefaultItemStack();
            gui.getInventory().setItem(i == 1 ? ITEM1_SLOT : ITEM2_SLOT, orbItem);
        }

        gui.setCloseGuiAction(e -> {

            ItemStack item1 = gui.getInventory().getItem(ITEM1_SLOT);
            ItemStack item2 = gui.getInventory().getItem(ITEM2_SLOT);

            CustomBattlePotionOrb customBattlePotionOrb1 = CustomItemManager.getCustomPotionOrb(item1);
            CustomBattlePotionOrb customBattlePotionOrb2 = CustomItemManager.getCustomPotionOrb(item2);

            customBattlePotion.setOrbAtIndex(itemMeta, 1, customBattlePotionOrb1);
            customBattlePotion.setOrbAtIndex(itemMeta, 2, customBattlePotionOrb2);

            int tier = 1;
            if(customItem instanceof Tierable tierable) {
                tier = Tierable.getTier(potion);
            }
            customItem.setLoreAndName(itemMeta, tier);

            potion.setItemMeta(itemMeta);
        });

        gui.open(player);

    }
}
