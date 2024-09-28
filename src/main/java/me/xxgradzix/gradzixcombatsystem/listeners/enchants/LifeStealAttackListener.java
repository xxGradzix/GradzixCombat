package me.xxgradzix.gradzixcombatsystem.listeners.enchants;

import me.xxgradzix.gradzixcombatsystem.managers.EnchantManager.EnchantManager;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class LifeStealAttackListener implements Listener {

    private static final Random random = new Random();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamage(EntityDamageByEntityEvent event) {

        if(event.isCancelled()) {
            return;
        }

        if(!(event.getDamager() instanceof Player damager)) {
            return;
        }

        if(!(event.getEntity() instanceof LivingEntity damaged)) {
            return;
        }

        ItemStack itemInMainHand = damager.getInventory().getItemInMainHand();

        int enchantLevel = EnchantManager.getEnchantLevel(itemInMainHand, EnchantManager.Enchant.LIFE_STEAL);

        if(enchantLevel == 0) return;

        if(!isLifeStealSuccessful(enchantLevel)) {
            return;
        }

        double damagedHealth = damaged.getHealth();

        double lifeToTransfer = damagedHealth / 5;


        damager.setHealth(damager.getHealth() + lifeToTransfer);
        damaged.setHealth(damagedHealth - lifeToTransfer);

        damaged.getLocation().getWorld().spawnParticle(Particle.SOUL, damaged.getLocation(), 15, 0.7, 0.7, 0.7, 0.05);
        damager.getLocation().getWorld().spawnParticle(Particle.HEART, damaged.getLocation(), 5, 0.7, 0.7, 0.7, 0.05);

    }

    private boolean isLifeStealSuccessful(int tier) {
        double chance = getLifeStealChance(tier);

        return chance >= random.nextDouble();
    }

    private double getLifeStealChance(int tier) {
        switch (tier) {
            case 1:
                return 0.05;
            case 2:
                return 0.10;
            case 3:
                return 0.15;
            default:
                return 0;
        }
    }


}
