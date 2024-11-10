package me.xxgradzix.gradzixcombatsystem.managers.magicEffects;

import com.destroystokyo.paper.ParticleBuilder;
import me.xxgradzix.gradzixcombatsystem.managers.EnchantManager.EnchantManager;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Optional;
import java.util.Random;

public class MagicEffectManager {

    private static final Random random = new Random();

    public enum MagicUseVairant {
        BATTLE_BOTTLE,
        BATTLE_BOTTLE_APPLY,
        ORB,
        ENCHANT,
        ARROW
    }

    public static void useFireEffect(MagicUseVairant vairant, Optional<Player> optionalCaster, int level, boolean superCharged, Optional<AreaEffectCloud> optionalAreaEffectCloud, Optional<LivingEntity> optionalTarget) {

        switch (vairant) {
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

                Vector pependicularVerticalAxis = playerAxis.clone().rotateAroundAxis(axisToRotate, Math.toRadians(Math.toDegrees(angle) - 90)).normalize();

                int fireRadius = level >= 2 ? 4 : 3;

                if (superCharged) fireRadius += 1;

                for (int i = 0; i < radius; i += 10) {

                    Vector rotated1 = direction.clone().rotateAroundAxis(pependicularVerticalAxis, Math.toRadians(i));
                    Vector rotated2 = direction.clone().rotateAroundAxis(pependicularVerticalAxis, Math.toRadians(-i));

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

                                if (superCharged) fireTicks *= 2;

                                player1.setFireTicks(fireTicks);
                            }
                        }
                    });

                    end2.getNearbyEntities(fireRadius, 0.5, fireRadius).forEach(entity -> {
                        if (entity instanceof LivingEntity player1) {
                            if (player1 != player) {

                                int fireTicks = level * 2 * 20;

                                if (superCharged) fireTicks *= 2;

                                player1.setFireTicks(fireTicks);
                            }
                        }
                    });

                    player.spawnParticle(Particle.FLAME, start1, 0, endVector1.getX(), endVector1.getY(), endVector1.getZ(), 0.1);
                    player.spawnParticle(Particle.FLAME, start2, 0, endVector2.getX(), endVector2.getY(), endVector2.getZ(), 0.1);

                    player.spawnParticle(Particle.SMALL_FLAME, end1, 25, fireRadius / 4, 1, fireRadius / 4, 0.04);
                    player.spawnParticle(Particle.SMALL_FLAME, end2, 25, fireRadius / 4, 1, fireRadius / 4, 0.04);

                    player.getWorld().playSound(location, Sound.ENTITY_BLAZE_SHOOT, 0.6f, 0.4f);

                    player.setCooldown(player.getInventory().getItemInMainHand().getType(), 20 * (superCharged ? 5 : 7));

                }
            }
        }

    }


    public static void useFreezeEffect(MagicUseVairant vairant, Optional<Player> optionalCaster, int level, boolean superCharged, Optional<AreaEffectCloud> optionalAreaEffectCloud, Optional<LivingEntity> optionalTarget) {

        switch (vairant) {
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


    public static void usePoisonEffect(MagicUseVairant variant, Optional<Player> optionalCaster, int level, boolean superCharged, Optional<AreaEffectCloud> optionalAreaEffectCloud, Optional<LivingEntity> optionalTarget) {

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

                areaEffectCloud.setParticle(new ParticleBuilder(Particle.EFFECT).color(Color.GREEN).particle());
                center.getWorld().spawnParticle(Particle.EFFECT, center, 20, radius, radius, radius, 0.05);
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


    public static void useWindEffect(MagicUseVairant variant, Optional<Player> optionalCaster, int level, boolean superCharged, Optional<AreaEffectCloud> optionalAreaEffectCloud, Optional<LivingEntity> optionalTarget) {

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

        switch (poisonLevel) {
            case 1:
                return 0.10;
            case 2:
                return 0.15;
            case 3:
                return 0.20;
            default:
                return 0;
        }
    }

}
