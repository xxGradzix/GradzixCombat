package me.xxgradzix.gradzixcombatsystem.listeners;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.items.CustomItem;
import me.xxgradzix.gradzixcombatsystem.items.CustomItemManager;
import me.xxgradzix.gradzixcombatsystem.items.projectiles.CustomArrow;
import me.xxgradzix.gradzixcombatsystem.items.projectiles.instances.ExplosiveArrow;
import me.xxgradzix.gradzixcombatsystem.items.projectiles.instances.GravitionalArrow;
import me.xxgradzix.gradzixcombatsystem.items.projectiles.instances.KnockBackArrow;
import me.xxgradzix.gradzixcombatsystem.items.weapons.ShootableWeapon;
import me.xxgradzix.gradzixcombatsystem.items.weapons.Tierable;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.AttributeManager;
import me.xxgradzix.gradzixcombatsystem.managers.modifiersManager.ModifiersManager;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

public class BowEvents implements Listener {

    @EventHandler
    public void debugShoot (EntityShootBowEvent event) {
    }

    @EventHandler
    public void debug(EntityDamageByEntityEvent event) {

        if(event.getDamager() instanceof Player player) return;

        if(event.getDamager() instanceof Arrow arrow) {
            event.setDamage(arrow.getDamage());
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void bowDamageEvent(EntityShootBowEvent event) {

        if (!(event.getEntity() instanceof Player player)) return;

        if (!(event.getProjectile() instanceof Arrow arrow)) return;

        ItemStack bow = event.getBow();

        ItemStack arrowItemStack = event.getConsumable();

        if (arrowItemStack == null) return;

        CustomItem customArrow = CustomItemManager.getCustomItem(arrowItemStack);

        if(!(customArrow instanceof CustomArrow)) return;

        float force = event.getForce();

        double savedArrowDamage = CustomArrow.getSavedArrowDamage(arrowItemStack);

        double bowDamage = ShootableWeapon.getWeaponDamage(bow);

        double finalDamage = (savedArrowDamage + bowDamage) * (force/3);

        arrow.setDamage(finalDamage);

        if (customArrow instanceof ExplosiveArrow) {
            if(force < 3) return;
            PersistentDataContainer persistentDataContainer = arrow.getPersistentDataContainer();
            persistentDataContainer.set(CAN_BE_APPLIED, PersistentDataType.BOOLEAN, true);
        }

        if (customArrow instanceof GravitionalArrow) {
            if(force < 3) return;
            arrow.setGravity(false);
        }

        if(customArrow instanceof KnockBackArrow knockBackArrow) {
            if(force < 3) return;
            arrow.setKnockbackStrength(knockBackArrow.getKnockBack(Tierable.getTier(arrowItemStack)));
        }
    }

    private static final NamespacedKey CAN_BE_APPLIED = new NamespacedKey(GradzixCombatSystem.plugin, "arrow_enchant_can_be_applied");

    @EventHandler
    public void explosionEvent(ProjectileHitEvent event) {

        if(!(event.getEntity() instanceof Arrow arrow)) return;

        if(!(arrow.getShooter() instanceof Player player)) return;

        ItemStack arrowItemStack = arrow.getItemStack();

        CustomItem customArrow = CustomItemManager.getCustomItem(arrowItemStack);

        if(!(customArrow instanceof CustomArrow)) return;

        if(!arrow.getPersistentDataContainer().has(CAN_BE_APPLIED)) return;

        if (customArrow instanceof ExplosiveArrow) arrow.getLocation().getWorld().createExplosion(player, arrow.getLocation(), 0.7f, false, false);
    }

    @EventHandler
    public void arrowSpeedEvent(EntityShootBowEvent event) {

        if(!(event.getEntity() instanceof Player player)) return;

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
