package me.xxgradzix.gradzixcombatsystem.listeners.enchants;

import me.xxgradzix.gradzixcombatsystem.managers.EnchantManager.EnchantManager;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.AttributePointsManager;
import me.xxgradzix.gradzixcombatsystem.managers.magicEffects.MagicEffectManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

public class WindEnchantChargeListener implements Listener {


    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        chargeWind(event.getPlayer());
    }

    @EventHandler
    public void onRightClickPlayer(PlayerInteractAtEntityEvent event) {
        chargeWind(event.getPlayer());
    }

    private void chargeWind(Player player) {

        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        boolean hasRequiredAttribute = AttributePointsManager.hasRequiredAttribute(itemInMainHand, player);
        if(!hasRequiredAttribute) return;

        int enchantLevel = EnchantManager.getEnchantLevel(itemInMainHand, EnchantManager.Enchant.WIND_CHARGE);
        if(enchantLevel < 1) return;
        if(player.getCooldown(itemInMainHand.getType()) > 0) return;

//        MagicEffectManager.useWindEffect(MagicEffectManager.MagicUseVariant.ENCHANT, Optional.of(player), enchantLevel, false, Optional.empty(), Optional.empty());

        new MagicEffectManager.SpellBuilder().effectType(MagicEffectManager.MagicEffectType.WIND).useVariant(MagicEffectManager.MagicUseVariant.ENCHANT).caster(player).level(enchantLevel).superCharge(false).cast();

//        Vector direction  = player.getLocation().clone().add(0, 1.5, 0).getDirection();
//
//        player.setCooldown(itemInMainHand.getType(), 12/enchantLevel * 20);
//
//        player.launchProjectile(WindCharge.class, direction.multiply(2));

    }
}
