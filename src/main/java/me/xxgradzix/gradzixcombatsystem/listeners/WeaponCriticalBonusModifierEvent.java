package me.xxgradzix.gradzixcombatsystem.listeners;

import me.xxgradzix.gradzixcombatsystem.events.critEvent.CriticalHitEvent;
import me.xxgradzix.gradzixcombatsystem.managers.modifiersManager.ModifiersManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class WeaponCriticalBonusModifierEvent implements Listener {

    @EventHandler
    public void onPlayerHitEvent(CriticalHitEvent event) {

        Player player = event.getPlayer();

        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();

        double multiplierValue = ModifiersManager.getMultiplierValue(itemInMainHand, ModifiersManager.Multiplier.CRIT_CHANCE_MULTIPLIER);

        event.setCriticalChance(event.getCriticalChance() + multiplierValue);

    }


}
