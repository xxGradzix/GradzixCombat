package me.xxgradzix.gradzixcombatsystem.listeners.enchants;

import me.xxgradzix.gradzixcombatsystem.managers.EnchantManager.EnchantManager;
import me.xxgradzix.gradzixcombatsystem.managers.magicEffects.MagicEffectManager;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

public class FireAfflictionEnchant implements Listener {

    @EventHandler
    public void playerAttack(EntityDamageByEntityEvent event) {

        if(!(event.getDamager() instanceof Player player)) return;
        ItemStack itemUsed = player.getInventory().getItemInMainHand();
        int enchantLevel = EnchantManager.getEnchantLevel(itemUsed, EnchantManager.Enchant.FIRE_AFFLICTION);
        if(enchantLevel <= 0) return;

        event.getEntity().setFireTicks(100);

    }

    @EventHandler
    public void onFireAfflictionDamage(PlayerInteractEvent event) {

        if(!event.getAction().equals(Action.RIGHT_CLICK_AIR) && !event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;

        ItemStack itemUsed = event.getItem();

        if(itemUsed == null) return;

        int enchantLevel = EnchantManager.getEnchantLevel(itemUsed, EnchantManager.Enchant.FIRE_AFFLICTION);

        if(enchantLevel <= 0) return;

        Player player = event.getPlayer();

        int cooldown = player.getCooldown(itemUsed.getType());

        if(cooldown > 0) return;

//        MagicEffectManager.useFireEffect(MagicEffectManager.MagicUseVariant.ENCHANT, Optional.of(player), enchantLevel, false, Optional.empty(), Optional.empty());

        new MagicEffectManager.SpellBuilder().effectType(MagicEffectManager.MagicEffectType.FIRE).useVariant(MagicEffectManager.MagicUseVariant.ENCHANT).caster(player).level(enchantLevel).superCharge(false).cast();



//        player.setCooldown(itemUsed.getType(), 20*7);
    }

//    private void burstFire(Player player, int level) {
//
//         Location location = player.getLocation().add(0, 1.5, 0);
//         location.add(location.getDirection().multiply(1.5));
//
//         final int sample = level == 2 ? 55 : 75;
//
//         Vector playerAxis = new Vector(0, 1, 0).normalize();
//
//         Vector direction  = location.clone().getDirection();
//
//         Vector axisToRotate = new Vector(direction.getZ(), 0, -direction.getX());
//
//         double angle = Math.acos(direction.dot(playerAxis) / (direction.length() * playerAxis.length()));
//
//         Vector pependicularVerticalAxis = playerAxis.clone().rotateAroundAxis(axisToRotate, Math.toRadians(Math.toDegrees(angle) - 90)).normalize();
//
//         for (int i = 0; i < sample; i += 10) {
//
//             Vector rotated1 = direction.clone().rotateAroundAxis(pependicularVerticalAxis, Math.toRadians(i));
//             Vector rotated2 = direction.clone().rotateAroundAxis(pependicularVerticalAxis, Math.toRadians(-i));
//
//             Location start1 = location.clone().add(rotated1.clone().multiply(0.7));
//             Location start2 = location.clone().add(rotated2.clone().multiply(0.7));
//
//             Vector endVector1 = rotated1.clone().multiply(level);
//             Vector endVector2 = rotated2.clone().multiply(level);
//
//             Location end1 = location.clone().add(endVector1);
//             Location end2 = location.clone().add(endVector2);
//
//             double flameRadius = level == 2 ? 0.6 : 0.8;
//
//             end1.getNearbyEntities(flameRadius, 0.5, flameRadius).forEach(entity -> {
//                 if(entity instanceof LivingEntity player1) {
//                     player1.setFireTicks(60);
//                 }
//             });
//
//             end2.getNearbyEntities(flameRadius, 0.5, flameRadius).forEach(entity -> {
//                 if(entity instanceof LivingEntity player1) {
//                     player1.setFireTicks(60);
//                 }
//             });
//
//             player.spawnParticle(Particle.FLAME, start1, 0, endVector1.getX(), endVector1.getY(), endVector1.getZ(), 0.1);
//             player.spawnParticle(Particle.FLAME, start2, 0, endVector2.getX(), endVector2.getY(), endVector2.getZ(), 0.1);
//
//             player.spawnParticle(Particle.SMALL_FLAME, end1, 8, flameRadius/5, flameRadius/5, flameRadius/5, 0.04);
//             player.spawnParticle(Particle.SMALL_FLAME, end2, 8, flameRadius/5, flameRadius/5, flameRadius/5, 0.04);
//
//             player.getWorld().playSound(location, Sound.ENTITY_BLAZE_SHOOT, 0.4f, 0.3f);
//         }
//    }

}
