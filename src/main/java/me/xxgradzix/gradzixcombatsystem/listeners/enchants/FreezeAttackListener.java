package me.xxgradzix.gradzixcombatsystem.listeners.enchants;

import me.xxgradzix.gradzixcombatsystem.managers.EnchantManager.EnchantManager;
import me.xxgradzix.gradzixcombatsystem.managers.magicEffects.MagicEffectManager;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;
import java.util.Random;

public class FreezeAttackListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamage(EntityDamageByEntityEvent event) {

        if(event.isCancelled()) return;

        if(!(event.getDamager() instanceof Player damager)) return;

        ItemStack itemInMainHand = damager.getInventory().getItemInMainHand();
        int freezeLevel = EnchantManager.getEnchantLevel(itemInMainHand, EnchantManager.Enchant.FREEZE);
        if(freezeLevel == 0) return;

        Entity target = event.getEntity();

        if(!(target instanceof LivingEntity)) return;

        MagicEffectManager.useFreezeEffect(MagicEffectManager.MagicUseVairant.ENCHANT, Optional.of(damager), freezeLevel, false, Optional.empty(), Optional.of((LivingEntity) event.getEntity()));

//
//        Entity damaged = event.getEntity();
//
//        int ticksToAdd = switch (freezeLevel) {
//            case 1 -> random.nextInt(25, 40);
//            case 2 -> random.nextInt(35, 60);
//            case 3 -> random.nextInt(45, 80);
//            default -> 0;
//        };
//
//        int previousTicks = damaged.getFreezeTicks();
//
//        int newTicks = previousTicks + ticksToAdd;
//        if(newTicks > 400) {
//            newTicks = 400;
//        }
//
//        damaged.setFreezeTicks(newTicks);
//
//        damaged.getLocation().getWorld().spawnParticle(Particle.SNOWFLAKE, damaged.getLocation(), 15, 0.6, 0.6, 0.6, 0.05);

    }


}
