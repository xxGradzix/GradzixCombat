package me.xxgradzix.gradzixcombatsystem.listeners.enchants;

import me.xxgradzix.gradzixcombatsystem.managers.EnchantManager.EnchantManager;
import me.xxgradzix.gradzixcombatsystem.managers.magicEffects.MagicEffectManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

public class AttackComboListener implements Listener {

//
//    class ComboData {
//
//        private int hitCount;
//        private long lastHit;
//        void setHitCount(int hitCount) {
//            this.hitCount = hitCount;
//        }
//        void setLastHit(long lastHit) {
//            this.lastHit = lastHit;
//        }
//        ComboData(int hitCount, long lastHit) {
//            this.hitCount = hitCount;
//            this.lastHit = lastHit;
//        }
//        int getHitCount() {
//            return hitCount;
//        }
//        long getLastHit() {
//            return lastHit;
//        }
//    };
//
//    private static final HashMap<UUID, ComboData> playerCombos = new HashMap<>();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamage(EntityDamageByEntityEvent event) {

        if(event.getEntity() instanceof Player damaged) MagicEffectManager.removePlayerFromCombo(damaged);
        Player damaged = (Player) event.getEntity();
        if (event.isCancelled()) return;
        if (!(event.getDamager() instanceof Player damager)) return;
        ItemStack itemInMainHand = damager.getInventory().getItemInMainHand();
        int enchantLevel = EnchantManager.getEnchantLevel(itemInMainHand, EnchantManager.Enchant.ATTACK_COMBO);
        if (enchantLevel == 0) return;

        double baseMultiplier = MagicEffectManager.useComboEffect(MagicEffectManager.MagicUseVariant.ENCHANT, Optional.of(damager), enchantLevel, false, Optional.empty(), Optional.of(damaged));

        event.setDamage(event.getDamage() * baseMultiplier);



//
//
//        ComboData combo = playerCombos.getOrDefault(damager.getUniqueId(), new ComboData(1, System.currentTimeMillis()));
//
//        if (System.currentTimeMillis() - combo.getLastHit() > 4000) {
//            combo.setHitCount(1);
//            combo.setLastHit(System.currentTimeMillis());
//        } else {
//            combo.setHitCount(combo.getHitCount() + 1);
//            combo.setLastHit(System.currentTimeMillis());
//        }
//        playerCombos.put(damager.getUniqueId(), combo);
//
//        double baseMultiplier = 1.0;
//        switch (enchantLevel) {
//
//            case 1 -> {
//
//                switch (combo.getHitCount()) {
//                    case 1 -> baseMultiplier = 1.0;
//                    case 2 -> baseMultiplier = 1.07;
//                    case 3 -> baseMultiplier = 1.14;
//                    case 4 -> baseMultiplier = 1.21;
//                    default -> baseMultiplier = 1.28;
//                }
//            }
//            case 2 -> {
//
//                switch (combo.getHitCount()) {
//                    case 1 -> baseMultiplier = 1.0;
//                    case 2 -> baseMultiplier = 1.09;
//                    case 3 -> baseMultiplier = 1.18;
//                    case 4 -> baseMultiplier = 1.27;
//                    default -> baseMultiplier = 1.36;
//                }
//            }
//            case 3 -> {
//
//                switch (combo.getHitCount()) {
//                    case 1 -> baseMultiplier = 1.0;
//                    case 2 -> baseMultiplier = 1.11;
//                    case 3 -> baseMultiplier = 1.22;
//                    case 4 -> baseMultiplier = 1.33;
//                    default -> baseMultiplier = 1.44;
//                }
//            }
//        }

//        event.setDamage(event.getDamage() * baseMultiplier);

    }

}
