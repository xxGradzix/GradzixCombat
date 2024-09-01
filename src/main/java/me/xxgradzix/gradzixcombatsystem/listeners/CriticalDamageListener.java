package me.xxgradzix.gradzixcombatsystem.listeners;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.managers.AttributeManager;
import me.xxgradzix.gradzixcombatsystem.managers.MessageManager;
import me.xxgradzix.gradzixcombatsystem.managers.MessageType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Collection;
import java.util.List;
import java.util.Random;

public class CriticalDamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();

        if(!(damager instanceof Player player)) {
            return;
        }

        ItemStack item = player.getInventory().getItemInMainHand();

        boolean hasRequiredAttribute = AttributeManager.hasRequiredAttribute(item, player);

        if(!hasRequiredAttribute) {
            if(new Random().nextDouble() < 0.5) {
                event.setCancelled(true);

                MessageManager.sendMessageFormated(player, MessageManager.YOU_MISSED, MessageType.TITLE);
                MessageManager.sendMessageFormated(player, MessageManager.NOT_SUFFICIENT_ATTRIBUTES, MessageType.SUBTITLE);
            }
        }
    }

    @EventHandler
    public void bowShootEvent(EntityShootBowEvent event) {

        if(!(event.getEntity() instanceof Player player)) {
            return;
        }

        ItemStack mainHandItem = player.getInventory().getItemInMainHand();

        if(mainHandItem.getType().equals(Material.BOW)) {
            Entity projectile = event.getProjectile();
            Vector multiply = projectile.getVelocity().multiply(2);
            event.getProjectile().setVelocity(multiply);

        }
    }

//    @EventHandler
//    public void onSprint(PlayerLaunchProjectileEvent event) {
//        Player player = event.getPlayer();
//
//        ItemStack mainHandItem = player.getInventory().getItemInMainHand();
//        ItemStack offHandItem = player.getInventory().getItemInOffHand();
//
//
//        if(mainHandItem.getType().equals(Material.BOW)) {
//
//            event.getProjectile().getVelocity().multiply(50);
//
//        }
//
//        if(mainHandItem == null || offHandItem == null) return;
//
//        if(mainHandItem.getType().equals(Material.TRIDENT) && offHandItem.getType().equals(Material.SHIELD)) {
//
//            event.setCancelled(true);
//
//            // charge player 5 blocks forward and deal damage on collision with entity
//
//            chargePlayer(player);
//        }
//
//    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.LEFT_CLICK_AIR && event.getAction() != Action.LEFT_CLICK_BLOCK) {
            return;
        }

        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (item.getType() == Material.TRIDENT) {
            hitEntityAtRange(player, 5.0); // Check and hit entity at 5 blocks range
        }
    }

    private void hitEntityAtRange(Player player, double range) {

        ItemStack weapon = player.getInventory().getItemInMainHand();

        Vector direction = player.getLocation().getDirection().normalize();
        Vector targetLocation = player.getLocation().toVector().multiply(range);

        Bukkit.broadcastMessage("Target Location: " + targetLocation.getX() + " " + targetLocation.getY() + " " + targetLocation.getZ());
        List<Entity> nearbyEntities = player.getNearbyEntities(range, range, range);
        for (Entity entity : nearbyEntities) {
            if (entity instanceof LivingEntity && entity != player) {
                Vector entityLocation = entity.getLocation().toVector();
                if (entityLocation.distance(targetLocation) < 1.0) { // Check if the entity is within 1 block of the target location
                    Collection<AttributeModifier> attributeModifiers = weapon.getItemMeta().getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE);
                    if (attributeModifiers != null) {
                        double damage = attributeModifiers.stream().mapToDouble(AttributeModifier::getAmount).sum();
                        Bukkit.broadcastMessage("Hand raised " + player.isHandRaised());

                        ((LivingEntity) entity).damage(damage, player); // Deal damage to the entity
                        ((LivingEntity) entity).setLastDamageCause(new EntityDamageByEntityEvent(player, entity, EntityDamageEvent.DamageCause.ENTITY_ATTACK, damage)); // Deal damage to the entity

                    }
                    break;
                }
            }
        }
    }


    private void chargePlayer(Player player) {
        Vector direction = player.getLocation().getDirection().normalize().setY(0).multiply(2);
        player.setVelocity(direction);

        Bukkit.getScheduler().runTaskLater(GradzixCombatSystem.plugin, () -> {
            player.setVelocity(new Vector(0, 0, 0)); // Stop the player's movement

            List<Entity> nearbyEntities = player.getNearbyEntities(2, 2, 2);
            for (Entity entity : nearbyEntities) {
                if (entity instanceof LivingEntity && entity != player) {
                    ((LivingEntity) entity).damage(10, player); // Deal damage to the collided entity
                }
            }
            pushBackEntities(player);
        }, 7L); // Delay to allow the player to move forward
    }

    private void pushBackEntities(Player centerEntity) {
        List<Entity> nearbyEntities = centerEntity.getNearbyEntities(2, 2, 2);
        for (Entity entity : nearbyEntities) {
            if (entity != centerEntity) {
                Vector pushDirection = entity.getLocation().toVector().subtract(centerEntity.getLocation().toVector()).setY(0.2).normalize().multiply(0.8);
                entity.setVelocity(pushDirection);
            }
        }
    }



}
