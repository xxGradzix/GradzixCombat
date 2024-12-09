package me.xxgradzix.gradzixcombatsystem.listeners;

import me.xxgradzix.gradzixcombatsystem.events.critEvent.CriticalHitEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class CheckCriticalHitListener implements Listener {

    @EventHandler
    public void onPlayerHitEvent(EntityDamageByEntityEvent event) {

        if(!(event.getDamager() instanceof Player player)) return;


        CriticalHitEvent criticalHitEvent = new CriticalHitEvent(player);
        Bukkit.getPluginManager().callEvent(criticalHitEvent);

        if(criticalHitEvent.isCanceled()) return;

        boolean isCritical = criticalHitEvent.isOverrideIsCritical() || Math.random() < criticalHitEvent.getCriticalChance();

        if(!isCritical) return;

        event.setDamage(event.getDamage() * 2);

    }


}
