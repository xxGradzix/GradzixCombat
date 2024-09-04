package me.xxgradzix.gradzixcombatsystem.listeners;

import me.xxgradzix.gradzixcombatsystem.managers.AttributeManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ShieldBlock implements Listener {


    @EventHandler
    public void onShieldBlock(PlayerInteractEvent event) {

        if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            ItemStack item = event.getItem();

            if(item.getType().equals(Material.SHIELD)) {
                if(!AttributeManager.hasRequiredAttribute(item, event.getPlayer())) {
                    event.setCancelled(true);
                }
            }


        }

    }

}
