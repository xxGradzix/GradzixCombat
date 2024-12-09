package me.xxgradzix.gradzixcombatsystem.listeners;

import com.destroystokyo.paper.event.player.PlayerRecipeBookClickEvent;
import me.xxgradzix.gradzixcombatsystem.guis.attributes.AttributesGuiManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class RecipeOpenEvent implements Listener {


    @EventHandler
    public void recipeOpenEvent(PlayerRecipeBookClickEvent event) {
        event.setCancelled(true);

        Player player = event.getPlayer();

        new AttributesGuiManager(player);
    }

}
