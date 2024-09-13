package me.xxgradzix.gradzixcombatsystem.listeners;

import com.destroystokyo.paper.event.entity.EntityKnockbackByEntityEvent;
import me.xxgradzix.gradzixcombatsystem.managers.modifiersManager.ModifiersManager;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class KnockBackListener implements Listener {


    @EventHandler
    public void onDamageEvent(EntityKnockbackByEntityEvent e){

        if (!(e.getPushedBy() instanceof Player damager)) return;

        ItemStack itemInMainHand = damager.getInventory().getItemInMainHand();

        double multiplierValue = ModifiersManager.getMultiplierValue(itemInMainHand, ModifiersManager.Multiplier.KNOCK_BACK_MULTIPLIER);

        e.getKnockback().multiply(multiplierValue);


    }
}
