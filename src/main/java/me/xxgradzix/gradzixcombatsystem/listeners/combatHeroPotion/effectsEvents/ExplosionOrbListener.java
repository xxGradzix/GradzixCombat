package me.xxgradzix.gradzixcombatsystem.listeners.combatHeroPotion.effectsEvents;

import me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager;
import me.xxgradzix.gradzixcombatsystem.managers.messages.MessageType;
import net.kyori.adventure.text.TextComponent;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ExplosionOrbListener implements Listener {

    private static final Map<UUID, Long> playersWithExplosionEffect = new HashMap<>();
    private static final int COOLDOWN_SECONDS = 5;


    public static void addPlayerWithExplosionEffect(UUID playerUUID, int durationSecconts) {
        playersWithExplosionEffect.put(playerUUID, System.currentTimeMillis() + (1000L * durationSecconts)); // 5 minut
    }
    public static void removePlayerWithExplosionEffect(UUID playerUUID) {
        playersWithExplosionEffect.remove(playerUUID);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        removePlayerWithExplosionEffect(event.getEntity().getUniqueId());
    }

//    private static HashMap<UUID, Integer> counter = new HashMap<>();


    private final Map<Player, Long> lastUseMap = new HashMap<>();

    private final Map<Player, Long> lastShiftMap = new HashMap<>();

    @EventHandler
    public void onPlayerInteract(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();

        if (playersWithExplosionEffect.getOrDefault(player.getUniqueId(), 0L) < System.currentTimeMillis()) return;

        if (lastShiftMap.containsKey(player)) {

            long lastShiftTime = lastShiftMap.get(player);

            if (System.currentTimeMillis() - lastShiftTime <= 300) { // Odstęp między kliknięciami - 300 ms

                if (!lastUseMap.containsKey(player) || isCooldownOver(player)) {

                    player.getWorld().createExplosion(player.getLocation().add(new Vector(0, 1.5, 0)), 1.5f, false, false, player);

                    lastUseMap.put(player, System.currentTimeMillis());
                } else {
                    long remainingCooldown = getRemainingCooldown(player);
                    MessageManager.sendMessageFormated(player, ChatColor.RED + "ᴍᴏżᴇꜱᴢ ᴜżʏć ᴘᴏɴᴏᴡɴɪᴇ ᴡʏʙᴜᴄʜᴜ ᴢᴀ: " + (remainingCooldown / 1000) + " ꜱᴇᴋᴜɴᴅ", MessageType.ACTIONBAR);
                }

            }
        }
        lastShiftMap.put(player, System.currentTimeMillis());
    }

//
//    @EventHandler
//    public void chargeExplosion(PlayerToggleSneakEvent event) {
////    public void chargeExplosion(PlayerInteractEvent event) {
//
//        Player player = event.getPlayer();
//
//        if (playersWithExplosionEffect.getOrDefault(player.getUniqueId(), 0L) < System.currentTimeMillis()) return;
//
////        boolean rightClick = event.getAction().equals(org.bukkit.event.block.Action.RIGHT_CLICK_AIR) || event.getAction().equals(org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK);
////        if(!rightClick) return;
//
//        int count = counter.getOrDefault(player.getUniqueId(), 0);
//
//
//        count++;
//
//
//        if(count == 3) {
//
//            player.getWorld().createExplosion(player.getLocation(), 1.5f, false, false, player);
//
//            count = 0;
//        }
//
//        counter.put(player.getUniqueId(), count);
//
//
//
//    }

    private boolean isCooldownOver(Player player) {
        long currentTime = System.currentTimeMillis();
        long lastUseTime = lastUseMap.getOrDefault(player, 0L);
        return currentTime - lastUseTime >= COOLDOWN_SECONDS * 1000; // 5 seconds in milliseconds
    }

    private long getRemainingCooldown(Player player) {
        long currentTime = System.currentTimeMillis();
        long lastUseTime = lastUseMap.getOrDefault(player, 0L);
        return COOLDOWN_SECONDS * 1000 - (currentTime - lastUseTime);
    }

//    @EventHandler
//    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
//
//        if (event.getEntity() instanceof Player) {
//            Player player = ((Player) event.getEntity()).getPlayer();
//
//            if (playersWithExplosionEffect.containsKey(player.getUniqueId())) {
//                player.getWorld().createExplosion(player.getLocation(), 1.5f, false, false, player);
//            }
//        }
//    }

}
