package me.xxgradzix.gradzixcombatsystem.listeners;

import me.xxgradzix.gradzixcombatsystem.items.ItemManager;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.AttributeManager;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class StoneOFAggressionUse implements Listener {

    @EventHandler
    public void onRestartPotionDrink(PlayerInteractEvent event) {

        ItemStack item = event.getItem();
        Player player = event.getPlayer();

        if(ItemManager.stoneOfAggression.isSimilar(item)) {
            player.getInventory().removeItem(ItemManager.stoneOfAggression);
            List<Entity> nearbyEntities = player.getNearbyEntities(50, 50, 50);

            for(Entity entity : nearbyEntities) {
                if(entity instanceof Creature creature) {
                    creature.setTarget(player);
                    creature.attack(player);
                }
            }
        }

    }



}
