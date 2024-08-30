package me.xxgradzix.gradzixcombatsystem.listeners;

import me.xxgradzix.gradzixcombatsystem.armorEvent.ArmorEquipEvent;
import me.xxgradzix.gradzixcombatsystem.managers.AttributeManager;
import me.xxgradzix.gradzixcombatsystem.managers.CombatAttribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class ArmorBlock implements Listener {

    @EventHandler
    public void elytraSwapBlock(ArmorEquipEvent event) {

        Player player = event.getPlayer();

        ItemStack newArmorPiece = event.getNewArmorPiece();
            int strLvl = AttributeManager.getAttributeLevel(player, CombatAttribute.STRENGTH);
            int dexLvl = AttributeManager.getAttributeLevel(player, CombatAttribute.DEXTERITY);

            int strReq = AttributeManager.getAttributeRequirement(newArmorPiece, CombatAttribute.STRENGTH);
            int dexReq = AttributeManager.getAttributeRequirement(newArmorPiece, CombatAttribute.DEXTERITY);

            if (strLvl < strReq || dexLvl < dexReq) {

                player.sendMessage("You don't have the required stats to wear this armor!");

                event.setCancelled(true);

            }
    }

}
