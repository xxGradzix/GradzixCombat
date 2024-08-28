package me.xxgradzix.gradzixcombatsystem;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.UUID;

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

    private static int getLightArmorGenericArmorAttribute(ArmorWeight armorWeight, ArmorType armorType, int tier) {

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

    public static void setAttributesPerTierAndWeight(ItemStack item, ArmorType type, ArmorWeight weight, int tier) {

        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
        itemMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
        itemMeta.removeAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
        itemMeta.removeAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED);

        EquipmentSlot equipmentSlot = null;


        switch (type) {
            case HELMET -> {
                equipmentSlot = EquipmentSlot.HEAD;
            }
            case CHESTPLATE -> {
                equipmentSlot = EquipmentSlot.CHEST;
            }
            case LEGGINGS -> {
                equipmentSlot = EquipmentSlot.LEGS;

            }
            case BOOTS -> {
                equipmentSlot = EquipmentSlot.FEET;
            }
        }

        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR.name(), getLightArmorGenericArmorAttribute(weight, type, tier), AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));

        if(weight == ArmorWeight.LIGHT) {

            switch (tier) {
                case 2 -> {
                    itemMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_MOVEMENT_SPEED.name(), 0.05, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
                }
                case 4 -> {
                    itemMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_MOVEMENT_SPEED.name(), 0.1, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
                }
            }

        }
        if(weight == ArmorWeight.MEDIUM) {

            switch (tier) {
                case 2 -> {
                    itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR_TOUGHNESS.name(), 1, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
                }
                case 4 -> {
                    itemMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_KNOCKBACK_RESISTANCE.name(), 0.05, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
                }
            }
        }
        if (weight == ArmorWeight.HEAVY) {

            switch (tier) {
                case 2 -> {
                    itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ARMOR_TOUGHNESS.name(), 3, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
                }
                case 4 -> {
                    itemMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_KNOCKBACK_RESISTANCE.name(), 0.15, AttributeModifier.Operation.ADD_NUMBER, equipmentSlot));
                }
            }
        }

    }

}
