package me.xxgradzix.gradzixcombatsystem.listeners.enchants;

import me.xxgradzix.gradzixcombatsystem.items.CustomItem;
import me.xxgradzix.gradzixcombatsystem.items.CustomItemManager;
import me.xxgradzix.gradzixcombatsystem.managers.EnchantManager.EnchantManager;
import me.xxgradzix.gradzixcombatsystem.managers.magicEffects.MagicEffectManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

public class SoulStealListener implements Listener {

//    private static final NamespacedKey SOULS_STOLEN_KEY = new NamespacedKey("gradzixcombatsystem", "souls_stealed");
//
//    public static int getSoulsStolen(ItemMeta itemMeta) {
//        if (itemMeta == null) return 0;
//        return itemMeta.getPersistentDataContainer().getOrDefault(SOULS_STOLEN_KEY, PersistentDataType.INTEGER, 0);
//    }

    @EventHandler
    public void onSoulSteal(EntityDamageByEntityEvent event) {

        if(!(event.getDamager() instanceof Player damager)) return;

        if(!(event.getEntity() instanceof Player damaged)) return;

        if(!event.isCritical()) return;

        ItemStack shield = damager.getInventory().getItemInOffHand();

        int enchantLevel = EnchantManager.getEnchantLevel(shield, EnchantManager.Enchant.SOUL_STEAL);
        if(enchantLevel == 0) return;

        CustomItem customItem = CustomItemManager.getCustomItem(shield);
        if(customItem == null) return;

        int soulsStolen = MagicEffectManager.getSoulsStolen(shield.getItemMeta());

        if(soulsStolen >= MagicEffectManager.getLimitByEnchantLevel(enchantLevel)) return;

        soulsStolen+=1;

        MagicEffectManager.setSoulsStolen(shield, soulsStolen);

        customItem.setLoreAndName(shield, 1);

    }

//    private static int getLimitByEnchantLevel(int enchantLevel) {
//        return switch (enchantLevel) {
//            case 1 -> 10;
//            case 2 -> 15;
//            case 3 -> 20;
//
//            default -> 0;
//        };
//    }

    @EventHandler
    public void onSoulSteal(PlayerInteractEvent event) {

        Player player = event.getPlayer();

//        MagicEffectManager.useSoulStealEffect(MagicEffectManager.MagicUseVariant.ENCHANT, Optional.of(player), 1, false, Optional.empty(), Optional.empty());

        new MagicEffectManager.SpellBuilder().effectType(MagicEffectManager.MagicEffectType.SOUL_STEAL).useVariant(MagicEffectManager.MagicUseVariant.ENCHANT).caster(player).level(1).superCharge(false).cast();


//        BukkitRunnable bukkitRunnable = new BukkitRunnable() {
//            @Override
//            public void run() {
//
//                if (soulsStolen.get() == 0 || !player.isBlocking() || !player.isSneaking() || player.isDead() || !player.isOnline()) {
//                    cancel();
//                    setSoulsStolen(shield, soulsStolen.get());
//                    customItem.setLoreAndName(shield, 1);
//                    return;
//                }
//
//                Location location = player.getLocation();
//
//                location.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, location, 20, 0.6, 0.6, 0.6, 0.05);
//                location.getWorld().spawnParticle(Particle.LARGE_SMOKE, location, 20, 0.6, 0.6, 0.6, 0.05);
//
//                for (Entity entity : player.getNearbyEntities(3, 3, 3)) {
//                    if (entity instanceof LivingEntity) {
//                        Vector direction = entity.getLocation().subtract(location).toVector().normalize().multiply(1.2);
//                        direction.setY(direction.getY() + 0.5);
//                        entity.setVelocity(direction);
//                    }
//                }
//                soulsStolen.set(soulsStolen.get() - 1);
//
//            }
//        };
//
//        bukkitRunnable.runTaskTimer(GradzixCombatSystem.plugin, 2, 20);


    }

//    private int getSoulsStolen(ItemStack itemStack) {
//        return itemStack.getItemMeta().getPersistentDataContainer().getOrDefault(SOULS_STOLEN_KEY, PersistentDataType.INTEGER, 0);
//
//    }
//
//    public static void setSoulsStolen(ItemStack itemStack, int value) {
//        ItemMeta itemMeta = itemStack.getItemMeta();
//        itemMeta.getPersistentDataContainer().set(SOULS_STOLEN_KEY, PersistentDataType.INTEGER, value);
//        itemStack.setItemMeta(itemMeta);
//    }


}
