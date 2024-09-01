package me.xxgradzix.gradzixcombatsystem;

import me.xxgradzix.gradzixcombatsystem.items.ItemManager;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

import static org.bukkit.Material.TRIDENT;

public class ArmorTierManager {



    public enum ArmorWeight {
        LIGHT, MEDIUM, HEAVY
    }
    public enum ArmorType {
        HELMET, CHESTPLATE, LEGGINGS, BOOTS
    }


    public static final int LIGHT_ARMOR_HELMET = 1;
    public static final int LIGHT_ARMOR_CHEST_PLATE = 3;
    public static final int LIGHT_ARMOR_LEGGINGS = 2;
    public static final int LIGHT_ARMOR_BOOTS = 2;

    public static final int MEDIUM_ARMOR_HELMET = 1;
    public static final int MEDIUM_ARMOR_CHEST_PLATE = 4;
    public static final int MEDIUM_ARMOR_LEGGINGS = 3;
    public static final int MEDIUM_ARMOR_BOOTS = 2;

    public static final int HEAVY_ARMOR_HELMET = 2;
    public static final int HEAVY_ARMOR_CHEST_PLATE = 5;
    public static final int HEAVY_ARMOR_LEGGINGS = 3;
    public static final int HEAVY_ARMOR_BOOTS = 2;

    private static int getArmorGenericArmorAttribute(ArmorWeight armorWeight, ArmorType armorType, int tier) {

        int bonus = tier == 3 ? 1 : tier == 5 ? 2 : 0;

        switch (armorWeight) {
            case LIGHT -> {
                switch (armorType) {
                    case HELMET -> {
                        return LIGHT_ARMOR_HELMET + bonus;
                    }
                    case CHESTPLATE -> {
                        return LIGHT_ARMOR_CHEST_PLATE + bonus;
                    }
                    case LEGGINGS -> {
                        return LIGHT_ARMOR_LEGGINGS + bonus;
                    }
                    case BOOTS -> {
                        return LIGHT_ARMOR_BOOTS + bonus;
                    }
                }
            }
            case MEDIUM -> {
                switch (armorType) {
                    case HELMET -> {
                        return MEDIUM_ARMOR_HELMET + bonus;
                    }
                    case CHESTPLATE -> {
                        return MEDIUM_ARMOR_CHEST_PLATE + bonus;
                    }
                    case LEGGINGS -> {
                        return MEDIUM_ARMOR_LEGGINGS + bonus;
                    }
                    case BOOTS -> {
                        return MEDIUM_ARMOR_BOOTS + bonus;
                    }
                }
            }
            case HEAVY -> {
                switch (armorType) {
                    case HELMET -> {
                        return HEAVY_ARMOR_HELMET + bonus;
                    }
                    case CHESTPLATE -> {
                        return HEAVY_ARMOR_CHEST_PLATE + bonus;
                    }
                    case LEGGINGS -> {
                        return HEAVY_ARMOR_LEGGINGS + bonus;
                    }
                    case BOOTS -> {
                        return HEAVY_ARMOR_BOOTS + bonus;
                    }
                }
            }
        }

        return 0;
    }


    private static final int AXE_DAMAGE = 7;
    private static final double AXE_ATTACK_SPEED = 0.6;

    private static final int SWORD_DAMAGE = 6;
    private static final double SWORD_ATTACK_SPEED = 1.6;

    private static final int BOW_DAMAGE = 5;

    private static final int CROSSBOW_DAMAGE = 6;

    private static final int TRIDENT_DAMAGE = 7;
    private static final double TRIDENT_ATTACK_SPEED = 1.2;


    private static int getWeaponAttribute(ItemManager.WeaponType type, int tier) {

        int bonus = tier == 3 ? 2 : tier == 5 ? 4 : 0;

        switch (type) {
            case AXE -> {
                return AXE_DAMAGE + bonus;
            }
            case SWORD -> {
                return SWORD_DAMAGE + bonus;
            }
            case BOW -> {
                return BOW_DAMAGE + bonus;
            }
            case JAVELIN -> {
                return TRIDENT_DAMAGE + bonus;
            }
            case CROSSBOW -> {
                return CROSSBOW_DAMAGE + bonus;
            }
        }
        return 0;
    }

    private static double getWeaponAttackSpeed(ItemManager.WeaponType type, int tier) {

        double bonus = 0.0;
        switch (type) {
            case AXE -> {
                if(tier >= 2)
                return AXE_ATTACK_SPEED;
            }
            case SWORD -> {
                return SWORD_ATTACK_SPEED;
            }
            case JAVELIN -> {
                return TRIDENT_ATTACK_SPEED;
            }
        }
        return 0;
    }

    public static void setAttributesPerTierAndWeight(ItemStack item, ArmorType type, ArmorWeight weight, int tier) {

        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
        itemMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
        itemMeta.removeAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
        itemMeta.removeAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED);

        EquipmentSlot equipmentSlot = null;

        switch (type) {
            case HELMET -> equipmentSlot = EquipmentSlot.HEAD;
            case CHESTPLATE -> equipmentSlot = EquipmentSlot.CHEST;
            case LEGGINGS -> equipmentSlot = EquipmentSlot.LEGS;
            case BOOTS -> equipmentSlot = EquipmentSlot.FEET;
        }

        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR.name(), getArmorGenericArmorAttribute(weight, type, tier), AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));

        if(weight == ArmorWeight.LIGHT) {
            if(tier > 3) {
                itemMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_MOVEMENT_SPEED.name(), 0.005, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
            } else if(tier > 1) {
                itemMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_MOVEMENT_SPEED.name(), 0.003, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
            }
        }

        if(weight == ArmorWeight.MEDIUM) {
            if(tier > 1) {
                itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR_TOUGHNESS.name(), 1, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
            }
            if(tier > 3) {
                itemMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_KNOCKBACK_RESISTANCE.name(), 0.05, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
            }
        }

        if (weight == ArmorWeight.HEAVY) {
            if(tier > 1) {
                itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR_TOUGHNESS.name(), 3, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
            }
            if(tier > 3) {
                itemMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_KNOCKBACK_RESISTANCE.name(), 0.15, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
            }
        }
        item.setItemMeta(itemMeta);

    }

    public static void setAttributesPerWeaponTierAndType(ItemStack item, ItemManager.WeaponType weaponType, int tier) {
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
        itemMeta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);

        EquipmentSlot equipmentSlot = EquipmentSlot.HAND;
        EquipmentSlot equipmentSlot2 = EquipmentSlot.OFF_HAND;

        if(weaponType.equals(ItemManager.WeaponType.AXE)) {
            itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ATTACK_DAMAGE.name(), getWeaponAttribute(weaponType, tier), AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
            itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ATTACK_DAMAGE.name(), getWeaponAttribute(weaponType, tier), AttributeModifier.Operation.ADD_NUMBER, equipmentSlot2));

            itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ATTACK_SPEED.name(), AXE_ATTACK_SPEED, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
            itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ATTACK_SPEED.name(), AXE_ATTACK_SPEED, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot2));
        }

        if(weaponType.equals(ItemManager.WeaponType.SWORD)) {
            itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ATTACK_DAMAGE.name(), getWeaponAttribute(weaponType, tier), AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
            itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ATTACK_DAMAGE.name(), getWeaponAttribute(weaponType, tier), AttributeModifier.Operation.ADD_NUMBER, equipmentSlot2));
        }

        item.setItemMeta(itemMeta);

    }
}
