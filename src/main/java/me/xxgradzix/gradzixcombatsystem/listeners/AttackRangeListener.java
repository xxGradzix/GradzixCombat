package me.xxgradzix.gradzixcombatsystem.listeners;

import io.papermc.paper.event.player.PlayerArmSwingEvent;
import io.papermc.paper.event.player.PrePlayerAttackEntityEvent;
import me.xxgradzix.gradzixcombatsystem.managers.modifiersManager.ModifiersManager;
import org.bukkit.Bukkit;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerAnimationType;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.RayTraceResult;

public class AttackRangeListener implements Listener {

//    private final static double RANGE = 3.3;

//    @EventHandler
//    public void onHit(EntityDamageByEntityEvent e) {
//        if(e.getDamager() instanceof Player) {
//
//            Player player = (Player) e.getDamager();
//            Entity victim =  e.getEntity();
//            double range = RANGE;
//
//            if(Material.TRIDENT.equals(player.getInventory().getItemInMainHand().getType())) {
//                range += 1;
//            }
//
//            double multiplierValue = ModifiersManager.getMultiplierValue(player.getInventory().getItemInMainHand(), ModifiersManager.Multiplier.RANGE_MULTIPLIER);
//
//            range *= multiplierValue;
//
//            if(player.getLocation().distance(victim.getLocation()) > range) {
//                e.setCancelled(true);
//            }
//        }
//    }
//    @EventHandler
//    public void b(PlayerAnimationEvent e) {
//            Player player = e.getPlayer();
//
//            if(!e.getAnimationType().equals(PlayerAnimationType.ARM_SWING)) return;
//
////            if(player.is)
//
//            double range = RANGE;
//
//            if(Material.TRIDENT.equals(player.getInventory().getItemInMainHand().getType())) {
//                range += 1;
//            }
//
//            double multiplierValue = ModifiersManager.getMultiplierValue(player.getInventory().getItemInMainHand(), ModifiersManager.Multiplier.RANGE_MULTIPLIER);
//
//            range *= multiplierValue;
//
//            Entity target = getTarget(player, range);
//
//            if (target != null) player.attack(target);
//
//    }
//
//    @EventHandler
//    public void dadd(PlayerInteractEvent e) {
//        Bukkit.broadcastMessage("ATT " + e.getAction().name());
//    }
//
//    @EventHandler
//    public void dadadadd(PlayerInteractEvent e) {
//        Bukkit.broadcastMessage("PlayerInteractEvent");
//    }
//
//    @EventHandler
//    public void onHit(PlayerInteractAtEntityEvent e) {
//        Bukkit.broadcastMessage("PlayerInteractAtEntityEvent");
//    }
//
//
//
//    public Entity getTarget(Player player, double range) {
//        RayTraceResult result = player.getWorld().rayTrace(player.getEyeLocation(), player.getEyeLocation().getDirection(), range, FluidCollisionMode.NEVER, true, 0.0, (entity) -> entity instanceof LivingEntity && !entity.equals(player));
//        Object closer = this.getCloser(result);
//        return closer instanceof LivingEntity ? (Entity)closer : null;
//    }
//
//    public Object getCloser(RayTraceResult result) {
//        if (result == null) {
//            return null;
//        } else {
//            LivingEntity entity = (LivingEntity)result.getHitEntity();
//            Block block = result.getHitBlock();
//            if (entity == null) {
//                return block;
//            } else {
//                return block == null ? entity : null;
//            }
//        }
//    }
}
