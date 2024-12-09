package me.xxgradzix.gradzixcombatsystem.listeners.battleBottles;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.items.CustomItemManager;
import me.xxgradzix.gradzixcombatsystem.items.battleBottles.CombatPotionType;
import me.xxgradzix.gradzixcombatsystem.items.battleBottles.CustomBottle;
import me.xxgradzix.gradzixcombatsystem.items.weapons.Tierable;
import me.xxgradzix.gradzixcombatsystem.managers.magicEffects.MagicEffectManager;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.AreaEffectCloudApplyEvent;
import org.bukkit.event.entity.LingeringPotionSplashEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.projectiles.ProjectileSource;

import java.util.List;
import java.util.Optional;

public class BottleListener implements Listener {

    private static final NamespacedKey LINGERING_AREA_POTION_TYPE = new NamespacedKey(GradzixCombatSystem.plugin, "LINGERING_AREA_POTION_TYPE");
    private static final NamespacedKey LINGERING_AREA_POTION_TIER = new NamespacedKey(GradzixCombatSystem.plugin, "LINGERING_AREA_POTION_TIER");

    @EventHandler
    public void onThrow(ProjectileLaunchEvent event) {

        Projectile entity = event.getEntity();

        if(!(entity instanceof ThrownPotion)) return;

        entity.getVelocity().multiply(1.3);

        ProjectileSource shooter = entity.getShooter();

        if(!(shooter instanceof Player player)) return;

        int cooldown = player.getCooldown(Material.LINGERING_POTION);

        if(!player.getGameMode().equals(GameMode.CREATIVE)) {
            if(cooldown > 0) {
                event.setCancelled(true);
                return;
            }
        }

        player.setCooldown(Material.LINGERING_POTION, 200);


    }

    @EventHandler
    public void onPotionSplash(LingeringPotionSplashEvent event) {


        ThrownPotion thrownPotion = event.getEntity();

        ItemStack item = thrownPotion.getItem();

        CustomBottle bottle = CustomItemManager.getCustomPotionBottle(item);

        if(bottle == null) return;

        if(!(thrownPotion.getShooter() instanceof Player player)) return;

        CombatPotionType combatPotionType = bottle.getCombatPotionType();

        AreaEffectCloud areaEffectCloud = event.getAreaEffectCloud();

        Location center = areaEffectCloud.getLocation();

        float radius = 4F;

        int tier = Tierable.getTier(item);


        areaEffectCloud.setRadius(radius);
        areaEffectCloud.setDuration(200);
        areaEffectCloud.getPersistentDataContainer().set(LINGERING_AREA_POTION_TYPE, PersistentDataType.STRING, combatPotionType.name());
        areaEffectCloud.getPersistentDataContainer().set(LINGERING_AREA_POTION_TIER, PersistentDataType.INTEGER, tier);

        switch (combatPotionType) {
            case FREEZE -> {
//                MagicEffectManager.useFreezeEffect(MagicEffectManager.MagicUseVariant.BATTLE_BOTTLE, Optional.of(player), tier, false, Optional.of(areaEffectCloud), Optional.empty());

                new MagicEffectManager.SpellBuilder().effectType(MagicEffectManager.MagicEffectType.FREEZE).useVariant(MagicEffectManager.MagicUseVariant.BATTLE_BOTTLE).caster(player).level(tier).areaEffectCloud(areaEffectCloud).superCharge(false).cast();

            }
            case FIRE -> {
//                MagicEffectManager.useFireEffect(MagicEffectManager.MagicUseVariant.BATTLE_BOTTLE, Optional.of(player), tier, false, Optional.of(areaEffectCloud), Optional.empty());
                new MagicEffectManager.SpellBuilder().effectType(MagicEffectManager.MagicEffectType.FIRE).useVariant(MagicEffectManager.MagicUseVariant.BATTLE_BOTTLE).caster(player).level(tier).areaEffectCloud(areaEffectCloud).superCharge(false).cast();

//                tutaj
//                areaEffectCloud.setDuration(220);
//
//                areaEffectCloud.setParticle(Particle.SMALL_FLAME);
//                center.getWorld().spawnParticle(Particle.FLAME, center, 20, radius, radius, radius, 0.05);
//                center.getWorld().spawnParticle(Particle.LARGE_SMOKE, center, 20, radius, radius, radius, 0.05);
            }
            case WIND -> {
//                MagicEffectManager.useWindEffect(MagicEffectManager.MagicUseVariant.BATTLE_BOTTLE, Optional.of(player), tier, false, Optional.of(areaEffectCloud), Optional.empty());
                new MagicEffectManager.SpellBuilder().effectType(MagicEffectManager.MagicEffectType.WIND).useVariant(MagicEffectManager.MagicUseVariant.BATTLE_BOTTLE).caster(player).level(tier).areaEffectCloud(areaEffectCloud).superCharge(false).cast();

//
//                areaEffectCloud.setDuration(80);
//
//                areaEffectCloud.setParticle(Particle.CLOUD);
//                center.getWorld().spawnParticle(Particle.CLOUD, center, 20, radius, radius, radius, 0.05);
            }
            case POISON -> {
//                MagicEffectManager.usePoisonEffect(MagicEffectManager.MagicUseVariant.BATTLE_BOTTLE, Optional.of(player), tier, false, Optional.of(areaEffectCloud), Optional.empty());
                new MagicEffectManager.SpellBuilder().effectType(MagicEffectManager.MagicEffectType.POISON).useVariant(MagicEffectManager.MagicUseVariant.BATTLE_BOTTLE).caster(player).level(tier).areaEffectCloud(areaEffectCloud).superCharge(false).cast();

//                areaEffectCloud.setDuration(280);
//
////                areaEffectCloud.setColor(Color.GREEN);
//                areaEffectCloud.setParticle(new ParticleBuilder(Particle.EFFECT).color(Color.GREEN).particle());
////                areaEffectCloud.se
//                center.getWorld().spawnParticle(Particle.EFFECT, center, 20, radius, radius, radius, 0.05);
            }
            case EXPLOSION -> {
                event.setCancelled(true);
//                MagicEffectManager.useExplosionEffect(MagicEffectManager.MagicUseVariant.BATTLE_BOTTLE, Optional.of(player), tier, false, Optional.of(areaEffectCloud), Optional.empty());
                new MagicEffectManager.SpellBuilder().effectType(MagicEffectManager.MagicEffectType.EXPLOSION).useVariant(MagicEffectManager.MagicUseVariant.BATTLE_BOTTLE).caster(player).level(tier).areaEffectCloud(areaEffectCloud).superCharge(false).cast();

            }

        }

    }


    @EventHandler
    public void onPotionSplash(AreaEffectCloudApplyEvent event) {

        AreaEffectCloud entity = event.getEntity();

        if(!(entity.getSource() instanceof Player player)) return;

        int tier = entity.getPersistentDataContainer().getOrDefault(LINGERING_AREA_POTION_TIER, PersistentDataType.INTEGER, 0);
        if(tier == 0) return;

        String orDefault = entity.getPersistentDataContainer().getOrDefault(LINGERING_AREA_POTION_TYPE, PersistentDataType.STRING, "");

        CombatPotionType potionType;
        try {
            potionType = CombatPotionType.valueOf(orDefault);
        } catch (IllegalArgumentException exception) {
            return;
        }

        List<LivingEntity> affectedEntities = event.getAffectedEntities();
        CustomBottle bottle = CustomItemManager.getCustomPotionBottle(potionType);

        for (LivingEntity livingEntity : affectedEntities) {

            bottle.affectedEffect(player, livingEntity, tier);

        }




    }

}