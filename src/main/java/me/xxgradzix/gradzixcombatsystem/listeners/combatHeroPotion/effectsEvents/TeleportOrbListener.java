package me.xxgradzix.gradzixcombatsystem.listeners.combatHeroPotion.effectsEvents;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TeleportOrbListener implements Listener {


    private static final Map<UUID, Long> playerWithBackTeleportEffect = new HashMap<>();

    public static void addPlayerWithBackTeleportEffect(Player player, int durationSeconds) {
        playerWithBackTeleportEffect.put(player.getUniqueId(), System.currentTimeMillis() + (1000L * durationSeconds));
    }

    private final Map<Player, Long> lastUseMap = new HashMap<>();

    public static void removePlayerWithWithBackTeleportEffect(Player player) {
        playerWithBackTeleportEffect.remove(player.getUniqueId());
    }
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        removePlayerWithWithBackTeleportEffect(event.getEntity());
    }

    @EventHandler
    public void onEntityDamageByEntityTeleport(PlayerInteractAtEntityEvent event) {


        Entity damaged = event.getRightClicked();
        Player damager = event.getPlayer();

        if(!playerWithBackTeleportEffect.containsKey(damager.getUniqueId()))return;

        teleportPlayerBehind(damager, damaged);

    }

//    private void teleportPlayerBehind(Entity entity, Entity teleported) {
//        Vector playerToEntity = entity.getLocation().toVector().subtract(teleported.getLocation().toVector());
//        playerToEntity.setY(0).normalize().multiply(4); // Przesunięcie o 5 kratek w poziomie
//        Location loc = teleported.getLocation().add(playerToEntity);
//        loc.setPitch(0);
////        loc.setPitch(entity.getLocation().getPitch());
//        loc.setYaw(entity.getLocation().getYaw());
//        teleported.teleport(loc);
//    }
    private void teleportPlayerBehind(Entity entityToTeleport, Entity entity) {

        Location startLocation = entity.getLocation();

        startLocation.setDirection(entityToTeleport.getLocation().getDirection());
        startLocation.setPitch(0);


//        Vector directionBetweenLocations = getDirectionBetweenLocations(entityToTeleport.getLocation(), entity.getLocation()).normalize();
        Vector directionBetweenLocations = entityToTeleport.getLocation().getDirection().setY(0).normalize();
//        Vector direction = entity.getLocation().toVector().subtract(entityToTeleport.getLocation().toVector()).setY(0).normalize();


        Location targetLocation = startLocation.clone();
        double maxDistance = 3.0;

        for (double d = 0.5; d <= maxDistance; d += 0.5) {
            Location nextLocation = startLocation.clone().add(directionBetweenLocations.clone().multiply(d));
//            if (!nextLocation.getBlock().getType().equals(Material.AIR)) {
            if (!nextLocation.getBlock().isEmpty()) {
                Bukkit.broadcastMessage(nextLocation.getBlock().toString());
                Bukkit.broadcastMessage("nextLocation.getBlock().getType().isSolid()");
                break;
            }
            targetLocation = nextLocation;
        }
//        targetLocation = startLocation.clone().add(directionBetweenLocations.clone().multiply(maxDistance));

        float yaw = entityToTeleport.getYaw();
        if (yaw < 0) {
            yaw += 180;
        } else {
            yaw -= 180;
        }
        targetLocation.setPitch(entityToTeleport.getPitch());
        targetLocation.setYaw(yaw);
        entityToTeleport.teleport(targetLocation);
    }



    private Vector getDirectionBetweenLocations(Location start, Location end) {
        Vector from = start.toVector();
        Vector to = end.toVector();
        return to.subtract(from);
    }
}
