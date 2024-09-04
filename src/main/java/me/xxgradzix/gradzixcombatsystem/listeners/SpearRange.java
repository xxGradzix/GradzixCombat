package me.xxgradzix.gradzixcombatsystem.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpearRange implements Listener {

     /** WORKS WELL**/
//    @EventHandler
//    public void onPlayerInteract(PlayerInteractEvent event) {
//        // Sprawdzamy czy kliknięcie było lewym przyciskiem myszy
//        if (event.getAction().toString().contains("LEFT_CLICK")) {
//            Player player = event.getPlayer();
//            Location playerLocation = player.getLocation();
//            Vector direction = playerLocation.getDirection(); // kierunek, w którym gracz patrzy
//
//            // Wyświetlamy lokalizacje co 1 blok w odległości 4 bloków
//            for (int i = 1; i <= 4; i++) {
//                Vector directionClone = direction.clone().normalize().multiply(i); // Normalizujemy wektor i przemnażamy przez odległość
//                Location targetLocation = playerLocation.clone().add(directionClone); // Obliczamy docelową lokalizację
//                player.sendMessage("Lokalizacja " + i + ": " + formatLocation(targetLocation));
//            }
//        }
//    }
//
//    // Metoda pomocnicza do formatowania lokalizacji
//    private String formatLocation(Location location) {
//        return "x: " + location.getBlockX() + ", y: " + location.getBlockY() + ", z: " + location.getBlockZ();
//    }

        @EventHandler
        public void onPlayerInteract(EntityEvent event) {

            Bukkit.broadcastMessage(event.getEntity().getName());
            Bukkit.broadcastMessage(event.getTarget() == null ? "null" : event.getTarget().getName());
//            // Sprawdzamy, czy kliknięcie było lewym przyciskiem myszy
//            Bukkit.broadcastMessage("Event: " + event.getAction().toString());
//            if (event.getAction().toString().contains("LEFT_CLICK") || event.getAction().equals(Action.PHYSICAL)) {
//                Player player = event.getPlayer();
//                Location playerLocation = player.getLocation();
//                Vector direction = playerLocation.getDirection(); // kierunek, w którym gracz patrzy
//
//                // Przeszukujemy lokalizacje co 1 blok w odległości 4 bloków
//                for (int i = 1; i <= 4; i++) {
//                    Vector directionClone = direction.clone().normalize().multiply(i); // Normalizujemy wektor i przemnażamy przez odległość
//                    Location targetLocation = playerLocation.clone().add(directionClone); // Obliczamy docelową lokalizację
//
//                    // Sprawdzamy, czy jest mob w tej lokalizacji
//                    World world = player.getWorld();
//                    List<Entity> nearbyEntities = (List<Entity>) world.getNearbyEntities(targetLocation, 0.5, 0.5, 0.5); // Sprawdzamy w promieniu 0.5 bloku
//
//                    for (Entity entity : nearbyEntities) {
//                        if (entity instanceof LivingEntity && !entity.equals(player)) {
//                            LivingEntity mob = (LivingEntity) entity;
//                            mob.damage(5.0); // Zadajemy obrażenia mobowi
//                            player.sendMessage("Zadano obrażenia mobowi na pozycji: " + formatLocation(mob.getLocation()));
//                            return; // Przerywamy po znalezieniu pierwszego moba
//                        }
//                    }
//
//                    player.sendMessage("Lokalizacja " + i + ": " + formatLocation(targetLocation));
//                }
//            }
        }

        // Metoda pomocnicza do formatowania lokalizacji
        private String formatLocation(Location location) {
            return "x: " + location.getBlockX() + ", y: " + location.getBlockY() + ", z: " + location.getBlockZ();
        }


}
