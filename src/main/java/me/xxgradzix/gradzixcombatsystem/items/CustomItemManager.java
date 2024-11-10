package me.xxgradzix.gradzixcombatsystem.items;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.items.armors.ArmorType;
import me.xxgradzix.gradzixcombatsystem.items.armors.CustomArmor;
import me.xxgradzix.gradzixcombatsystem.items.armors.instances.HeavyArmor;
import me.xxgradzix.gradzixcombatsystem.items.armors.instances.Light;
import me.xxgradzix.gradzixcombatsystem.items.armors.instances.Medium;
import me.xxgradzix.gradzixcombatsystem.items.battleBottles.CombatPotionType;
import me.xxgradzix.gradzixcombatsystem.items.battleBottles.CustomBottle;
import me.xxgradzix.gradzixcombatsystem.items.battleBottles.instances.*;
import me.xxgradzix.gradzixcombatsystem.items.combatPotion.CustomBattlePotion;
import me.xxgradzix.gradzixcombatsystem.items.combatPotion.CustomBattlePotionOrb;
import me.xxgradzix.gradzixcombatsystem.items.combatPotion.orbInstances.DashOrb;
import me.xxgradzix.gradzixcombatsystem.items.combatPotion.orbInstances.HeartOrb;
import me.xxgradzix.gradzixcombatsystem.items.combatPotion.orbInstances.TeleportOrb;
import me.xxgradzix.gradzixcombatsystem.items.combatPotion.orbInstances.VolcanoOrb;
import me.xxgradzix.gradzixcombatsystem.items.enchantBooks.instances.*;
import me.xxgradzix.gradzixcombatsystem.items.projectiles.instances.CommonArrow;
import me.xxgradzix.gradzixcombatsystem.items.projectiles.instances.ExplosiveArrow;
import me.xxgradzix.gradzixcombatsystem.items.projectiles.instances.GravitionalArrow;
import me.xxgradzix.gradzixcombatsystem.items.projectiles.instances.KnockBackArrow;
import me.xxgradzix.gradzixcombatsystem.items.weapons.Tierable;
import me.xxgradzix.gradzixcombatsystem.items.weapons.instances.*;
import me.xxgradzix.gradzixcombatsystem.managers.EnchantManager.EnchantManager;
import me.xxgradzix.gradzixcombatsystem.managers.modifiersManager.ModifiersManager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public enum CustomItemManager {


    SWORD(new BattleSword()),
    BOW(new BattleBow()),
    CROSSBOW(new BattleCrossBow()),
    JAVELIN(new BattleSpear()),
    AXE(new BattleAxe()),
    SHIELD(new BattleShield()),

    COMMON_ARROW(new CommonArrow()),
    GRAVITATIONAL_ARROW(new GravitionalArrow()),
    KNOCK_BACK_ARROW(new KnockBackArrow()),
    EXPLOSIVE_ARROW(new ExplosiveArrow()),

    LIGHT_ARMOR(new Light()),
    MEDIUM_ARMOR(new Medium()),
    HEAVY_ARMOR(new HeavyArmor()),

    FREEZE_COMBAT_POTION(new CombatBottleFreeze()),
    FIRE_COMBAT_POTION(new CombatBottleFire()),
    WIND_COMBAT_POTION(new CombatBottleWind()),
    EXPLOSION_COMBAT_POTION(new CombatBottleExplosion()),
    POISON_COMBAT_POTION(new CombatBottlePoison()),

    ARROW_RAIN_ENCHANT_BOOK(new ArrowRainEnchantBook()),
    ATTACK_COMBO_ENCHANT_BOOK(new AttackComboEnchantBook()),
    LIFE_STEAL_ENCHANT_BOOK(new LifeStealEnchantBook()),
    POISON_ENCHANT_BOOK(new PoisonEnchantBook()),
    SOUL_STEAL_ENCHANT_BOOK(new SoulStealEnchantBook()),
    FREEZE_ENCHANT_BOOK(new FreezeEnchantBook()),
    WIND_CHARGE_ENCHANT_BOOK(new WindChargeEnchantBook()),
    FIRE_AFFLICTION_ENCHANT_BOOK(new FireAfflictionEnchantBook()),
    LIGHTNING_ENCHANT_BOOK(new LightningEnchantBook()),
    MULTI_SHOT_ENCHANT_BOOK(new MultiShotEnchantBook()),
    GREED_ENCHANT_BOOK(new GreedEnchantBook()),

    DASH_ORB(new DashOrb()),
    VOLCANO_ORB(new VolcanoOrb()),
    TELEPORT_ORB(new TeleportOrb()),
    HEART_ORB(new HeartOrb()),

    FALLEN_HERO_POTION(new CustomBattlePotion())
    ;

    private final CustomItem customItem;

    public static ArmorType getArmorType(ItemStack defaultItemStack) {

        if(defaultItemStack == null) return null;

        if (defaultItemStack.getType().name().contains("HELMET")) {
            return ArmorType.HELMET;
        } else if (defaultItemStack.getType().name().contains("CHESTPLATE")) {
            return ArmorType.CHESTPLATE;
        } else if (defaultItemStack.getType().name().contains("LEGGINGS") || defaultItemStack.getType().name().contains("PANTS")) {
            return ArmorType.LEGGINGS;
        } else if (defaultItemStack.getType().name().contains("BOOTS")) {
            return ArmorType.BOOTS;
        }
        return null;

    }

    public static CustomItem getEnchantBookCustomItemByEnchant(EnchantManager.Enchant enchant) {
        return switch (enchant) {
            case FREEZE -> FREEZE_ENCHANT_BOOK.getCustomItem();
            case SOUL_STEAL -> SOUL_STEAL_ENCHANT_BOOK.getCustomItem();
            case MULTI_SHOT -> MULTI_SHOT_ENCHANT_BOOK.getCustomItem();
            case FIRE_AFFLICTION -> FIRE_AFFLICTION_ENCHANT_BOOK.getCustomItem();
            case LIFE_STEAL -> LIFE_STEAL_ENCHANT_BOOK.getCustomItem();
            case ARROW_RAIN -> ARROW_RAIN_ENCHANT_BOOK.getCustomItem();
            case LIGHTNING -> LIGHTNING_ENCHANT_BOOK.getCustomItem();
            case ATTACK_COMBO -> ATTACK_COMBO_ENCHANT_BOOK.getCustomItem();
            case WIND_CHARGE -> WIND_CHARGE_ENCHANT_BOOK.getCustomItem();
            case POISON -> POISON_ENCHANT_BOOK.getCustomItem();
            case GREED -> GREED_ENCHANT_BOOK.getCustomItem();
        };
    }

    public static CustomBottle getCustomPotionBottle(CombatPotionType potionType) {
        return switch (potionType) {
//            default -> null;
            case FREEZE -> (CustomBottle) FREEZE_COMBAT_POTION.getCustomItem();
            case EXPLOSION -> (CustomBottle) EXPLOSION_COMBAT_POTION.getCustomItem();
            case POISON -> (CustomBottle) POISON_COMBAT_POTION.getCustomItem();
            case FIRE -> (CustomBottle) FIRE_COMBAT_POTION.getCustomItem();
            case WIND -> (CustomBottle) WIND_COMBAT_POTION.getCustomItem();
        };
    }
    public static CustomBottle getCustomPotionBottle(ItemStack itemStack) {
        if(itemStack == null) return null;
        String customId = CustomItem.getCustomId(itemStack.getItemMeta()).orElse(null);
        if(customId == null) return null;

        for(CustomItemManager customItemManager : CustomItemManager.values()) {
            if(customItemManager.customItem.getCustomId().equals(customId)) {
                if(customItemManager.customItem instanceof CustomBottle customBottle) {
                    return customBottle;
                }
            }
        }

        return null;
    }

    public static CustomBattlePotionOrb getCustomPotionOrb(ItemStack item) {

        if(item == null) return null;
        String customId = CustomItem.getCustomId(item.getItemMeta()).orElse(null);
        if(customId == null) return null;

        for(CustomItemManager customItemManager : CustomItemManager.values()) {
            if(customItemManager.customItem.getCustomId().equals(customId)) {
                if(customItemManager.customItem instanceof CustomBattlePotionOrb customBattlePotionOrb) {
                    return customBattlePotionOrb;
                }
            }
        }

        return null;

    }

    public static CustomBattlePotionOrb getCustomPotionOrbByOrbID(String orbID) {

        for(CustomItemManager customItemManager : CustomItemManager.values()) {
            if(customItemManager.customItem instanceof CustomBattlePotionOrb customBattlePotionOrb) {
                if(customBattlePotionOrb.getCustomId().equals(orbID)) {
                    return customBattlePotionOrb;
                }
            }
        }

        return null;
    }


    public CustomItem getCustomItem() {
        return customItem;
    }

    CustomItemManager(CustomItem customItem) {
        this.customItem = customItem;
    }

    /** WEAPON METHODS **/

    public static CustomItem getCustomItem(ItemStack itemStack) {
        if(itemStack == null) return null;
        String customId = CustomItem.getCustomId(itemStack.getItemMeta()).orElse(null);
        if(customId == null) return null;

        for(CustomItemManager customItemManager : CustomItemManager.values()) {
            if(customItemManager.customItem.getCustomId().equals(customId)) {
                return customItemManager.customItem;
            }
        }

        return null;
    }

    public static boolean canBeUpgraded(ItemStack itemStack) {
        CustomItem customItem1 = getCustomItem(itemStack);
        if(customItem1 == null) return false;
        return customItem1 instanceof Upgradable;

    }

    public static ItemStack getUpgradedVersionOfItem(ItemStack itemStack) {

        CustomItem customItem = getCustomItem(itemStack);

        if(customItem == null) return itemStack;

        if(!(customItem instanceof Upgradable)) return itemStack;

        int newTier = Tierable.getTier(itemStack) + 1;

        if(newTier > GradzixCombatSystem.MAX_TIER) return itemStack;

        ItemStack newItem;

        if(customItem instanceof CustomArmor) {
            ArmorType armorType = getArmorType(itemStack);
            newItem = customItem.getDefaultItemStack(newTier, armorType);

        } else {
            newItem = customItem.getDefaultItemStack(newTier);
        }

        ItemMeta newMeta = newItem.getItemMeta();

        EnchantManager.copyEnchantsFromMetaToMeta(itemStack.getItemMeta(), newMeta);
        ModifiersManager.copyModifiers(itemStack.getItemMeta(), newMeta);

        customItem.setLoreAndName(newMeta, newTier);

        newItem.setItemMeta(newMeta);

        return newItem;
    }


}
