package me.xxgradzix.gradzixcombatsystem.listeners.combatHeroPotion;

import me.xxgradzix.gradzixcombatsystem.items.CustomItem;
import me.xxgradzix.gradzixcombatsystem.items.CustomItemManager;
import me.xxgradzix.gradzixcombatsystem.items.combatPotion.CustomBattlePotion;
import me.xxgradzix.gradzixcombatsystem.items.combatPotion.CustomBattlePotionOrb;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class PotionDrinkEvent implements Listener {


    @EventHandler
    public void potionDrink(PlayerItemConsumeEvent event) {
        ItemStack potion = event.getItem();
        CustomItem customItem = CustomItemManager.getCustomItem(potion);
        if(!(customItem instanceof CustomBattlePotion customBattlePotion)) return;
        ItemStack potionReplacement = potion.clone();
        potionReplacement.setType(Material.GLASS_BOTTLE);

//        potionReplacement.setItemMeta(potion.getItemMeta());
        event.setReplacement(potionReplacement);

        HashMap<Integer, CustomBattlePotionOrb> orbs = customBattlePotion.getOrbs(potion.getItemMeta());

        for (CustomBattlePotionOrb customBattlePotionOrb : orbs.values()) customBattlePotionOrb.onDrink(event.getPlayer());



    }
}
