package me.xxgradzix.gradzixcombatsystem.managers.magicEffects;

import com.destroystokyo.paper.ParticleBuilder;
import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.items.CustomItem;
import me.xxgradzix.gradzixcombatsystem.items.CustomItemManager;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class MagicEffectManager {

    private static final Random random = new Random();

    private static final HashMap<UUID, HashMap<MagicEffectType, Long>> superCharges = new HashMap<>();

    public static void superChargePlayer(Player player, int timeSeconds, Set<MagicEffectType> magicEffectTypes) {

        long deadline = System.currentTimeMillis() + (1000L * timeSeconds);

        HashMap<MagicEffectType, Long> charges = superCharges.getOrDefault(player.getUniqueId(), new HashMap<>());
        for(MagicEffectType magicEffectType : magicEffectTypes) charges.put(magicEffectType, deadline);

        superCharges.put(player.getUniqueId(), charges);
    }

    public enum MagicUseVariant {
        BATTLE_BOTTLE,
        BATTLE_BOTTLE_APPLY,
        ORB,
        ENCHANT,
        ARROW
    }
    public enum MagicEffectType {
        ARROW_RAIN,
        COMBO,
        FIRE,
        FREEZE,
        GREED,
        LIFE_STEAL,
        LIGHTNING,
        MULTI_SHOT,
        POISON,
        SOUL_STEAL,
        WIND,
        EXPLOSION,
    }

    private static boolean isSuperCharged(Player player, MagicEffectType magicEffectType) {
        return superCharges.getOrDefault(player.getUniqueId(), new HashMap<>()).getOrDefault(magicEffectType, 0L) > System.currentTimeMillis();
    }

    public static class SpellBuilder {
        private MagicEffectType effectType = null;
        private MagicEffectManager.MagicUseVariant useVariant = null;
        private boolean superCharge = false;
        private int level = 1;
        private AreaEffectCloud areaEffectCloud = null;
        private LivingEntity target = null;
        private Player caster = null;
        private Arrow arrow = null;
        private Location hitLocation = null;

        public SpellBuilder effectType(MagicEffectType effectType) {
            this.effectType = effectType;
            return this;
        }
        public SpellBuilder useVariant(MagicEffectManager.MagicUseVariant useVariant) {
            this.useVariant = useVariant;
            return this;
        }
        public SpellBuilder superCharge(boolean superCharge) {
            this.superCharge = superCharge;
            return this;
        }
        public SpellBuilder level(int level) {
            this.level = level;
            return this;
        }
        public SpellBuilder areaEffectCloud(AreaEffectCloud areaEffectCloud) {
            this.areaEffectCloud = areaEffectCloud;
            return this;
        }
        public SpellBuilder target(LivingEntity target) {
            this.target = target;
            return this;
        }
        public SpellBuilder caster(Player caster) {
            this.caster = caster;
            return this;
        }
        public SpellBuilder arrow(Arrow arrow) {
            this.arrow = arrow;
            return this;
        }
        public SpellBuilder location(Location location) {
            this.hitLocation = location;
            return this;
        }
        public Object cast() {
            if(effectType == null || useVariant == null) return null;
            if(caster != null) {
                if(isSuperCharged(caster, MagicEffectType.FIRE)) superCharge = true;
            }
            return castMagic(Optional.ofNullable(caster), effectType, useVariant, superCharge, level, Optional.ofNullable(areaEffectCloud), Optional.ofNullable(target), Optional.ofNullable(arrow), Optional.ofNullable(hitLocation));
        }

    }

    private static Object castMagic(Optional<Player> optionalCaster, MagicEffectType effectType, MagicEffectManager.MagicUseVariant useVariant, boolean superCharge, int level, Optional<AreaEffectCloud> optionalAreaEffectCloud, Optional<LivingEntity> optionalTarget, Optional<Arrow> arrow, Optional<Location> hitLocation) {

        switch (effectType) {

            case WIND -> {
                useWindEffect(useVariant, optionalCaster, level, superCharge, optionalAreaEffectCloud, optionalTarget);
            }
            case FIRE -> {
                useFireEffect(useVariant, optionalCaster, level, superCharge, optionalAreaEffectCloud, optionalTarget);
            }
            case FREEZE -> {
                useFreezeEffect(useVariant, optionalCaster, level, superCharge, optionalAreaEffectCloud, optionalTarget);
            }
            case POISON -> {
                usePoisonEffect(useVariant, optionalCaster, level, superCharge, optionalAreaEffectCloud, optionalTarget);
            }
            case EXPLOSION -> {
                useExplosionEffect(useVariant, optionalCaster, level, superCharge, optionalAreaEffectCloud, optionalTarget);
            }
            case ARROW_RAIN -> {
                useArrowRainEffect(useVariant, optionalCaster, level, superCharge, optionalAreaEffectCloud, optionalTarget, arrow);
            }
            case LIFE_STEAL -> {
                useLifeStealEffect(useVariant, optionalCaster, level, superCharge, optionalAreaEffectCloud, optionalTarget);
            }
            case COMBO -> {
                return useComboEffect(useVariant, optionalCaster, level, superCharge, optionalAreaEffectCloud, optionalTarget);
            }
            case LIGHTNING -> {
                useLightningEffect(useVariant, optionalCaster, level, superCharge, optionalAreaEffectCloud, optionalTarget, hitLocation);
            }
            case SOUL_STEAL -> {
                useSoulStealEffect(useVariant, optionalCaster, level, superCharge, optionalAreaEffectCloud, optionalTarget);
            }
        }
        return true;

    }


    private static void useFireEffect(MagicEffectManager.MagicUseVariant variant, Optional<Player> optionalCaster, int level, boolean superCharged, Optional<AreaEffectCloud> optionalAreaEffectCloud, Optional<LivingEntity> optionalTarget) {

        boolean finalSuperCharged = superCharged;
        switch (variant) {
            case BATTLE_BOTTLE -> {

                if (optionalAreaEffectCloud.isEmpty()) return;

                AreaEffectCloud areaEffectCloud = optionalAreaEffectCloud.get();

                areaEffectCloud.setParticle(Particle.SMALL_FLAME);

                Location center = areaEffectCloud.getLocation();

                float radius = 3;

                if (superCharged) radius += 1;
                if (level >= 2) radius += 1;

                areaEffectCloud.setRadius(radius);
                areaEffectCloud.setDuration((int) (level * 20 * 2 * (superCharged ? 1.5 : 1)));

                center.getWorld().spawnParticle(Particle.FLAME, center, 20, radius / 2, radius / 4, radius / 2, 0.05);
                center.getWorld().spawnParticle(Particle.LARGE_SMOKE, center, 20, radius / 2, radius / 4, radius / 2, 0.05);

            }
            case BATTLE_BOTTLE_APPLY -> {
                if (optionalTarget.isEmpty()) return;

                LivingEntity target = optionalTarget.get();

                int fireTicks = level * 40;

                if (superCharged) fireTicks += 20;

                target.setFireTicks(fireTicks);
            }
            case ENCHANT -> {

                if (optionalCaster.isEmpty()) return;

                Player player = optionalCaster.get();

                Location location = player.getLocation().add(0, 1.5, 0);
                location.add(location.getDirection().multiply(1.5));

                int radius = level >= 2 ? 75 : 55;
                if (superCharged) radius = 180;

                final Vector playerAxis = new Vector(0, 1, 0).normalize();

                Vector direction = location.clone().getDirection();

                Vector axisToRotate = new Vector(direction.getZ(), 0, -direction.getX());

                double angle = Math.acos(direction.dot(playerAxis) / (direction.length() * playerAxis.length()));

                Vector perpendicularVerticalAxis = playerAxis.clone().rotateAroundAxis(axisToRotate, Math.toRadians(Math.toDegrees(angle) - 90)).normalize();

                int fireRadius = level >= 2 ? 4 : 3;

                if (superCharged) fireRadius += 1;

                for (int i = 0; i < radius; i += 10) {

                    Vector rotated1 = direction.clone().rotateAroundAxis(perpendicularVerticalAxis, Math.toRadians(i));
                    Vector rotated2 = direction.clone().rotateAroundAxis(perpendicularVerticalAxis, Math.toRadians(-i));

                    Location start1 = location.clone().add(rotated1.clone().multiply(0.7));
                    Location start2 = location.clone().add(rotated2.clone().multiply(0.7));

                    Vector endVector1 = rotated1.clone().multiply(fireRadius);
                    Vector endVector2 = rotated2.clone().multiply(fireRadius);

                    Location end1 = location.clone().add(endVector1);
                    Location end2 = location.clone().add(endVector2);

                    end1.getNearbyEntities(fireRadius, 0.5, fireRadius).forEach(entity -> {

                        if (entity instanceof LivingEntity player1) {
                            if (player1 != player) {

                                int fireTicks = level * 2 * 20;

                                if (finalSuperCharged) fireTicks *= 2;

                                player1.setFireTicks(fireTicks);
                            }
                        }
                    });

                    end2.getNearbyEntities(fireRadius, 0.5, fireRadius).forEach(entity -> {
                        if (entity instanceof LivingEntity player1) {
                            if (player1 != player) {

                                int fireTicks = level * 2 * 20;

                                if (finalSuperCharged) fireTicks *= 2;

                                player1.setFireTicks(fireTicks);
                            }
                        }
                    });

                    player.spawnParticle(Particle.FLAME, start1, 0, endVector1.getX(), endVector1.getY(), endVector1.getZ(), 0.1);
                    player.spawnParticle(Particle.FLAME, start2, 0, endVector2.getX(), endVector2.getY(), endVector2.getZ(), 0.1);

                    player.spawnParticle(Particle.SMALL_FLAME, end1, 65, (double)fireRadius / 4, 0.5, (double)fireRadius / 4, 0.04);
                    player.spawnParticle(Particle.SMALL_FLAME, end2, 65, (double)fireRadius / 4, 0.5, (double)fireRadius / 4, 0.04);

                    player.getWorld().playSound(location, Sound.ENTITY_BLAZE_SHOOT, 0.6f, 0.4f);

                    player.setCooldown(player.getInventory().getItemInMainHand().getType(), 20 * (superCharged ? 5 : 7));

                }
            }
        }

    }


    private static void useFreezeEffect(MagicEffectManager.MagicUseVariant variant, Optional<Player> optionalCaster, int level, boolean superCharged, Optional<AreaEffectCloud> optionalAreaEffectCloud, Optional<LivingEntity> optionalTarget) {

        switch (variant) {
            case BATTLE_BOTTLE -> {

                if(optionalAreaEffectCloud.isEmpty()) return;

                AreaEffectCloud areaEffectCloud = optionalAreaEffectCloud.get();

                Location center = areaEffectCloud.getLocation();

                float radius = 3;

                if(superCharged) radius += 1;
                if(level >= 2) radius += 1;

                areaEffectCloud.setRadius(radius);
                areaEffectCloud.setDuration((int) (level * 20 * 3 * (superCharged ? 1.5 : 1)));

                areaEffectCloud.setDuration(320);
                areaEffectCloud.setParticle(Particle.SNOWFLAKE);
                center.getWorld().spawnParticle(Particle.SNOWFLAKE, center, 20, 0.6, 0.6, 0.6, 0.05);
            }
            case BATTLE_BOTTLE_APPLY -> {
                if(optionalTarget.isEmpty()) return;
                LivingEntity target = optionalTarget.get();

                int ticksToAdd = switch (level) {
                    case 1 -> 85;
                    case 2 -> 100;
                    case 3 -> 115;
                    default -> 0;
                };

                if(superCharged) ticksToAdd += 45;

                int previousTicks = target.getFreezeTicks();

                int newTicks = previousTicks + ticksToAdd;
                if(newTicks > 900) {
                    newTicks = 900;
                }

                target.setFreezeTicks(newTicks);

                target.getLocation().getWorld().spawnParticle(Particle.SNOWFLAKE, target.getLocation(), 15, 0.6, 0.6, 0.6, 0.05);

            }
            case ENCHANT -> {

                if(optionalTarget.isEmpty()) return;
                LivingEntity target = optionalTarget.get();

                int ticksToAdd = switch (level) {
                    case 1 -> random.nextInt(35, 50);
                    case 2 -> random.nextInt(45, 70);
                    case 3 -> random.nextInt(55, 90);
                    default -> 0;
                };

                int previousTicks = target.getFreezeTicks();
                if(superCharged) ticksToAdd += 25;


                int newTicks = previousTicks + ticksToAdd;
                if(newTicks > 900) {
                    newTicks = 900;
                }

                target.setFreezeTicks(newTicks);

                target.getLocation().getWorld().spawnParticle(Particle.SNOWFLAKE, target.getLocation(), 15, 0.6, 0.6, 0.6, 0.05);

            }
        }

    }


    private static void usePoisonEffect(MagicEffectManager.MagicUseVariant variant, Optional<Player> optionalCaster, int level, boolean superCharged, Optional<AreaEffectCloud> optionalAreaEffectCloud, Optional<LivingEntity> optionalTarget) {

        switch (variant) {
            case BATTLE_BOTTLE -> {

                if(optionalAreaEffectCloud.isEmpty()) return;
                AreaEffectCloud areaEffectCloud = optionalAreaEffectCloud.get();
                Location center = areaEffectCloud.getLocation();

                float radius = 3;
                if(superCharged) radius += 1;
                if(level >= 2) radius += 1;
                areaEffectCloud.setRadius(radius);
                areaEffectCloud.setDuration((int) (level * 20 * 2 * (superCharged ? 1.5 : 1)));

                areaEffectCloud.setParticle(new ParticleBuilder(Particle.DUST).color(Color.GREEN).particle());
                center.getWorld().spawnParticle(Particle.DUST, center, 20, radius, radius, radius, 0.05);
            }
            case BATTLE_BOTTLE_APPLY -> {
                if(optionalTarget.isEmpty()) return;
                LivingEntity target = optionalTarget.get();

                target.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20 * 5, 1));
                if(level >= 2) {
                    target.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 20 * 2, 2));
                }
                if(superCharged) target.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20 * 5, 1));

            }
            case ENCHANT -> {

                if(optionalTarget.isEmpty()) return;

                LivingEntity target = optionalTarget.get();

                double chance = getPoisonChance(level);

                if(random.nextDouble() > chance || superCharged) {

                    target.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20 * 5, 1));
                    if(level >= 2) {
                        target.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 20 * 2, 2));
                    }
                    if(superCharged) target.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20 * 5, 1));

                }

            }
        }
    }


    private static void useWindEffect(MagicEffectManager.MagicUseVariant variant, Optional<Player> optionalCaster, int level, boolean superCharged, Optional<AreaEffectCloud> optionalAreaEffectCloud, Optional<LivingEntity> optionalTarget) {

        switch (variant) {
            case BATTLE_BOTTLE -> {

                if(optionalAreaEffectCloud.isEmpty()) return;
                AreaEffectCloud areaEffectCloud = optionalAreaEffectCloud.get();
                Location center = areaEffectCloud.getLocation();

                float radius = 3;
                if(superCharged) radius += 1;
                if(level >= 2) radius += 1;
                areaEffectCloud.setRadius(radius);
                areaEffectCloud.setDuration((int) (level * 20 * 2 * (superCharged ? 1.5 : 1)));


                areaEffectCloud.setParticle(Particle.CLOUD);
                center.getWorld().spawnParticle(Particle.CLOUD, center, 20, radius, radius, radius, 0.05);
            }
            case BATTLE_BOTTLE_APPLY -> {
                if(optionalTarget.isEmpty()) return;
                LivingEntity target = optionalTarget.get();
                target.setVelocity(new Vector(0, 1.6, 0));
            }
            case ENCHANT -> {

                if(optionalCaster.isEmpty()) return;
                Player player = optionalCaster.get();

                ItemStack itemInMainHand = player.getInventory().getItemInMainHand();

                Vector direction  = player.getLocation().clone().add(0, 1.5, 0).getDirection();

                player.setCooldown(itemInMainHand.getType(), 12/level * (20 * (superCharged ? 2 : 1)));

                player.launchProjectile(WindCharge.class, direction.multiply(2 * (superCharged ? 1.5 : 1)));

            }
        }

    }

    private static double getPoisonChance(int poisonLevel) {

        return switch (poisonLevel) {
            case 1 -> 0.20;
            case 2 -> 0.25;
            case 3 -> 0.30;
            default -> 0;
        };
    }

    private static void useExplosionEffect(MagicEffectManager.MagicUseVariant variant, Optional<Player> optionalCaster, int level, boolean superCharged, Optional<AreaEffectCloud> optionalAreaEffectCloud, Optional<LivingEntity> optionalTarget) {

        switch (variant) {
            case BATTLE_BOTTLE -> {

                if(optionalAreaEffectCloud.isEmpty()) return;
                AreaEffectCloud areaEffectCloud = optionalAreaEffectCloud.get();
                Location center = areaEffectCloud.getLocation();

                float radius = 3;
                if(superCharged) radius += 1;
                if(level >= 2) radius += 1;
                areaEffectCloud.setDuration(1);

                center.getWorld().spawnParticle(Particle.FLAME, center, 40, radius, radius, radius, 0.5);
                center.getWorld().spawnParticle(Particle.LARGE_SMOKE, center, 40, radius, radius, radius, 0.5);
                center.createExplosion(2, false);
            }
            case BATTLE_BOTTLE_APPLY -> {
                if(optionalTarget.isEmpty()) return;
                LivingEntity target = optionalTarget.get();
                target.setVelocity(new Vector(0, 1.6, 0));
            }
            case ENCHANT -> {

                if(optionalCaster.isEmpty()) return;
                Player player = optionalCaster.get();

                ItemStack itemInMainHand = player.getInventory().getItemInMainHand();

                Vector direction  = player.getLocation().clone().add(0, 1.5, 0).getDirection();

                player.setCooldown(itemInMainHand.getType(), 12/level * (20 * (superCharged ? 2 : 1)));

                player.launchProjectile(WindCharge.class, direction.multiply(2 * (superCharged ? 1.5 : 1)));

            }
        }

    }


    private static void useArrowRainEffect(MagicEffectManager.MagicUseVariant variant, Optional<Player> optionalCaster, int level, boolean superCharged, Optional<AreaEffectCloud> optionalAreaEffectCloud, Optional<LivingEntity> optionalTarget, Optional<Arrow> optionalArrow) {

        if(optionalArrow.isEmpty()) return;

        Arrow arrow = optionalArrow.get();

        switch (variant) {
            case ENCHANT -> {

                if(optionalCaster.isEmpty()) return;
                Player player = optionalCaster.get();

                arrowRainV1(arrow, level, player, superCharged);

            }
        }

    }

    private static void arrowRainV1(Arrow originalArrow, int enchantLevel, Player player, boolean superCharged) {

        originalArrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);

        int shift = 4;

        int arrowDistance = (enchantLevel >= 2 ? shift*4: shift*3);

        if(superCharged) arrowDistance += 4;


        final Location startLocation = player.getLocation();

        AtomicReference<Arrow> previousArrow = new AtomicReference<>(originalArrow);

        for (int i = shift; i < arrowDistance; i += shift) {

            int finalI = i;

            int spawnTIme = finalI - shift;

            Bukkit.getScheduler().runTaskLater(GradzixCombatSystem.plugin, () -> {

                Location location = startLocation.clone().add(startLocation.getDirection().multiply(finalI).add(new Vector(0, 1.5, 0)));
                location.add(0, 5, 0);
                location.getWorld().spawnParticle(Particle.CLOUD, location, 10, 0.5, 0.5, 0.5, 0.05);

                Arrow summonedArrowAtLoc = summonArrowAtLoc(previousArrow.get(), location, enchantLevel, superCharged);

                previousArrow.set(summonedArrowAtLoc);

                if(spawnTIme == 0) originalArrow.remove();

            }, spawnTIme);

        }

    }

    private static Arrow summonArrowAtLoc(Arrow arrow, Location location, int level, boolean superCharged) {

        Arrow arrowToReturn = null;
        if (level >= 3 || superCharged) {
            for (int j = 0; j < (superCharged ? 5 : 3); j++) {
                Location location1 = location.clone().add(Math.random() * 1.9 - 0.95, 0, Math.random() * 1.9 - 0.95);
                arrowToReturn = summonArrowAtLoc(arrow, location1);
            }
        } else {
            arrowToReturn = summonArrowAtLoc(arrow, location);
        }
        return arrowToReturn;
    }

    private static Arrow summonArrowAtLoc(Arrow arrow, Location location) {

        Arrow copy = (Arrow) arrow.copy(location);

        copy.setVisibleByDefault(true);
        copy.setShooter(arrow.getShooter());

        copy.setRotation(0, 0);

        Vector velocity = copy.getVelocity();
        velocity.setX(0).setZ(0).setY(-1.5);
        copy.setVelocity(velocity);

        copy.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);

        return copy;
    }



    static class ComboData {

        private int hitCount;
        private long lastHit;
        void setHitCount(int hitCount) {
            this.hitCount = hitCount;
        }
        void setLastHit(long lastHit) {
            this.lastHit = lastHit;
        }
        ComboData(int hitCount, long lastHit) {
            this.hitCount = hitCount;
            this.lastHit = lastHit;
        }
        int getHitCount() {
            return hitCount;
        }
        long getLastHit() {
            return lastHit;
        }
    }

    private static final HashMap<UUID, ComboData> playerCombos = new HashMap<>();

    public static void removePlayerFromCombo(Player damaged) {
        playerCombos.remove(damaged.getUniqueId());
    }

    private static double useComboEffect(MagicEffectManager.MagicUseVariant variant, Optional<Player> optionalCaster, int level, boolean superCharged, Optional<AreaEffectCloud> optionalAreaEffectCloud, Optional<LivingEntity> optionalTarget) {

        switch (variant) {
            case ENCHANT -> {

                if(optionalCaster.isEmpty()) return 1;
                Player damager = optionalCaster.get();

                ComboData combo = playerCombos.getOrDefault(damager.getUniqueId(), new ComboData(1, System.currentTimeMillis()));

                if (System.currentTimeMillis() - combo.getLastHit() > 4000) {
                    combo.setHitCount(1);
                    combo.setLastHit(System.currentTimeMillis());
                } else {
                    combo.setHitCount(combo.getHitCount() + 1);
                    combo.setLastHit(System.currentTimeMillis());
                }
                playerCombos.put(damager.getUniqueId(), combo);

                double baseMultiplier = 1.0;
                switch (level) {

                    case 1 -> {

                        switch (combo.getHitCount()) {
                            case 2 -> baseMultiplier = 1.07;
                            case 3 -> baseMultiplier = 1.14;
                            case 4 -> baseMultiplier = 1.21;
                            default -> baseMultiplier = 1.28;
                        }
                    }
                    case 2 -> {

                        switch (combo.getHitCount()) {
                            case 2 -> baseMultiplier = 1.09;
                            case 3 -> baseMultiplier = 1.18;
                            case 4 -> baseMultiplier = 1.27;
                            default -> baseMultiplier = 1.36;
                        }
                    }
                    case 3 -> {

                        switch (combo.getHitCount()) {
                            case 2 -> baseMultiplier = 1.11;
                            case 3 -> baseMultiplier = 1.22;
                            case 4 -> baseMultiplier = 1.33;
                            default -> baseMultiplier = 1.44;
                        }
                    }
                }

                if(superCharged) baseMultiplier += 0.2;


                return (baseMultiplier);
            }
        }
        return 1;

    }



    private static void useLifeStealEffect(MagicEffectManager.MagicUseVariant variant, Optional<Player> optionalCaster, int level, boolean superCharged, Optional<AreaEffectCloud> optionalAreaEffectCloud, Optional<LivingEntity> optionalTarget) {

        switch (variant) {
            case ENCHANT -> {

                if(optionalCaster.isEmpty()) return;
                Player damager = optionalCaster.get();
                if(optionalTarget.isEmpty()) return;
                LivingEntity damaged = optionalTarget.get();

                if(!isLifeStealSuccessful(level, superCharged)) return;

                double damagedHealth = damaged.getHealth();
                double lifeToTransfer = damagedHealth / (superCharged ? 3 : 5);

                damager.setHealth(damager.getHealth() + lifeToTransfer);
                damaged.setHealth(damagedHealth - lifeToTransfer);

                damaged.getLocation().getWorld().spawnParticle(Particle.SOUL, damaged.getLocation(), 15, 0.7, 0.7, 0.7, 0.05);
                damager.getLocation().getWorld().spawnParticle(Particle.HEART, damaged.getLocation(), 5, 0.7, 0.7, 0.7, 0.05);

                damager.getWorld().playSound(damager.getLocation(), Sound.ENTITY_WARDEN_HEARTBEAT, 0.7f, 0.5f);

            }
        }

    }

    private static boolean isLifeStealSuccessful(int tier, boolean superCharged) {
        double chance = getLifeStealChance(tier);

        if(superCharged) chance += 0.1;

        return chance >= random.nextDouble();
    }

    private static double getLifeStealChance(int tier) {
        switch (tier) {
            case 1:
                return 0.05;
            case 2:
                return 0.10;
            case 3:
                return 0.15;
            default:
                return 0;
        }
    }


    private static final HashMap<UUID, Long> lastLightningUse = new HashMap<>();

    private static void useLightningEffect(MagicEffectManager.MagicUseVariant variant, Optional<Player> optionalCaster, int level, boolean superCharged, Optional<AreaEffectCloud> optionalAreaEffectCloud, Optional<LivingEntity> optionalTarget, Optional<Location> optionalHitLocation) {

        if(optionalHitLocation.isEmpty()) return;
        Location hitLocation = optionalHitLocation.get();

        switch (variant) {
            case ENCHANT -> {

                if(optionalCaster.isEmpty()) return;
                Player damager = optionalCaster.get();

                int cooldownSeconds = switch (level) {
                    case 1 -> 16;
                    case 2 -> 12;
                    case 3 -> 8;
                    default -> 0;
                };
//                if(superCharged) cooldownSeconds -= 4;

                if(lastLightningUse.containsKey(damager.getUniqueId())) {
                    if(System.currentTimeMillis() - lastLightningUse.get(damager.getUniqueId()) < 1000 * cooldownSeconds) {
                        return;
                    }
                }

                lastLightningUse.put(damager.getUniqueId(), System.currentTimeMillis());

                LightningStrike lightningStrike = hitLocation.getWorld().strikeLightning(hitLocation);
                lightningStrike.setCausingPlayer(damager);

                if(superCharged) {
                    for (int j = 0; j < 3; j++) {
                        Location location1 = hitLocation.clone().add(Math.random() * 1.9 - 0.95, 0, Math.random() * 1.9 - 0.95);

                        LightningStrike additionalLightningStrike = location1.getWorld().strikeLightning(location1);
                        additionalLightningStrike.setCausingPlayer(damager);
                    }
                }

            }
        }

    }

    private static final NamespacedKey SOULS_STOLEN_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_souls_stolen");

    public static int getSoulsStolen(ItemMeta itemMeta) {
        if (itemMeta == null) return 0;
        return itemMeta.getPersistentDataContainer().getOrDefault(SOULS_STOLEN_KEY, PersistentDataType.INTEGER, 0);
    }

    public static int getLimitByEnchantLevel(int enchantLevel) {
        return switch (enchantLevel) {
            case 1 -> 10;
            case 2 -> 15;
            case 3 -> 20;

            default -> 0;
        };
    }

    private static void useSoulStealEffect(MagicUseVariant variant, Optional<Player> optionalCaster, int level, boolean superCharged, Optional<AreaEffectCloud> optionalAreaEffectCloud, Optional<LivingEntity> optionalTarget) {

        boolean finalSuperCharged = superCharged;
        switch (variant) {
            case ENCHANT -> {

                if(optionalCaster.isEmpty()) return;
                Player player = optionalCaster.get();

                ItemStack shield = player.getInventory().getItemInOffHand();
                if (shield == null) return;
                if (shield.getItemMeta() == null) return;
                CustomItem customItem = CustomItemManager.getCustomItem(shield);
                if(customItem == null) return;

                AtomicReference<Integer> soulsStolen = new AtomicReference<>(getSoulsStolen(shield));
                if (soulsStolen.get() == 0) return;

                BukkitRunnable bukkitRunnable = new BukkitRunnable() {
                    @Override
                    public void run() {

                        if (soulsStolen.get() == 0 || !player.isBlocking() || !player.isSneaking() || player.isDead() || !player.isOnline()) {
                            cancel();
                            setSoulsStolen(shield, soulsStolen.get());
                            customItem.setLoreAndName(shield, 1);
                            return;
                        }

                        Location location = player.getLocation();

                        double radius = (finalSuperCharged ? 5 : 3);

                        location.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, location, 20, radius/5, radius/5, radius/5, 0.05);
                        location.getWorld().spawnParticle(Particle.LARGE_SMOKE, location, 20, radius/5, radius/5, radius/5, 0.05);

                        for (Entity entity : player.getNearbyEntities(radius, radius, radius)) {
                            if (entity instanceof LivingEntity) {
                                Vector direction = entity.getLocation().subtract(location).toVector().normalize().multiply(1.2);
                                direction.setY(direction.getY() + 0.5);
                                entity.setVelocity(direction);
                            }
                        }
                        soulsStolen.set(soulsStolen.get() - 1);

                    }
                };

                bukkitRunnable.runTaskTimer(GradzixCombatSystem.plugin, 2, 20);


            }
        }
    }

    public static int getSoulsStolen(ItemStack itemStack) {
        return itemStack.getItemMeta().getPersistentDataContainer().getOrDefault(SOULS_STOLEN_KEY, PersistentDataType.INTEGER, 0);

    }

    public static void setSoulsStolen(ItemStack itemStack, int value) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.getPersistentDataContainer().set(SOULS_STOLEN_KEY, PersistentDataType.INTEGER, value);
        itemStack.setItemMeta(itemMeta);
    }
}
