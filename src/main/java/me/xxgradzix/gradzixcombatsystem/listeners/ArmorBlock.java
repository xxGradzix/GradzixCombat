package me.xxgradzix.gradzixcombatsystem.listeners;

import me.xxgradzix.gradzixcombatsystem.armorEvent.ArmorEquipEvent;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.AttributeManager;
import me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager;
import me.xxgradzix.gradzixcombatsystem.managers.messages.MessageType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class ArmorBlock implements Listener {

    @EventHandler
    public void checkArmorRestriction(ArmorEquipEvent event) {

        Player player = event.getPlayer();

        ItemStack newArmorPiece = event.getNewArmorPiece();

            if (!AttributeManager.hasRequiredAttribute(newArmorPiece, player)) {
                MessageManager.sendMessageFormated(player, MessageManager.NOT_SUFFICIENT_ATTRIBUTES, MessageType.TITLE);
                event.setCancelled(true);
            }
    }
}
