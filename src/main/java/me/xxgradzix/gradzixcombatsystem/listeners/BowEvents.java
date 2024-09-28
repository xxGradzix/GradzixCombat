package me.xxgradzix.gradzixcombatsystem.listeners;

import me.xxgradzix.gradzixcombatsystem.items.ItemManager;
import me.xxgradzix.gradzixcombatsystem.items.projectiles.CustomArrow;
import me.xxgradzix.gradzixcombatsystem.items.projectiles.instances.GravitionalArrow;
import me.xxgradzix.gradzixcombatsystem.items.projectiles.instances.KnockBackArrow;
import me.xxgradzix.gradzixcombatsystem.items.weapons.ShootableWeapon;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.AttributeManager;
import me.xxgradzix.gradzixcombatsystem.managers.modifiersManager.ModifiersManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class BowEvents implements Listener {

    @EventHandler
    public void debugShoot (EntityShootBowEvent event) {
        Bukkit.broadcastMessage("Moc: " + event.getForce());
    }

    @EventHandler
    public void debug (EntityDamageByEntityEvent event) {

        if(event.getDamager() instanceof Player player) {
            Bukkit.broadcastMessage("gracz " + event.getDamager().getName() + ": " + event.getDamage() + " crit: " + event.isCritical());
            return;
        }

        if(event.getDamager() instanceof Arrow arrow) {
            event.setDamage(arrow.getDamage());
            Bukkit.broadcastMessage("strzała: " + event.getDamage() + " crit: " + event.isCritical());
        }
    }

    @EventHandler
    public void bowDamageEvent(EntityShootBowEvent event) {

        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        if (!(event.getProjectile() instanceof Arrow arrow)) {
            return;
        }

        ItemStack bow = event.getBow();

        ItemStack arrowItemStack = event.getConsumable();

        if (arrowItemStack == null) {
            return;
        }

        CustomArrow customArrow = ItemManager.getCustomArrow(arrowItemStack);

        float force = event.getForce();

        double savedArrowDamage = CustomArrow.getSavedArrowDamage(arrowItemStack);

        double bowDamage = ShootableWeapon.getWeaponDamage(bow);

        double finalDamage = (savedArrowDamage + bowDamage) * (force/3);

        arrow.setDamage(finalDamage);

        if (customArrow instanceof GravitionalArrow) {
            arrow.setGravity(false);
        }
        if(customArrow instanceof KnockBackArrow knockBackArrow) {
            arrow.setKnockbackStrength(knockBackArrow.getKnockBack(CustomArrow.getTier(arrowItemStack)));
        }
    }

    @EventHandler
    public void arrowSpeedEvent(EntityShootBowEvent event) {

        if(!(event.getEntity() instanceof Player player)) {
            return;
        }

        ItemStack bow = event.getBow();

        boolean hasAttributes = AttributeManager.hasRequiredAttribute(bow, player);

        double multiplierValue = ModifiersManager.getMultiplierValue(bow, ModifiersManager.Multiplier.ARROW_SPEED);

        if(!hasAttributes) {
            multiplierValue = 0.01;
        }

        Entity projectile = event.getProjectile();
        Vector multiply = projectile.getVelocity().multiply(multiplierValue);
        event.getProjectile().setVelocity(multiply);
    }
}
