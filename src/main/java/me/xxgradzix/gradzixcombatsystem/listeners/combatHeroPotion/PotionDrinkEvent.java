package me.xxgradzix.gradzixcombatsystem.listeners.combatHeroPotion;

import me.xxgradzix.gradzixcombatsystem.events.cooldowns.CooldownApplyEvent;
import me.xxgradzix.gradzixcombatsystem.items.CustomItem;
import me.xxgradzix.gradzixcombatsystem.items.CustomItemManager;
import me.xxgradzix.gradzixcombatsystem.items.combatPotion.CustomBattlePotion;
import me.xxgradzix.gradzixcombatsystem.items.combatPotion.CustomBattlePotionOrb;
import me.xxgradzix.gradzixcombatsystem.items.weapons.Tierable;
import me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager;
import me.xxgradzix.gradzixcombatsystem.managers.messages.MessageType;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
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

        if(event.getPlayer().getCooldown(potion.getType()) > 0) {
            event.setCancelled(true);
            MessageManager.sendMessageFormated(event.getPlayer(),  "&cᴍᴜꜱɪꜱᴢ ᴏᴅᴄᴢᴇᴋᴀć " + MessageManager.secondsToTimeFormat(event.getPlayer().getCooldown(potion.getType())/20) + " ꜱᴇᴋᴜɴᴅ ᴘʀᴢᴇᴅ ᴘᴏɴᴏᴡɴʏᴍ ᴡʏᴘɪᴄɪᴇᴍ", MessageType.ACTIONBAR);
            return;
        }

        ItemStack potionReplacement = potion.clone();

        potionReplacement.setType(Material.GLASS_BOTTLE);

        int tier = Tierable.getTier(potion);

        int cooldown = customBattlePotion.getCooldownSecondsByTier(tier);


        CooldownApplyEvent cooldownApplyEvent = new CooldownApplyEvent(event.getPlayer(), potion, cooldown * 20);

        Bukkit.getPluginManager().callEvent(cooldownApplyEvent);

        if(!cooldownApplyEvent.isCancelled()) {
            if(!event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {

                cooldownApplyEvent.getPlayer().setCooldown(cooldownApplyEvent.getItem().getType(), cooldownApplyEvent.getCooldown());
            }
        }


        event.setReplacement(potionReplacement);

        HashMap<Integer, CustomBattlePotionOrb> orbs = customBattlePotion.getOrbs(potion.getItemMeta());

        for (CustomBattlePotionOrb customBattlePotionOrb : orbs.values()) customBattlePotionOrb.onDrink(event.getPlayer());



    }
}
