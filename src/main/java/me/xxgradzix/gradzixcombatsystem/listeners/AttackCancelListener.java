package me.xxgradzix.gradzixcombatsystem.listeners;

import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.AttributeManager;
import me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager;
import me.xxgradzix.gradzixcombatsystem.managers.messages.MessageType;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Random;

public class AttackCancelListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();

        if(!(damager instanceof Player player)) {
            return;
        }

        ItemStack item = player.getInventory().getItemInMainHand();

        boolean hasRequiredAttribute = AttributeManager.hasRequiredAttribute(item, player);

        if(!hasRequiredAttribute) {
            if(new Random().nextDouble() < 0.75) {
                event.setCancelled(true);

                MessageManager.sendMessageFormated(player, MessageManager.YOU_MISSED, MessageType.TITLE);
                MessageManager.sendMessageFormated(player, MessageManager.NOT_SUFFICIENT_ATTRIBUTES, MessageType.SUBTITLE);
            }
        }
    }


//    @EventHandler
//    public void bowShootEvent(EntityShootBowEvent event) {
//
//        if(!(event.getEntity() instanceof Player player)) {
//            return;
//        }
//
//        ItemStack bow = event.getBow();
//
//        boolean hasAttributes = AttributeManager.hasRequiredAttribute(bow, player);
//
//        if(!hasAttributes) {
//            event.setCancelled(true);
//            return;
//        }
//
//        Entity projectile = event.getProjectile();
//        Vector multiply = projectile.getVelocity().multiply(1.1);
//        event.getProjectile().setVelocity(multiply);
//    }


}
