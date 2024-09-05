package me.xxgradzix.gradzixcombatsystem.listeners;

import me.xxgradzix.gradzixcombatsystem.items.ItemManager;
import me.xxgradzix.gradzixcombatsystem.managers.AttributeManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class RestartPotionDrink implements Listener {

    @EventHandler
    public void onRestartPotionDrink(PlayerItemConsumeEvent event) {

        ItemStack item = event.getItem();
        Player player = event.getPlayer();

        if(ItemManager.restartPotion.equals(item)) {
            AttributeManager.resetAttributes(player, AttributeManager.getTotalAttributePoints(player));
        }

    }



}
