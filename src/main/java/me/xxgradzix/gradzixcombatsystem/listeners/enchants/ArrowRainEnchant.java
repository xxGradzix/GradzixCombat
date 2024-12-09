package me.xxgradzix.gradzixcombatsystem.listeners.enchants;

import me.xxgradzix.gradzixcombatsystem.managers.EnchantManager.EnchantManager;
import me.xxgradzix.gradzixcombatsystem.managers.magicEffects.MagicEffectManager;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

public class ArrowRainEnchant implements Listener {



    @EventHandler(priority = EventPriority.LOWEST)
    public void onArrowRain(EntityShootBowEvent event) {

        ItemStack bow = event.getBow();
        int enchantLevel = EnchantManager.getEnchantLevel(bow, EnchantManager.Enchant.ARROW_RAIN);
        if (enchantLevel <= 0) return;
        Entity projectile = event.getProjectile();
        LivingEntity entity = event.getEntity();
        if (!(projectile instanceof Arrow arrow)) return;
        if (!(entity instanceof Player player)) return;

        projectile.setVisibleByDefault(false);

//        MagicEffectManager.useArrowRainEffect(MagicEffectManager.MagicUseVariant.ENCHANT, Optional.of(player), enchantLevel, false, Optional.empty(), Optional.empty(), arrow);
        new MagicEffectManager.SpellBuilder().effectType(MagicEffectManager.MagicEffectType.ARROW_RAIN).useVariant(MagicEffectManager.MagicUseVariant.ENCHANT).caster(player).level(enchantLevel).superCharge(false).arrow(arrow).cast();

    }

//    private void arrowRainV1(Arrow originalArrow, int enchantLevel, Player player) {
//
//        originalArrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
//
//        int shift = 4;
//
//        int arrowDistance = (enchantLevel >= 2 ? shift*4: shift*3);
//
//        final Location startLocation = player.getLocation();
//
//        List<Projectile> projectiles = new ArrayList<>();
//        projectiles.add(originalArrow);
//
//        AtomicReference<Arrow> previousArrow = new AtomicReference<>(originalArrow);
//
//
//        for (int i = shift; i < arrowDistance; i += shift) {
//
//            int finalI = i;
//
//            int spawnTIme = finalI - shift;
//
//            Bukkit.getScheduler().runTaskLater(GradzixCombatSystem.plugin, () -> {
//
//                Location location = startLocation.clone().add(startLocation.getDirection().multiply(finalI).add(new Vector(0, 1.5, 0)));
//                location.add(0, 5, 0);
//                location.getWorld().spawnParticle(Particle.CLOUD, location, 10, 0.5, 0.5, 0.5, 0.05);
//
//                Arrow summonedArrowAtLoc = summonArrowAtLoc(previousArrow.get(), location, enchantLevel);
//
//                previousArrow.set(summonedArrowAtLoc);
//
//                if(spawnTIme == 0) originalArrow.remove();
//
//            }, spawnTIme);
//
//
//
//        }
//
//    }
//
//    private Arrow summonArrowAtLoc(Arrow arrow, Location location, int level) {
//
//        Arrow arrowToReturn = null;
//        if (level >= 3) {
//            for (int j = 0; j < 3; j++) {
//                Location location1 = location.clone().add(Math.random() * 1.9 - 0.95, 0, Math.random() * 1.9 - 0.95);
//                Arrow arrow1 = summonArrowAtLoc(arrow, location1);
//                arrowToReturn = arrow1;
//            }
//        } else {
//            Arrow arrow1 = summonArrowAtLoc(arrow, location);
//            arrowToReturn = arrow1;
//        }
//        return arrowToReturn;
//    }
//
//    private Arrow summonArrowAtLoc(Arrow arrow, Location location) {
//
//        Arrow copy = (Arrow) arrow.copy(location);
//
//        copy.setVisibleByDefault(true);
//        copy.setShooter(arrow.getShooter());
//
//        copy.setRotation(0, 0);
//
//        Vector velocity = copy.getVelocity();
//        velocity.setX(0).setZ(0).setY(-1.5);
//        copy.setVelocity(velocity);
//
//        copy.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
//
//        return copy;
//    }


}
