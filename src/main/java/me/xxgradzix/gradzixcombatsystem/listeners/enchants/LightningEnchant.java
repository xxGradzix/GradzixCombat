package me.xxgradzix.gradzixcombatsystem.listeners.enchants;

import me.xxgradzix.gradzixcombatsystem.managers.EnchantManager.EnchantManager;
import me.xxgradzix.gradzixcombatsystem.managers.magicEffects.MagicEffectManager;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;

import java.util.Optional;

public class LightningEnchant implements Listener {

    @EventHandler
    public void onArrowHit(ProjectileHitEvent event) {

        ProjectileSource shooter = event.getEntity().getShooter();

        if(!(shooter instanceof Player)) return;

        if(event.getEntity() instanceof Arrow arrow) {

            if(arrow.getWeapon() == null) return;
            ItemStack activeItem = arrow.getWeapon();
            int enchantLevel = EnchantManager.getEnchantLevel(activeItem, EnchantManager.Enchant.LIGHTNING);
            if(enchantLevel == 0) return;
//            if(((Player) shooter).getCooldown(activeItem.getType()) > 0) return;
//            int cooldown = 20 * (enchantLevel == 1 ? 16 : enchantLevel == 2 ? 12 : 8);
//            ((Player) shooter).setCooldown(activeItem.getType(), cooldown);


            Location location = event.getEntity().getLocation();

            MagicEffectManager.useLightningEffect(MagicEffectManager.MagicUseVariant.ENCHANT, Optional.of(((Player) shooter)), enchantLevel, false, Optional.empty(), Optional.empty(), location);

        }

        if(event.getEntity() instanceof Trident trident) {

            ItemStack itemStack = trident.getItemStack();
            int enchantLevel = EnchantManager.getEnchantLevel(itemStack, EnchantManager.Enchant.LIGHTNING);
            if(enchantLevel == 0) return;
            if(((Player) shooter).getCooldown(itemStack.getType()) > 0) return;



//            int cooldown = 20 * (enchantLevel == 1 ? 16 : enchantLevel == 2 ? 12 : 8);
//
//            ((Player) shooter).setCooldown(itemStack.getType(), cooldown);


            Location location = event.getEntity().getLocation();
            MagicEffectManager.useLightningEffect(MagicEffectManager.MagicUseVariant.ENCHANT, Optional.of(((Player) shooter)), enchantLevel, false, Optional.empty(), Optional.empty(), location);

        }
    }

}
