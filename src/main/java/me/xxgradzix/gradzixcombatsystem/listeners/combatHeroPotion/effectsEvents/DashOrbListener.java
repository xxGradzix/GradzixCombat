//package me.xxgradzix.gradzixcombatsystem.listeners.combatHeroPotion.effectsEvents;
//
//import net.md_5.bungee.api.ChatMessageType;
//import net.md_5.bungee.api.chat.TextComponent;
//import org.bukkit.ChatColor;
//import org.bukkit.entity.Player;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.Listener;
//import org.bukkit.event.block.Action;
//import org.bukkit.event.entity.PlayerDeathEvent;
//import org.bukkit.event.player.PlayerInteractEvent;
//import org.bukkit.util.Vector;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//public class DashOrbListener implements Listener {
//
//    private static Map<UUID, Long> playersWithDashEffect = new HashMap<>();
//
//
//    public static void addPlayerWithDashEffect(UUID playerUUID, int durationSecconts) {
//        playersWithDashEffect.put(playerUUID, System.currentTimeMillis() + (1000L * durationSecconts)); // 5 minut
//    }
//    public static void removePlayerWithDashEffect(UUID playerUUID) {
//        playersWithDashEffect.remove(playerUUID);
//    }
//
//    @EventHandler
//    public void onDeath(PlayerDeathEvent event) {
//        removePlayerWithDashEffect(event.getEntity().getUniqueId());
//    }
//
//    private Map<Player, Long> lastUseMap = new HashMap<>();
//
//    private Map<Player, Long> lastClickMap = new HashMap<>();
//
//    @EventHandler
//    public void onPlayerInteract(PlayerInteractEvent event) {
//        Player player = event.getPlayer();
//
//        if (playersWithDashEffect.getOrDefault(player.getUniqueId(), 0L) < System.currentTimeMillis()) return;
//
//        if (!(event.getAction().equals(Action.RIGHT_CLICK_AIR) ||event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) return;
//
//        if (lastClickMap.containsKey(player)) {
//            long lastClickTime = lastClickMap.get(player);
//
//            if (System.currentTimeMillis() - lastClickTime <= 300) { // Odstęp między kliknięciami - 300 ms
//                //
//                if (!lastUseMap.containsKey(player) || isCooldownOver(player)) {
//                    applyEffect(player);
//                    lastUseMap.put(player, System.currentTimeMillis());
//                } else {
//                    long remainingCooldown = getRemainingCooldown(player);
//                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + "Możesz to użyć ponownie za: " + (remainingCooldown / 1000) + " sekund"));
//
//                }
//                //
//            }
//        }
//        lastClickMap.put(player, System.currentTimeMillis());
//    }
//
//    private boolean isCooldownOver(Player player) {
//        long currentTime = System.currentTimeMillis();
//        long lastUseTime = lastUseMap.getOrDefault(player, 0L);
//        return currentTime - lastUseTime >= 5000; // 5 sekund w milisekundach
//    }
//
//    private long getRemainingCooldown(Player player) {
//        long currentTime = System.currentTimeMillis();
//        long lastUseTime = lastUseMap.getOrDefault(player, 0L);
//        return 5000 - (currentTime - lastUseTime);
//    }
//
//
//    private void applyEffect(Player player) {
//        Vector movementVector = player.getLocation().getDirection().setY(0).normalize().multiply(2);
//
//        if (player.isSprinting()) movementVector = movementVector.multiply(0.5);
//
//        player.setVelocity(movementVector);
//    }
//
//}


package me.xxgradzix.gradzixcombatsystem.listeners.combatHeroPotion.effectsEvents;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DashOrbListener implements Listener {

    private static final Map<UUID, Long> playersWithDashEffect = new HashMap<>();

    public static void addPlayerWithDashEffect(Player player, int durationSeconds) {
        playersWithDashEffect.put(player.getUniqueId(), System.currentTimeMillis() + (1000L * durationSeconds));
        player.setAllowFlight(true);

    }

    private final Map<Player, Long> lastUseMap = new HashMap<>();
//    private Map<Player, Long> lastRightClickMap = new HashMap<>();
//    private Map<Player, Long> lastLeftClickMap = new HashMap<>();

    public static void removePlayerWithDashEffect(Player player) {
        playersWithDashEffect.remove(player.getUniqueId());
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        removePlayerWithDashEffect(event.getPlayer());
        event.getPlayer().setAllowFlight(false);
    }

    @EventHandler
    public void toggleFlight(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();

        if(GameMode.CREATIVE.equals(player.getGameMode())) return;
        if (playersWithDashEffect.getOrDefault(player.getUniqueId(), 0L) < System.currentTimeMillis()) {
            player.setAllowFlight(false);
            return;
        }
        event.setCancelled(true);

        long currentTime = System.currentTimeMillis();

        if (isCooldownOver(player)) {
            applyEffect(player);
            lastUseMap.put(player, currentTime);
        } else {
            long remainingCooldown = getRemainingCooldown(player);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + "Możesz to użyć ponownie za: " + (remainingCooldown / 1000) + " sekund"));
        }


    }

//
//    @EventHandler
//    public void onPlayerInteract(PlayerInteractEvent event) {
//        Player player = event.getPlayer();
//
//        if (playersWithDashEffect.getOrDefault(player.getUniqueId(), 0L) < System.currentTimeMillis()) return;
//
//        Action action = event.getAction();
//        long currentTime = System.currentTimeMillis();
//
//        if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
//            lastRightClickMap.put(player, currentTime);
//        } else if (action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
//            lastLeftClickMap.put(player, currentTime);
//        }
//
//        if (lastRightClickMap.containsKey(player) && lastLeftClickMap.containsKey(player)) {
//            long lastRightClickTime = lastRightClickMap.get(player);
//            long lastLeftClickTime = lastLeftClickMap.get(player);
//
//            if (Math.abs(lastRightClickTime - lastLeftClickTime) <= 300) { // 300 ms window
//                if (!lastUseMap.containsKey(player) || isCooldownOver(player)) {
//                    applyEffect(player);
//                    lastUseMap.put(player, currentTime);
//                } else {
//                    long remainingCooldown = getRemainingCooldown(player);
//                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + "Możesz to użyć ponownie za: " + (remainingCooldown / 1000) + " sekund"));
//                }
//            }
//        }
//    }

    private boolean isCooldownOver(Player player) {
        long currentTime = System.currentTimeMillis();
        long lastUseTime = lastUseMap.getOrDefault(player, 0L);
        return currentTime - lastUseTime >= 5000; // 5 seconds in milliseconds
    }

    private long getRemainingCooldown(Player player) {
        long currentTime = System.currentTimeMillis();
        long lastUseTime = lastUseMap.getOrDefault(player, 0L);
        return 5000 - (currentTime - lastUseTime);
    }

    private void applyEffect(Player player) {
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_WIND_CHARGE_WIND_BURST, 1, 0.6f);
        player.getWorld().spawnParticle(Particle.CLOUD, player.getLocation(), 40, 0.5, 0.5, 0.5, 0.1);
        Vector movementVector = player.getLocation().getDirection().multiply(1.5);

//        if (player.isSprinting()) movementVector = movementVector.multiply(0.5);

        player.setVelocity(movementVector);
    }
}