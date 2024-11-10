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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Optional;
import java.util.Random;

public class PoisonAttackListener implements Listener {

    private static final Random random = new Random();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamage(EntityDamageByEntityEvent event) {

        if(event.isCancelled()) {
            return;
        }

        if(!(event.getDamager() instanceof Player damager)) return;


        ItemStack itemInMainHand = damager.getInventory().getItemInMainHand();

        int poisonLevel = EnchantManager.getEnchantLevel(itemInMainHand, EnchantManager.Enchant.POISON);

        if(poisonLevel == 0) return;

        Entity damaged = event.getEntity();
        if(!(damaged instanceof LivingEntity livingEntity)) return;

        MagicEffectManager.usePoisonEffect(MagicEffectManager.MagicUseVairant.ENCHANT, Optional.of(damager), poisonLevel, false, Optional.empty(), Optional.of(livingEntity));

//        double chance = getPoisonChance(poisonLevel);
//
////        if(random.nextDouble() > chance) {
//
//            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20 * 5, 1));
//            if(poisonLevel >= 2) {
//                livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 20 * 2, 2));
//            }
//
////        }



    }

    private double getPoisonChance(int poisonLevel) {

        switch (poisonLevel) {
            case 1:
                return 0.10;
            case 2:
                return 0.15;
            case 3:
                return 0.20;
            default:
                return 0;
        }
    }


}
