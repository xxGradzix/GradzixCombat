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

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GigantysmOrbListener implements Listener {

    private static final Map<UUID, Long> playersWithGigantysmEffect = new HashMap<>();

    public static void removePlayerWithGigantysmEffect(Player player) {
        playersWithGigantysmEffect.remove(player.getUniqueId());
        resetAttributes(player);
    }

    public static void addPlayer(Player player, int durationSeconds, boolean superCharge) {
        playersWithGigantysmEffect.put(player.getUniqueId(), System.currentTimeMillis() + (1000L * durationSeconds));
        setAttributes(player, superCharge);

        Bukkit.getScheduler().runTaskLater(GradzixCombatSystem.plugin, () -> {
            if (player.isOnline()) {
                removePlayerWithGigantysmEffect(player);
            }
        }, durationSeconds * 20L);
    }

    @EventHandler
    public void join(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (playersWithGigantysmEffect.getOrDefault(player.getUniqueId(), 0L) < System.currentTimeMillis()) {
            resetAttributes(player);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        removePlayerWithGigantysmEffect(event.getPlayer());
    }

    private static final NamespacedKey SCALE_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_gigantysm_scale");
    private static final NamespacedKey HEALTH_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_gigantysm_health");
    private static final NamespacedKey STEP_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_gigantysm_step");
    private static final NamespacedKey REACH_BLOCK_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_gigantysm_reach_block");
    private static final NamespacedKey REACH_ENTITY_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_gigantysm_reach_entity");
    private static final NamespacedKey ATTACK_SPEED_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_gigantysm_attack_speed");
    private static final NamespacedKey SPEED_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_gigantysm_speed");
    private static final NamespacedKey JUMP_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_gigantysm_jump");
    private static final NamespacedKey KNOCK_BACK_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_gigantysm_fall");
    private static final NamespacedKey FALL_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_gigantysm_knockback");
    private static final NamespacedKey GRAVITY_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_gigantysm_gravity");


    private static void resetAttributes(Player player) {
        player.getAttribute(Attribute.GENERIC_SCALE).removeModifier(SCALE_KEY);
        player.getAttribute(Attribute.GENERIC_STEP_HEIGHT).removeModifier(STEP_KEY);
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).removeModifier(HEALTH_KEY);
        player.getAttribute(Attribute.PLAYER_BLOCK_INTERACTION_RANGE).removeModifier(REACH_BLOCK_KEY);
        player.getAttribute(Attribute.PLAYER_ENTITY_INTERACTION_RANGE).removeModifier(REACH_ENTITY_KEY);
        player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).removeModifier(SPEED_KEY);
        player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).removeModifier(ATTACK_SPEED_KEY);
        player.getAttribute(Attribute.GENERIC_JUMP_STRENGTH).removeModifier(JUMP_KEY);

        player.getAttribute(Attribute.GENERIC_GRAVITY).removeModifier(GRAVITY_KEY);
    }

    private static void setAttributes(Player player, boolean superCharge) {
        player.getAttribute(Attribute.GENERIC_SCALE).addModifier(new AttributeModifier(SCALE_KEY, (superCharge ? 1 : 0.5), AttributeModifier.Operation.MULTIPLY_SCALAR_1));
        player.getAttribute(Attribute.GENERIC_STEP_HEIGHT).addModifier(new AttributeModifier(STEP_KEY,  (superCharge ? 2 : 1.0), AttributeModifier.Operation.MULTIPLY_SCALAR_1));
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).addModifier(new AttributeModifier(HEALTH_KEY,  (superCharge ? 2 : 1), AttributeModifier.Operation.MULTIPLY_SCALAR_1));
        player.getAttribute(Attribute.PLAYER_BLOCK_INTERACTION_RANGE).addModifier(new AttributeModifier(REACH_BLOCK_KEY,  (superCharge ? 1 : 0.6), AttributeModifier.Operation.MULTIPLY_SCALAR_1));
        player.getAttribute(Attribute.PLAYER_ENTITY_INTERACTION_RANGE).addModifier(new AttributeModifier(REACH_ENTITY_KEY,  (superCharge ? 1 : 0.6), AttributeModifier.Operation.MULTIPLY_SCALAR_1));
        player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).addModifier(new AttributeModifier(SPEED_KEY,  (superCharge ? 0.1 : -0.1), AttributeModifier.Operation.MULTIPLY_SCALAR_1));
        player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).addModifier(new AttributeModifier(ATTACK_SPEED_KEY, -0.5, AttributeModifier.Operation.MULTIPLY_SCALAR_1));
        player.getAttribute(Attribute.GENERIC_JUMP_STRENGTH).addModifier(new AttributeModifier(JUMP_KEY,  (superCharge ? 0.5 : 0.2), AttributeModifier.Operation.MULTIPLY_SCALAR_1));
        player.getAttribute(Attribute.GENERIC_SAFE_FALL_DISTANCE).addModifier(new AttributeModifier(FALL_KEY,  2, AttributeModifier.Operation.MULTIPLY_SCALAR_1));
        player.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).addModifier(new AttributeModifier(KNOCK_BACK_KEY,  3, AttributeModifier.Operation.MULTIPLY_SCALAR_1));
        player.getAttribute(Attribute.GENERIC_GRAVITY).addModifier(new AttributeModifier(GRAVITY_KEY,  -0.2, AttributeModifier.Operation.MULTIPLY_SCALAR_1));
    }

}