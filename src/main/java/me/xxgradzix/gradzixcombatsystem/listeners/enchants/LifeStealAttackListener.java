package me.xxgradzix.gradzixcombatsystem.listeners.enchants;

import me.xxgradzix.gradzixcombatsystem.managers.EnchantManager.EnchantManager;
import me.xxgradzix.gradzixcombatsystem.managers.magicEffects.MagicEffectManager;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;
import java.util.Random;

public class LifeStealAttackListener implements Listener {

    private static final Random random = new Random();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamage(EntityDamageByEntityEvent event) {

        if(event.isCancelled()) return;
        if(!(event.getDamager() instanceof Player damager)) return;
        if(!(event.getEntity() instanceof LivingEntity damaged)) return;

        ItemStack itemInMainHand = damager.getInventory().getItemInMainHand();
        int enchantLevel = EnchantManager.getEnchantLevel(itemInMainHand, EnchantManager.Enchant.LIFE_STEAL);
        if(enchantLevel == 0) return;

//        MagicEffectManager.useLifeStealEffect(MagicEffectManager.MagicUseVariant.ENCHANT, Optional.of(damager), enchantLevel, false, Optional.empty(), Optional.of(damaged));

        new MagicEffectManager.SpellBuilder().effectType(MagicEffectManager.MagicEffectType.FIRE).useVariant(MagicEffectManager.MagicUseVariant.ENCHANT).caster(damager).level(enchantLevel).superCharge(false).target(damaged).cast();


//        if(!isLifeStealSuccessful(enchantLevel)) return;
//
//        double damagedHealth = damaged.getHealth();
//        double lifeToTransfer = damagedHealth / 5;
//
//        damager.setHealth(damager.getHealth() + lifeToTransfer);
//        damaged.setHealth(damagedHealth - lifeToTransfer);
//
//        damaged.getLocation().getWorld().spawnParticle(Particle.SOUL, damaged.getLocation(), 15, 0.7, 0.7, 0.7, 0.05);
//        damager.getLocation().getWorld().spawnParticle(Particle.HEART, damaged.getLocation(), 5, 0.7, 0.7, 0.7, 0.05);

    }


}
