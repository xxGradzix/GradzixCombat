package me.xxgradzix.gradzixcombatsystem.listeners.enchants;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.managers.EnchantManager.EnchantManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class ArrowRainEnchant implements Listener {



    @EventHandler(priority = EventPriority.LOWEST)
    public void onArrowRain(EntityShootBowEvent event) {

        ItemStack bow = event.getBow();

        int enchantLevel = EnchantManager.getEnchantLevel(bow, EnchantManager.Enchant.ARROW_RAIN);

        if (enchantLevel <= 0) return;

        Entity projectile = event.getProjectile();

        LivingEntity entity = event.getEntity();

        if (!(projectile instanceof Arrow arrow)) return;

        arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);

        if (!(entity instanceof Player player)) return;

        projectile.setVisibleByDefault(false);

        int arrowDistance = (enchantLevel >= 2 ? 30 : 20);

        final Location startLocation = player.getLocation();

        List<Projectile> projectiles = new ArrayList<>();

        projectiles.add(arrow);
        for (int i = 4; i < arrowDistance; i += 4) {

            int finalI = i;
            Bukkit.getScheduler().runTaskLater(GradzixCombatSystem.plugin, () -> {
                Location location = startLocation.clone().add(startLocation.getDirection().multiply(finalI).add(new Vector(0, 1.5, 0)));
                location.add(0, 5, 0);
                location.getWorld().spawnParticle(Particle.CLOUD, location, 10, 0.5, 0.5, 0.5, 0.05);

                if (enchantLevel >= 3) {
                    for (int j = 0; j < 3; j++) {
                        Location location1 = location.clone().add(Math.random() * 1.9 - 0.95, 0, Math.random() * 1.9 - 0.95);
                        Arrow arrow1 = summonArrowAtLoc(arrow, location1);
                        projectiles.add(arrow1);
                    }
                } else {
                    Arrow arrow1 = summonArrowAtLoc(arrow, location);
                    projectiles.add(arrow1);
                }


            }, i);

        }

        Bukkit.getScheduler().runTaskLater(GradzixCombatSystem.plugin, () -> {
            for (Projectile projectile1 : projectiles) {
                projectile1.remove();
            }
        }, arrowDistance + 10);
    }
    private Arrow summonArrowAtLoc(Arrow arrow, Location location) {

        Arrow copy = (Arrow) arrow.copy(location);

        copy.setVisibleByDefault(true);
        copy.setShooter(arrow.getShooter());

        copy.setRotation(0, 0);

        Vector velocity = copy.getVelocity();
        velocity.setX(0).setZ(0).setY(-1.5);
        copy.setVelocity(velocity);

        copy.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);

        return copy;
    }


}
