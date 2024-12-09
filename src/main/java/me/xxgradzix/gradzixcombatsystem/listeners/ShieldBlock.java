package me.xxgradzix.gradzixcombatsystem.listeners;

import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.AttributePointsManager;
import me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager;
import me.xxgradzix.gradzixcombatsystem.managers.messages.MessageType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;

public class ShieldBlock implements Listener {

    @EventHandler
    public void onShieldBlock(EntityDamageByEntityEvent event) {

        if(event.getEntity() instanceof Player player) {
            ItemStack item = player.getInventory().getItemInMainHand();
            ItemStack offItem = player.getInventory().getItemInOffHand();

            if(!player.isBlocking()) return;

            if(item.getType().equals(Material.SHIELD) || offItem.getType().equals(Material.SHIELD)) {
                if(!AttributePointsManager.hasRequiredAttribute(item, player)) {
                    MessageManager.sendMessageFormated(player, MessageManager.BLOCKING_UNSUCCESSFUL, MessageType.SUBTITLE);
                }
            }
        }
    }



    @EventHandler
    public void onShieldBlock(PlayerInteractEvent event) {


        if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            ItemStack item = event.getItem();

            if(item == null) return;

            Player player = event.getPlayer();
            if(Material.SHIELD.equals(item.getType())) {
                if(!AttributePointsManager.hasRequiredAttribute(item, event.getPlayer())) {
                    player.setShieldBlockingDelay(99999);
                    MessageManager.sendMessageFormated(player, MessageManager.BLOCKING_UNSUCCESSFUL, MessageType.SUBTITLE);
                } else {
                    player.setShieldBlockingDelay(0);
                }
            }


        }

    }

}
